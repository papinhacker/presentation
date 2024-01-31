package functional.predicate;

import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateExample {

    static String[] arrayStr = {"as", "a", "the", " ", "d", "on", "and", ""};
    static int[] arrayInt = {1, 3, 5, 9, 17, 33, 65};

    public static void main(String[] args) {
        Predicate<String> predicateStr1 = s -> s.length() < 2;
        Predicate<String> predicateStr2 = String::isBlank; // о разных видаъ записи поговорим позже
        Predicate<Integer> predicateInt = i -> i >= 9;

        // Вызов этих функций довольно примитивен и опять же на практике в явном
        // виде никогда не используется:
        System.out.println(predicateStr1.test("java")); // false
        System.out.println(predicateStr2.test(" ")); // true
        System.out.println(predicateInt.test(16)); // true

        /* Predicate default methods */

        // Композиции предикатов можно строить, используя методы: default
        // Predicate<T> and(Predicate<? super T> other) — логическое «и».
        Predicate<String> predicate1 = s -> s.contains("a");
        System.out.println(Arrays.stream(arrayStr)
                .filter(predicate1.and(s -> s.contains("n")))
                .collect(Collectors.toList()));

        // Или, что тоже самое, но без использования ссылки на функцию predicate1:
        System.out.println(Arrays.stream(arrayStr)
                .filter(((Predicate<String>) s -> s.contains("a")).and(s -> s.contains("n")))
                .collect(Collectors.toList()));

        // default Predicate<T> or(Predicate<? super T> other) — логическое «или».
        System.out.println(Arrays.stream(arrayInt)
                .filter(((IntPredicate) i -> i > 32).or(i -> i < 4))
                .boxed()
                .collect(Collectors.toList()));
        // Для упрощения работы с потоками чисел разработаны предикаты IntPredicate,
        // DoublePredicate, LongPredicate с практически идентичным набором методов
        // построения логических выражений.

        // default Predicate<T> negate() — логическое отрицание предиката.
        System.out.println(Arrays.stream(arrayStr)
                .filter(((Predicate<String>)s -> s.contains("and")).negate())
                .collect(Collectors.toList()));

        // static Predicate<T> not(Predicate<? super T> target) — более короткий вариант логического отрицания.
        System.out.println(Arrays.stream(arrayStr)
                .filter(Predicate.not(s -> s.contains("and")))
                .collect(Collectors.toList()));

        // static Predicate<T> isEqual(Object targetRef) — возвращает предикат эквивалент метода equals() класса Object.
        // Применяется для поиска точного совпадения объектов:
        System.out.println(Arrays.stream(arrayStr)
                .filter(Predicate.isEqual("and"))
                .collect(Collectors.toList()));

        // В пакет java.util.function объявлен еще один интерфейс-предикат BiPredicate<T, U>
        // c абстрактным методом boolean test(T t, U u).
        BiPredicate<String, Integer> biPredicate = (s, max) -> s.length() <= max;
        System.out.println(biPredicate.test("java", 7)); // true

        // Некоторые способы использования предикатов методами интерфейса Stream:
        // filter(Predicate<? super T> predicate);
        // remove(Predicate<? super E> filter);
        // allMatch(Predicate<? super T> predicate);
        // noneMatch(Predicate<? super T> predicate);
        // anyMatch(Predicate<? super T> predicate);
        // takeWhile(Predicate<? super T> predicate);
        // iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next).
    }
}
