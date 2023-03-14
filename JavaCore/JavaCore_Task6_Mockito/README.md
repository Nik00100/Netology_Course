# Тестирование сервиса отправки сообщений

# Описание
В репозитории cервис отправки сообщений находится код приложения отправки локализованных сообщений, в котором на основе ip-адреса, переданного в заголовке, определяется язык отправляемого сообщения. ip-адрес начинающийся со "172." относится к российскому сегменту, а с "96." - к американскому. Для российских адресов отправляется текст на русском, а для американских адресов и всех остальных - на английском. Наша задача написать/добавить unit-тесты с использованием библиотеки mockito для проверки корректности работы функционала.

Что нужно сделать
Написать тесты для проверки языка отправляемого сообщения (класс MessageSender) с использованием Mockito
Поверить, что MessageSenderImpl всегда отправляет только русский текст, если ip относится к российскому сегменту адресов.
Поверить, что MessageSenderImpl всегда отправляет только английский текст, если ip относится к американскому сегменту адресов.
Написать тесты для проверки определения локации по ip (класс GeoServiceImpl)
Проверить работу метода public Location byIp(String ip)
Написать тесты для проверки возвращаемого текста (класс LocalizationServiceImpl)
Проверить работу метода public String locale(Country country)

# Реализация
Склонируйте удаленный репозиторий сервиса https://github.com/netology-code/geo-service или сделайте его fork (предпочтительно) или скачайте к себе в виде архива;
Подключите к maven-проекту зависимости junit и mockito (их нужно добавить в файл pom.xml);
Создайте класс для тестов в папке src/test/java (можете также создать подпапки в соответствии с package'ом класса, который вы будете тестировать);
Создайте тесты в соответствии с задачей (для сервиса MessageSenderImpl, нужно обязательно создать заглушки (mock) в виде GeoServiceImpl и LocalizationServiceImpl) минимум 4 unit теста;
Отправьте задачу на проверку.