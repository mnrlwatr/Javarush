taskKey="JavaRush.tasks.Quest3.task24.task2413.big20"

Арканоид(20)

Отличная работа! Я добавил пару методов, а также класс KeyboardObserver.

Немного отдохни перед новым уровнем и поиграй.

P.S. Только не забудь отрегулировать высоту консоли.


Требования:
1.	Расслабься и получай удовольствие.


Арканоид(19)

Осталось совсем чуть-чуть.

В классе Arkanoid создай поле приватное isGameOver типа boolean.

Реализуй метод checkBricksBump.
В этом методе надо проверить - не столкнулся ли шарик с каким-нибудь из "кирпичей".
Для проверки столкновения используй метод isIntersec.
Если шарик все-таки попал в кирпич, то:
а) шарик отлетает в случайном направлении:
double angle = Math.random() * 360;
ball.setDirection(angle);
б) кирпич умирает - надо удалить его из списка всех кирпичей.

Реализуй метод checkStandBump.
В этом методе надо проверить - не ударился ли шарик о подставку.
Для проверки столкновения используй метод isIntersec.
Если шарик все-таки ударился о подставку, то:
шарик отлетает в случайным направлении вверх:
double angle = 90 + 20 * (Math.random() - 0.5);
ball.setDirection(angel);

Реализуй метод checkEndGame.
Если координата y шарика больше чем высота поля игры (height), значит шарик улетел вниз за границу экрана.
В этом случае надо переменную isGameOver установить в true.



Арканоид(18)

Теперь вернемся к классу Arkanoid.

Реализуй методы:
а) move()
В этом методе нужно двигать все движимые объекты (stand, ball).
б) draw(Canvas canvas)
В этом методе надо вызвать метод draw всех существующих объектов, которые его имеют.



Арканоид(17)

Еще подставке нужны методы:
а) move - см. move в BaseObject
Движение доски осуществляется горизонтально, поэтому мы меняем только координату х.
Подумай, как координата х зависит от направления (direction) и скорости (speed). Реализуй зависимость.
б) draw - см. draw в BaseObject
Его кодом я займусь сам.
в) moveLeft() - задает постоянное движение "подставки" влево
Просто присвой правильное значение переменной direction.
г) moveRight() - задает постоянное движение "подставки" вправо
Просто присвой правильное значение переменной direction.



Арканоид(16)

И наконец "подставка"!
Ей понадобятся такие приватные поля:
а) speed (скорость шарика) типа double;
б) direction (направление движения по оси x: 1 - вправо, -1 - влево, 0 - начальное значение, стоим на месте) типа double.

Также создай для них геттеры.

А еще с тебя конструктор, примерно вот такой:
public Stand(double x, double y) {
super(x, y, 3);
speed = 1;
direction = 0;
}



Арканоид(15)

Не поверишь, но и это еще не все.

Во-первых нужен метод setDirection,
который не только устанавливает значение переменной direction,
но и вычисляет новые значения переменных dx и dy.

Код должен выглядеть примерно так:
this.direction = direction;
double angel = Math.toRadians(direction);
dx = Math.cos(angle) * speed;
dy = -Math.sin(angle) * speed.

Во-вторых шарик может удариться о стенку.
При этом он должен от нее отскочить.
Для этого нам понадобится еще один метод:
void checkRebound(int minx, int maxx, int miny, int maxy)
Создай его, а кодом я займусь сам.



