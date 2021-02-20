package com.project.vacancy.service.impl;

import com.project.vacancy.dto.VacancyRequest;
import com.project.vacancy.dto.VacancyResponse;
import com.project.vacancy.exeption.EntityNotExistRuntimeException;
import com.project.vacancy.exeption.UserNotFoundException;
import com.project.vacancy.model.ApplicationUser;
import com.project.vacancy.model.Vacancy;
import com.project.vacancy.model.enums.StatusVacancy;
import com.project.vacancy.repositiry.VacancyRepository;
import com.project.vacancy.service.UserService;
import com.project.vacancy.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final int PAGE_SIZE = 5;
    private final VacancyRepository vacancyRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final JavaMailSender javaMailSender;

    @Override
    public List<VacancyResponse> findUsersVacancies(int currentPage) throws UserNotFoundException {
        ApplicationUser currentUser = userService.findCurrentUser();
        Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);
        List<Vacancy> vacancyList = vacancyRepository.findAllByUser(currentUser, pageable);

        return vacancyList.stream()
                .map(vacancy -> modelMapper.map(vacancy, VacancyResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createNewVacancy(VacancyRequest vacancyRequest) throws UserNotFoundException {
        Vacancy newVacancy = modelMapper.map(vacancyRequest, Vacancy.class);
        newVacancy.setLastChange(LocalDate.now());
        newVacancy.setUser(userService.findCurrentUser());
        vacancyRepository.save(newVacancy);
    }

    @Override
    public void updateVacancy(VacancyRequest vacancyRequest) {
        Vacancy oldVacancy = vacancyRepository.findById(vacancyRequest.getId())
                .orElseThrow(() -> new EntityNotExistRuntimeException("Vacancy not found"));
        Vacancy updatedVacancy = modelMapper.map(vacancyRequest, Vacancy.class);
        updatedVacancy.setUser(oldVacancy.getUser());
        if (!oldVacancy.getStatusVacancy().equals(updatedVacancy.getStatusVacancy())) {
            updatedVacancy.setLastChange(LocalDate.now());
        }else{
            updatedVacancy.setLastChange(oldVacancy.getLastChange());
        }
        vacancyRepository.save(updatedVacancy);
    }

    @Override
    public void deleteVacancy(long id) throws UserNotFoundException {
        ApplicationUser currentUser=userService.findCurrentUser();
        Vacancy vacancy=vacancyRepository.findById(id)
                .orElseThrow(()->new EntityNotExistRuntimeException("Vacancy not found"));
        if(vacancy.getUser().equals(currentUser))
        vacancyRepository.deleteById(id);
    }

    @Override
    public List<VacancyResponse> findUsersVacanciesByStatus(StatusVacancy statusVacancy, int currentPage) throws UserNotFoundException {
        ApplicationUser currentUser = userService.findCurrentUser();
        Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);
        List<Vacancy> vacancyListByStatus = vacancyRepository.findAllByUserAndStatusVacancy(statusVacancy,currentUser, pageable);

        return vacancyListByStatus.stream()
                .map(vacancy -> modelMapper.map(vacancy, VacancyResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<VacancyResponse> findUsersVacanciesByNameCompany(String nameCompany, int currentPage) throws UserNotFoundException {
        ApplicationUser currentUser = userService.findCurrentUser();
        Pageable pageable = PageRequest.of(currentPage, PAGE_SIZE);
        List<Vacancy> vacancyListByNameCompany = vacancyRepository.findAllByUserAndNameCompany(nameCompany, currentUser, pageable);

        return vacancyListByNameCompany.stream()
                .map(vacancy -> modelMapper.map(vacancy, VacancyResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void sendEmail() throws UserNotFoundException {
        ApplicationUser currentUser = userService.findCurrentUser();
        LocalDate verificationDate = LocalDate.now().minusDays(7);
        List<Vacancy> vacancyListByStatus = vacancyRepository.findAllForEmailSending(currentUser, verificationDate);
        for (Vacancy vacancy : vacancyListByStatus) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("admin@g.com");
            message.setTo(vacancy.getRecruitersContacts());
            message.setSubject("I`m waiting for your feedback");
            message.setText("Hello! I applied for the "
                    + vacancy.getPosition() + " posted at "
                    + vacancy.getLinkToVacancy() + " However I received no response."
                    + " I`m waiting for your feedback");
            javaMailSender.send(message);
        }


    }
}
