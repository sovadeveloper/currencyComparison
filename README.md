# Тестовое задание

### Описнание (ТЗ)

Создать сервис, который обращается к сервису курсов валют, и отображает gif:
если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich,
если ниже - отсюда https://giphy.com/search/broke

## Установка

1) Скоприровать репозиторий

        git clone https://github.com/sovadeveloper/currencyComparison.git
    
2) Перейти в него

        cd currencyComparison
    
## Запуск с помощью Gradle

Запуск с помощью Gradle:

        ./gradlew bootRun
    
## Запуск с помощью Docker

1 вариант: Dockerfile:
1) Необходимо собрать проект

        ./gradlew bootJar 
        
    или
    
        ./gradlew build
    
2) После чего сбилдить и поднять докер

        docker build .
    
        docker run -p 8080-8080 (image)
    
2 вариант: docker-compose:

        docker-compose up
    
## Эндпоинты (маппинги)

        /api/exchanges/compare/{symbol}

где {symbol} - там указываем валюту для сравнения с USD, например RUB.

Данный эндпоинты возваращает JSON со ссылкой на гифку
