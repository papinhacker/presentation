package functional.main;

public class RectangleService implements ShapeService{

    // обычная запись
    @Override
    public double perimeter(double a, double b) {
        return 2 * (a + b);
    }

    // В виде лямбда реализация выглядит так:
    ShapeService rectangleService = (a, b) -> 2 * (a + b);

    // На самом деле лямбда-выражение представляет сокращенную запись анонимного класса.
    ShapeService rectangleService1 = new ShapeService() {
        @Override
        public double perimeter(double a, double b) {
            return 2 * (a + b);
        }
    };

    // Эволюция в лямбда начинается с того, что опускается конструктор анонимного класса и имя метода интерфейса.
    // Так как метод единственный в интерфейсе, то и его имя можно не упоминать. Параметры метода отделяются от
    // тела метода оператором «стрелка»:
    ShapeService rectangleService2 = (double a, double b) -> {
        return 2 * (a + b);
    };

    // Если тело метода состоит из одного оператора, то и фигурные скобки можно
    // опустить.
    ShapeService rectangleService3 = (double a, double b) -> 2 * (a + b);

    // Типы параметров метода также можно опустить, так как предполагается,
    // что они известны из сигнатуры единственного абстрактного метода.
    ShapeService rectangleService4 = (a, b) -> 2 * (a + b);
}
