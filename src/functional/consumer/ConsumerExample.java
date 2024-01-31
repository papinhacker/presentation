package functional.consumer;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;

public class ConsumerExample {

    public static void main(String[] args) {

        // Интерфейс Consumer<T> представляет абстрактный метод void accept(T t),
        // функция, принимающая объект типа T и выполняющая над ним некоторое действие.
        // Результат действия можно сохранить во внешнем объекте, например,
        // коллекции или вывести в поток вывода, например, в файл или на консоль.

        // В следующем примере consumer преобразует строку на основе разделителя в массив строк:
        String str = "as a- the-d -on and";
        String regex = "-";
        Consumer<String> consumer = s -> System.out.println(Arrays.toString(s.split(regex)));
        consumer.accept(str);

        // Еще один пример на Consumer с изменением поля объекта из массива объектов класса RightTriangle
        RightTriangle[] triangles = {new RightTriangle(1, 2), new RightTriangle(3, 4)};
        Arrays.stream(triangles)
                .forEach(t -> t.setSideA(t.getSideA() * 10));
        System.out.println(Arrays.toString(triangles));

        // Вспомогательный метод default Consumer<T> andThen(Consumer<? super T>after)
        // позволяет построить композицию из двух и более действий как одного целого:
        String regex1 = "\\s";
        Consumer<String> consumer1 = s -> Arrays.toString(s.split(regex1));
        String regex2 ="a";
        Consumer<String> consumer2 = consumer1
                .andThen(s -> System.out.println(Arrays.toString(s.split(regex2))));
        consumer2.accept(str);

        // Интерфейс BiConsumer<T, U> объявляет метод void accept(T t, U u) с двумя параметрами,
        // что представляется как расширение возможностей Consumer.
        // Применение этого метода позволит передавать множитель стороны треугольника в метод accept().
        RightTriangle triangle = new RightTriangle(1, 2);
        BiConsumer<RightTriangle, Integer> consumer3 = (t, n) -> t.setSideA(t.getSideA()*n);
        consumer3.accept(triangle, 50);
        System.out.println(triangle);

        // Более специализированный родственный интерфейс IntConsumer объявляет метод void accept(int value)
        // для действий над целым числом. Аналогичные
        // методы содержат интерфейсы DoubleConsumer, LongConsumer.
        // Родственный интерфейсу BiConsumer интерфейс ObjIntConsumer<T>
        // объявляет метод void accept(T t, int value). С его помощью предыдущий пример переписывается несколько проще:
        ObjIntConsumer<RightTriangle> consumer4 = (t, n) -> t.setSideA(t.getSideA() * n);

        // Аналогичные методы содержат интерфейсы ObjDoubleConsumer, ObjLongConsumer

        // Некоторые способы использования Consumer методами интерфейса Stream:
        // forEach(Consumer<? super T> action);
        // forEachOrdered(Consumer<? super T> action);
        // peek(Consumer<? super T> action).
    }
}
