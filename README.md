# Registration Tests
Этот проект содержит автоматизированные тесты для проверки формы регистрации на сайте http://tl.af-ctf.ru. Включает UI-тесты (на основе Selenide) и API-тесты (на основе RestAssured), написанные на Java 11 с использованием Maven, TestNG и Allure для отчетности.

## Требования
Java: 17

Maven: 3.6+

WebDriver: ChromeDriver (для UI-тестов, должен соответствовать версии Chrome на вашей машине)

## Запуск всех тестов через testng.xml
```
mvn allure:serve
mvn allure:report - только отчет
```
