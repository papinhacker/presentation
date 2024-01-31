package functional;

import java.util.Comparator;
import java.util.function.Supplier;

public class References {

    public static void main(String[] args) {

        // Оператор видимости «::» отделяет метод от объекта или класса. Существуют три варианта записи:
        //• ContainingClass::staticMethodName — ссылка на статический метод;
        //• ContainingObject::instanceMethodName — ссылка на нестатический метод конкретного объекта;
        //• ContainingType::methodName — ссылка на нестатический метод любого объекта конкретного типа.
        // Первые два варианта эквивалентны лямбда-выражению с параметрами методами.
        // В третьем варианте первый параметр становится целевым для метода, например:
        Comparator<Long> comparator = (l1, l2) -> l1.compareTo(l2);
        comparator = Long::compareTo;

        // В качестве объекта можно использовать ссылки this и super.
        // Кроме ссылки на метод, существуют также и ссылки на конструктор, где
        // в качестве имени метода указывается оператор new.
        Supplier<StringBuilder> supplier1 = StringBuilder::new;
    }
}
