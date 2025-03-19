# Calorie Tracker API

## Описание
Calorie Tracker API - это RESTful сервис для отслеживания дневной нормы калорий пользователя и учета съеденных блюд.

## Функционал
- Регистрация пользователей с автоматическим расчетом дневной нормы калорий по формуле Харриса-Бенедикта.
- Добавление блюд с указанием калорийности, белков, жиров и углеводов.
- Фиксация приемов пищи с привязкой к пользователю.
- Генерация отчетов по потребленным калориям за день и проверка соответствия дневной норме.
- История приемов пищи.

## Технологии
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- JUnit, Mockito

## Установка и запуск
### 1. Клонирование репозитория
```sh
git clone https://github.com/VageGG/CalorieTracker.git
cd calorieTracker
```

### 2. Настройка базы данных
Создайте базу данных PostgreSQL и настройте подключение в `application.properties`:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/calorie_tracker
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Сборка и запуск
```sh
mvn clean install
mvn spring-boot:run
```

## Тестирование
```bash
mvn test
```

## API эндпоинты
### 1. Пользователи
- **Создание пользователя:** `POST /api/users`
- **Получение пользователя:** `GET /api/users/{id}`

### 2. Блюда
- **Добавление блюда:** `POST /api/dishes`
- **Список блюд:** `GET /api/dishes`

### 3. Прием пищи
- **Добавление приема пищи:** `POST /api/meals`
- **История приемов пищи:** `GET /api/meals/history/{userId}`

### 4. Отчеты
- **Калории за день:** `GET /api/reports/daily/{userId}`
- **Проверка нормы калорий:** `GET /api/reports/limit/{userId}`

## Тестирование API
Вы можете использовать Postman-коллекцию (находится в папке `postman/collection.json`) для тестирования API.

