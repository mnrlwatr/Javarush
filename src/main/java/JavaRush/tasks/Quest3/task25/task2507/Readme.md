taskKey="JavaRush.tasks.Quest3.task25.task2507"

Работать в поте лица!

Реализуй логику метода interrupt, который должен прерывать трэд предварительно закрыв используемые ресурсы.
Используй метод super-класса в блоке finally.


Требования:
1.	Сигнатуру метода interrupt менять нельзя.
2.	Метод interrupt должен закрывать используемые классом Solution ресурсы.
3.	Затем, метод interrupt должен прерывать трэд (вызов super.interrupt).
4.	Трэд должен быть прерван в любом случае, даже если во время закрытия ресурсов было выкинуто исключение.

