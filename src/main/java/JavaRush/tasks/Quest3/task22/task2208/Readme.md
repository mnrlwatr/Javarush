taskKey="JavaRush.tasks.Quest3.task22.task2208"

Формируем WHERE

Сформируй часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"


Требования:
1.	Метод getQuery должен принимать один параметр типа Map.
2.	Метод getQuery должен иметь тип возвращаемого значения String.
3.	Метод getQuery должен быть статическим.
4.	Метод getQuery должен возвращать строку сформированную по правилам описанным в условии задачи.


