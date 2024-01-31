package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {

    public static void main(String[] args) {

        List<String> strings = List.of("as", "a", "the", "d", "on", "and");

        // Интерфейс java.util.stream.Stream<T> — поток объектов для преобразования коллекций, массивов.
        // В потоке не хранятся элементы операции, он не модифицирует источник, а формирует в ответ на действие новый поток.
        // Операции в потоке не выполняются до момента, пока не потребуется получить конечный результат выполнения.

        // Stream нельзя воспринимать как просто поток ввода\вывода.
        // Этот поток создается на основе коллекции\массива, элементы которой переходят в состояние информационного ожидания действия,
        // переводящего поток в следующее состояние до достижения терминальной цели, после чего поток прекращает
        // свое существование

        // Невозможно на одном и том же состоянии объекта stream вызвать метод его
        // обработки повторно:
//        Stream<String> stream = strings.stream();
//        stream.findFirst();
//        stream.filter(String::isBlank).findAny();

        // Некоторые промежуточные методы преобразования потоков объектов:
        // filter(Predicate<? super T> predicate) — выбор элементов из потока на основании работы предиката в новый поток.
        // Отбрасываются все элементы, не удовлетворяющие условию предиката:
        strings.stream()
                .filter(s -> s.length() < 2)
                .forEach(s -> System.out.print(s + " "));

        // map(Function<? super T, ? extends R> mapper) — изменение всех элементов
        // потока, применяя к каждому элементу функцию mapper. Тип параметризации потока может изменяться,
        // если типизация T и R относится к различным классам:
        strings.stream()
                .map(s -> s.length())
                .forEach(s -> System.out.print(s + " "));

        strings.stream()
                .map(String::toUpperCase)
                .forEach(s -> System.out.print(s + " "));

        // flatMap(Function<T, Stream<R>> mapper) — преобразовывает один объект,
        // как правило составной, в объект более простой структуры, например, массив
        // в строку, список в объект, список списков в один список:
        OrderType type1 = new OrderType(771);
        type1.add("SEK");
        type1.add("DKK");
        type1.add("NOK");
        type1.add("EUR");
        OrderType type2 = new OrderType(779);
        type2.add("SEK");
        type2.add("PLN");
        type2.add("CZK");
        type2.add("EUR");
        List<OrderType> orderTypes = List.of(type1, type2);
        List<String> currencyList =
                orderTypes.stream()
                        .map(o -> o.getCurrencyNames()) // > Stream<List<String>>
                        .flatMap(o -> o.stream()) // > Stream<String>
                        .collect(Collectors.toList());
        System.out.println(currencyList);

        // peek(Consumer<T> consumer) — возвращает поток, содержащий все элементы исходного потока.
        // Используется для просмотра элементов в текущем
        // состоянии потока. Можно использовать для записи логов:
        System.out.println("11111");
        strings.stream()
                .map(String::length)
                .peek(s -> System.out.print(s + "-"))
                .map(n -> n + 100)
//                .forEach()
                .sorted()
                .forEach(s -> System.out.print(s + " "));
        System.out.println("\n11111");

        // sorted(Comparator<T> comparator) и sort() — сортировка в новый поток:
        // Сортировка с использованием собственного компаратора класса String:
        strings.stream()
                .sorted()
                .forEach(s -> System.out.print(s + " "));

        // Сортировка с использованием заданного компаратора, сортирующего по длине строк:
        strings.stream()
                .sorted(Comparator.comparingInt(String::length)) // identically
                .forEach(s -> System.out.print(s + " "));

        // limit(long maxSize) — ограничивает выходящий поток заданным в параметре значением;
        strings.stream()
                .limit(3)
                .forEach(s -> System.out.print(s + " "));

        // skip(long n) — не включает в выходной поток первые n элементов исходного потока;
        strings.stream()
                .skip(4)
                .forEach(s -> System.out.print(s + " "));

        // distinct() — удаляет из потока все идентичные элементы;
        List<String> stringsDouble = List.of("the and the and the and".split("\\s+"));
        stringsDouble.stream()
                .distinct()
                .forEach(s -> System.out.print(s + " "));

        // Некоторые терминальные методы сведения потока к результату. Результатом
        // может быть новая коллекция, объект некоторого класса, число. Промежуточные
        // операции обязательно должны завершаться терминальными, иначе они не выполнятся, так как просто не имеют смысла.

        // void forEach(Consumer<T> action) — выполняет действие над каждым элементом потока.
        // Чтобы результат действия сохранялся, реализация action должна
        // предусматривать фиксацию результата в каком-либо объекте или потоке вывода;

        // Optional<T> findFirst() — находит первый элемент в потоке;
        String firstStr = strings.stream()
                .filter(s -> s.matches("a\\w*"))
                .findFirst()
                .orElse("none");
        System.out.println(firstStr);

        // Optional<T> findAny() — находит элемент в потоке;
        String anyStr = strings.stream()
                .filter(s -> s.matches("an[a-z]"))
                .findAny()
                .orElse("none");
        System.out.println(anyStr);

        // long count() — возвращает число элементов потока;
        long count = strings.stream()
                .filter(s -> s.matches("a\\w*"))
                .count();
        System.out.println(count);

        // boolean anyMatch(Predicate<T> predicate) — возвращает истину, если
        // хотя бы один элемент stream удовлетворяет условию предиката;
        boolean res2 = strings.stream()
                .anyMatch(s -> s.startsWith("a")); // true

        // boolean noneMatch(Predicate<T> predicate) — возвращает истину, если ни
        // один элемент stream не удовлетворяет условию предиката;
        boolean res3 = strings.stream()
                .noneMatch(s -> s.endsWith("z")); // true

        // reduce(T identity, BinaryOperator accumulator) — преобразовывает все элементы стрима
        // в один объект(посчитать сумму всех элементов, либо найти минимальный элемент),
        // cперва берётся объект identity и первый элемент стрима,
        // применяется функция accumulator и identity становится её результатом. Затем всё продолжается для остальных элементов.
        System.out.println("1!!!!!");
        int sumLength = strings.stream()
                .map(s -> s.length())
                .reduce(0, (n1, n2) -> n1 + n2);
        strings.stream().reduce("start ", (s1, s2) -> s1 + s2);
        int sum = Stream.of(1, 2, 3, 4, 5).reduce(10, (acc, x) -> acc + x);
        System.out.println(strings.stream().reduce("start ", (s1, s2) -> s1 + " " + s2));
        System.out.println("1!!!!!");

        // <R, A> R collect(Collector<? super T, A, R> collector) — собирает элементы
        // в коллекцию или объект другого типа;
        Map<String, Integer> map = Arrays.stream("as a the d on and".split("\\s+"))
                .collect(Collectors.toMap(s -> s, s -> s.length()));

        // Optional<T> min(Comparator<T> comparator) — находит минимальный элемент;
        String min = strings.stream()
                .min(Comparator.comparingInt(s -> s.charAt(s.length() - 1)))
                .orElse("none");
        System.out.println(min);

        // Optional<T> max(Comparator<T> comparator) — находит максимальный элемент.
        String max = strings.stream()
                .max(Comparator.comparingInt(Action::sumCharCode))
                .orElse("empty");
        System.out.println(max);

        // Источники Stream API:
        // collection.stream(),
        // Arrays.stream(int[] array),
        // Files.walk(Path path),
        // Files.list(Path path),
        // bufferedReader.lines(),
        // Stream.iterate(T seed, UnaryOperator<T> f),
        // Stream.generate(Supplier<? extends T> s),
        // Stream.of(T...t),
        // Stream.ofNullable(T t),
        // Stream.empty(),
        // Stream.concat(Stream<? extends T> a, Stream<? extends T> b),
        // string.lines(),
        // string.codePoints(),
        // string.chars(),
        // random.ints(),
        // random.doubles(),
        // random.longs() и другие

        /**
         * Алгоритмы сведения Collectors
         */



    }
}
