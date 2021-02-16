
insert into `user` (`name`, `email`, `password`) values
('admin','admin@g.com', '$2a$12$BzcrGYG77xPP4EtfdV1WtOCb67RtnTAvUmSPyOuB74ADsUeRsIlGW'),
('user1', 'user1@g.com', '$2a$12$BzcrGYG77xPP4EtfdV1WtOCb67RtnTAvUmSPyOuB74ADsUeRsIlGW'),
('user2', 'user2@g.com', '$2a$12$BzcrGYG77xPP4EtfdV1WtOCb67RtnTAvUmSPyOuB74ADsUeRsIlGW'),
('user3', 'user3@g.com', '$2a$12$BzcrGYG77xPP4EtfdV1WtOCb67RtnTAvUmSPyOuB74ADsUeRsIlGW');

insert into `vacancy`
(`name_company`, `position`,`expected_salary`, `link_to_vacancy`,`recruiters_contacts`,`status_vacancy`,`last_change`,`history`,`notes`,`user_id`)  values

('nameCompany1', 'Junior Java Developer', 500, 'linkToVacancy1', 'recruiters1@gmail.com','SUBMITTED','2021-01-20', '2021-01-20 SUBMITTED', 'notes',2),
('nameCompany2', 'Trainee Java Developer', 600, 'linkToVacancy2', 'recruiters2@gmail.com','GAVE_TEST','2021-01-21', '2021-01-20 SUBMITTED', 'notes',2),
('nameCompany3', 'Intern Java Developer', 700, 'linkToVacancy3', 'recruiters3@gmail.com','WAITING_FEEDBACK','2021-01-22', '2021-01-20 SUBMITTED','',2),
('nameCompany4', 'Middle Java Developer', 800, 'linkToVacancy4', 'recruiters4@gmail.com','SCREENING', '2021-01-23', '2021-01-20 SUBMITTED', 'notes',2),
('nameCompany5','Senior Java Developer', 900, 'linkToVacancy5', 'recruiters5@gmail.com','TECHNICAL_INTERVIEW','2021-01-24', '2021-01-20 SUBMITTED', 'notes',2),
('nameCompany6','Junior Java Developer', 1000, 'linkToVacancy6', 'recruiters6@gmail.com','OFFER','2021-02-02', '2021-01-20 SUBMITTED', '',3),
('nameCompany7','Trainee Java Developer', 1500, 'linkToVacancy7', 'recruiters7@gmail.com','REFUSED','2021-02-03', '2021-01-20 SUBMITTED', 'notes',2),
('nameCompany8','Intern Java Developer', 1700, 'linkToVacancy8', 'recruiters8@gmail.com','NO_RESPONSE','2021-02-03', '2021-01-20 SUBMITTED', 'notes',3),
('nameCompany9','Middle Java Developer', 2000, 'linkToVacancy9', 'recruiters9@gmail.com','SUBMITTED', '2021-02-04','2021-01-20 SUBMITTED', '',4),
('nameCompany10','Senior Java Developer', 5000, 'linkToVacancy10', 'recruiters10@gmail.com','WAITING_FEEDBACK', '2021-02-10','2021-01-20 SUBMITTED', '',4);

