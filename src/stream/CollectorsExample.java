package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectorsExample {

    public static void main(String[] args) {
        List<String> strings = List.of("as a the d on and".split("\\s+"));

        // Методы класса java.util.stream.Collectors представляют основные возможности, позволяя произвести обработку stream к результату,
        // будь то число, строка или коллекция. Каждый статический метод класса создает экземпляр Collector
        // для передачи методу collect() интерфейса Stream,
        // который и выполнит действия по преобразованию stream алгоритмом, содержащимся в экземпляре Collector.

        // toCollection(Supplier<C> collectionFactory), toList(), toSet() — преобразование в коллекцию;
        List<Integer> listLengths = strings.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(listLengths);

        // Преобразование в список с применением ссылки на конструктор:
        List<Character> listFirstSymb = strings.stream()
                .map(s -> s.charAt(0))
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(listFirstSymb);

        // joining(CharSequence delimiter) — обеспечивает конкатенацию строк с заданным разделителем;
        String result = strings.stream()
                .map(String::toUpperCase).collect(Collectors.joining(" : "));
        System.out.println(result);

        // mapping(Function<? super T,? extends U> mapper,Collector<? super U,A,R> downstream) —
        // позволяет преобразовать элементы одного типа в элементы другого типа;
        List<Integer> listCode = strings.stream()
                .collect(Collectors.mapping(s -> (int) s.charAt(0), Collectors.toList()));
        System.out.println(listCode);

        // minBy(Comparator<? super T> c)/maxBy(Comparator<? super T> c) —
        // коллектор для нахождения минимального или максимального элемента в потоке;
        String minLex = strings.stream()
                .collect(Collectors.minBy(String::compareTo)).orElse("none");
        System.out.println(minLex);

        // filtering(Predicate<? super T>  predicate, Collector<? super T,A,R> downstream) —
        // выполняет фильтрацию элементов;
        List<String> lists = strings.stream()
                .collect(Collectors.filtering(s -> s.length() > 1, Collectors.toList()));
        System.out.println(lists);

        // counting() — позволяет посчитать количество элементов потока;
        long counter = strings.stream()
                .collect(Collectors.counting());
        System.out.println(counter);

        // summingInt(ToIntFunction<? super T> mapper) — выполняет суммирование элементов. Существуют версии для Long и Double;
        int length = strings.stream()
                .collect(Collectors.summingInt(String::length));
        System.out.println(length);

        // averagingInt(ToIntFunction<? super T> mapper)  — вычисляет среднее
        // арифметическое элементов потока. Существуют версии для Long и Double;
        Double averageLength = strings.stream()
                .collect(Collectors.averagingDouble(String::length));
        System.out.println(averageLength);

        // reducing(T identity, BinaryOperator<T> op) — коллектор,
        // осуществляющий редукцию (сведение) элементов на основании заданного бинарного оператора;
        // compute sum of code first chars each strings
        int sumCodeFirstChars = strings.stream()
                .map(s -> (int)s.charAt(0))
                .collect(Collectors.reducing(0, (a, b) -> a + b));
        System.out.println(sumCodeFirstChars);

        // groupingBy(Function<? super T, ? extends K> classifier) — коллектор группировки элементов потока;
        Map<Integer, List<String>> byLength = strings.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(byLength);

        // partitioningBy(Predicate<? super T> predicate) — коллектор разбиения элементов потока;

        // partition strings into length less than 2 and all the rest
        Map<Boolean, List<String>> boolLength = strings.stream()
                .collect(Collectors.partitioningBy(s -> s.length() < 2));
        System.out.println(boolLength);

        // parallel streams and streams flags???

    }
}
