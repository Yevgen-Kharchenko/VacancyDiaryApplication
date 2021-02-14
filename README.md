# VacancyDiaryApplication
# **Тестовое задание**

Создать Vacancy diary API для заполнения и отслеживания состояния своих откликов на вакансии

## Приложение должно содержать:

- Список пользователей
- Для каждого пользователя список вакансий, куда он подавался и состояние отзыва

## Возможные действия через API:

### Пользователь

- Регистрация пользователя (Email, пароль, ...)

    Authorization - Bearer Token

- Вход для пользователя (Email, пароль)
- Редактирование данных у пользователя (пароль, ...)
- Получение данных пользователя (Email, пароль, ...)
- Удаление пользователя

### Вакансии

1. Вывести список все вакансий куда подавался кандидат

Должна присутствовать пагинация (лимит 5 вакансий на страницу)

Массив записей с полями: 

- Название компании
- Должность
- Ожидаемая зарплата
- Ссылка на вакансию
- Контакты рекрутера
- Статус
- Дата последнего изменения статуса

2. Редактирование данных о вакансии - можно изменять все данные, которые касаются внесенной информации об этой вакансии. Изменения статуса на один из возможных:

- Подался
- Дали тестовое
- Жду фидбека
- Скрининг
- Техническое собеседование
- Оффер
- Отказ
- Нет ответа

3. Возможность выводить вакансии по статусам (на запрос с одним из статусов, в ответ получаем массив записей, у которых именно этот статус)

4. Возможность поиска по названию компании (на запрос с названием компании, в ответ получаем запись, которая касается этой компании)

5. Возможность отправки email с предустановленным текстом по всем вакансиям из категории "Жду фидбека", если на текущий момент запроса прошла неделя от момента, когда этот статус был установлен

## Обязательные данные в БД:

### Пользователи:

- Email
- Пароль

### Вакансии:

- Название компании
- Должность
- Ожидаемая зарплата
- Ссылка на вакансию
- Контакты рекрутера
- Статус. Каждая вакансия должна иметь один из статусов  ("Подался", "Дали тестовое", "Жду фидбека", "Скрининг", "Техническое собеседование", "Оффер", "Отказ", "Нет ответа")
- Дата последнего изменения статуса

## Технические требования:

- Предоставлен `API Postman`
- Результат API запросов `JSON`
- Используется база данных `h2`

## Инструкциями по установке и запуску

- Склонировать Git репозиторий
- Открыть проект в IntelliJ IDEA
- Запустить проект
- С помощью приложения Postman протестировать API запросы

