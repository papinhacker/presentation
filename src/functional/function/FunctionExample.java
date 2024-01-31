package functional.function;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class FunctionExample {

    static String[] arrayStr = {"as", "a", "the", "d", "on", "and"};
    static int[] arrayInt = { 1, 3, 5, 9, 17, 33, 65};

    public static void main(String[] args) {

        // Основной абстрактный метод R apply(T t) принимает объект типа T и возвращает объект типа R.
        // Его задача: выполнить действие над объектом одного
        // типа и возвратить объект другого типа.
        // Создание объекта Function может выглядеть следующим образом:

        Function<String, Integer> fun1 = s -> s.length(); // String to Integer
        Function<Integer, String> fun2 = i -> Integer.toBinaryString(i); //int to String

        // Следующие два метода позволяют построить композицию из двух и более
        // функций, которые будут в итоге вызываться как одна.

        // default <V>  Function<V, R> compose(Function<? super V, ? extends T> before) —
        // возвращает составную функцию, которая сначала применяет функцию before к своему входу,
        // а затем применяет эту функцию к результату:
        Function<Integer, Integer> fun3 = fun1.compose(fun2);
        // или
        fun3 = fun1.compose(i -> Integer.toBinaryString(i));

        System.out.println(fun1.compose(fun2).apply(17));

        // default <V> Function<T,V> andThen(Function<? super R, ? extends V> after) —
        // возвращает составную функцию, которая сначала применяет эту
        // функцию к своему входу, а затем применяет функцию after к результату.
        // Метод andThen() вызовет функции в порядке, обратном методу compose().
        Function<String, String> fun4 = fun1.andThen(fun2);
        System.out.println(fun1.andThen(fun2).apply("java"));

        // Еще один метод static <T> Function<T, T> identity() — возвращает функцию, которая всегда возвращает свой входной аргумент.

        // Все интерфейсы пакета java.util.function, имеющие в своем названии слова
        // Function и Operator, на самом деле являются вариациями интерфейса Function<T, R>,
        // не наследуя его при этом.
        // Интерфейс UnaryOperator<T> объявляет метод T  apply(T t). Отличием
        // этого интерфейса от Function является только то, что и принимаемое, и возвращаемое значения метода T apply(T t)
        // должны быть одного и того же типа, что
        // в простом примере может соответствовать оператору инкремента:

        UnaryOperator<Integer> operator = i -> ++i;
        System.out.println(operator.apply(1)); // 2

        // Интерфейс BiFunction<T, U, R> объявляет метод R apply(T t, U u) с двумя
        // параметрами, что также представляется более широкой версией Function.

        BiFunction<Double, String, Integer> bi = (d, s) -> (Double.toString(d) + s).length();
        int length = bi.apply(1.23, "java");
        System.out.println(length); // 8

        // К еще более специализированным интерфейсам можно отнести:
        // ToIntFunction<T> — метод которого int applyAsInt(T t) принимает любой
        // тип данных, но должен возвращать значение типа int.

        // IntFunction<R> — наоборот, его метод R apply(int value) принимает значение типа int,
        // но может возвращать значение любого типа.

        // Интерфейс BinaryOperator<T, T> объявляет метод T apply(T t1, T t2), что
        // соответствует обычному бинарному оператору:
        BinaryOperator<String> binaryOperator = (s1, s2) -> s1 + s2.toUpperCase();
        System.out.println(binaryOperator.apply("oracle", "java"));

        // К интерфейсам группы Function также можно отнести интерфейс java.util.
        // Comparator<T> c его методом int compare(T o1, T o2). То есть это бинарная
        // фукция, принимающая два объекта одного типа и возвращающая значение типа int

        // Некоторые способы использования функциональных интерфейсов, аналагичных Function, методами интерфейса Stream:
        // reduce(BinaryOperator<T> accumulator);
        // sorted(Comparator<? super T> order);
        // max(Comparator<? super T> comparator);
        // min(Comparator<? super T> comparator);
        // map(Function<? super T,? extends R> mapper);
        // flatMap(Function<? super T,? extends Stream<? extends R>> mapper);
        // iterate(T seed, UnaryOperator<T> t);
        // mapToInt(ToIntFunction<? super T> mapper);
        // toArray(IntFunction<A[]> generator).
    }
}
