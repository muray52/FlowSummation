Flow-сумматор. 

Была решена задача:

Разработать Flow, который суммирует значения других Flow.
1. UI - на экране должно быть 3 элемента. Поле ввода, кнопка запуска и текстовое поле для вывода информации. При нажатии на кнопку получаем N - число введенное в поле ввода и запускаем Flow. Результат работы нужно вывести в текстовое поле. Каждое обновление должно находиться на новой строчке.
2. Flow. Необходимо создать массив Flow<Int>, количества N, каждый из которых после задержки в (index + 1) * 100, эмитит значение index + 1. Т.е. Flow с индексом 0 с задержкой 100 эмитит значение 1, Flow с индексом 1 с задержкой 200 эмитит значение 2.
3. Суммирование. Результирующий Flow должен суммировать значения всех N Flow. Суммирующий Flow должен возвращать значение после обновления каждого из N Flow.
Важно: Значение на экране должно добавляться каждые 100мс.

Пример:


Введенное количество: 1

Результат: 1


Введенное количество: 2

Результат: 1 3


Введенное количество: 3

Результат: 1 3 6


Введенное количество: 7

Результат: 1 3 6 10 15 21 28

![image](https://github.com/muray52/FlowSummation/assets/5577221/4bce54e8-76f1-4cb9-94a9-d9e987aaffb5)
