package functional.comparator;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorExample {

    public static void main(String[] args) {

        // Интерфейс java.util.Comparator<T>, начиная с версии Java 8, приобрел
        // свойства функционального интерфейса. Реализация интерфейса Comparator<T>
        // представляет возможность его использования для сортировки наборов объектов конкретного типа по правилам,
        // определенным для этого типа. Контракт интерфейса подразумевает реализацию метода int compare(T ob1, T ob2),
        // принимающего в качестве параметров два объекта, для которых должно быть
        // определено возвращаемое целое значение, знак которого и определяет правило сортировки.

        // !1.1

        // С помощью лямбда-выражения можно привести базовую реализацию компаратора для строк по убыванию их длин.
        Comparator<String> comparator = (s1, s2) -> s2.length() - s1.length();

        // Тогда сортировку массива строк по убыванию их длины можно провести способом:
        String str = "and java oracle the rose lion wolf hero green white red white";
        Arrays.stream(str.split("\\s"))
                .sorted(comparator)
                .forEach(s -> System.out.printf("%s ", s));

        // !1.2

        System.out.println();

        // Кроме уже перечисленных методов, интерфейс Comparator<T> объявляет
        // еще некоторые необходимые методы:

        // default Comparator<T> reversed() — примененный к уже созданному компаратору, делает сортировку в обратном направлении;

        // nullFirst(Comparator<? super T> comparator) и nullLast(Comparator<? super T> comparator) —
        // применяются к компаратору для размещения всех
        // null в начале или конце списка при сортировке;

        // thenComparing(Comparator<? super E> other), thenComparing( Function<? super T, ? extends U> keyExtractor) —
        // default-методы, позволяющие произвести сортировку в сортировке. Например,
        // все строки отсортировать по возрастанию длины и далее все строки с одинаковыми длинами отсортировать по алфавиту.
        Arrays.stream(str.split("\\s"))
                .sorted(Comparator.comparing(String::length)
                        .thenComparing(String::compareTo))
                .forEach(s -> System.out.printf("%s ", s));

        // Если в процессе использования необходимы сортировки по различным полям класса,
        // то реализацию компаратора можно вынести в отдельный класс.
        // Также реализация компаратора, являясь логической частью класса-сущности,
        // может быть его частью и представлена в виде статического внутреннего класса, была популярна до появления перечислений:
    }
}
