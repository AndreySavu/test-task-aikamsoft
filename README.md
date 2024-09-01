# test-task-aikamsoft

### Инструкция по сборке и запуску
- собрать: mvn clean package
- создать БД из resources/dump_db.sql
- запуск с тестовыми данными:

  java -jar target/test-task-aikamsoft-jar-with-dependencies.jar search src\main\resources\input1.json src\main\resources\output.json

  java -jar target/test-task-aikamsoft-jar-with-dependencies.jar stat src\main\resources\input2.json src\main\resources\output.json