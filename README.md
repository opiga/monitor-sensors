# Monitor Sensors

## Описание

Приложение для мониторинга сенсоров с реализацией полного CRUD-функционала.

## Реализовано

-  CRUD-приложение `Monitor Sensors`:
    - Создание, чтение, обновление и удаление данных сенсоров
-  Миграции БД:
    - Инициализация и управление схемой базы данных с помощью миграций
-  Docker:
    - Возможность поднятия приложения в Docker-контейнере с помощью `Dockerfile` и `docker-compose.yml`
-  Безопасность:
   - Настроена аутентификация и авторизация с использованием **Spring Security**
      - `admin` — роль `Administrator`
      - `user` — роль `Viewer`