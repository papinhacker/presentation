package functional.supplier;

import java.util.function.Supplier;

public class SupplierExample {

    public static void main(String[] args) {

        // Интерфейс Supplier<T> возвращает новый объект типа T методом T get().
        // Предназначен для создания новых объектов.
        // Метод get() единственный в интерфейсе. Аналогичные интерфейсы предназначены для создания значений базовых типов:

        // BooleanSupplier и его метод boolean getAsBoolean();
        // DoubleSupplier и его метод double getAsDouble();
        // IntSupplier и его метод int getAsInt();
        // LongSupplier и его метод long getAsLong().
        Supplier<StringBuilder> supplier = () -> new StringBuilder("java");
        StringBuilder builder = supplier.get();
        Supplier<int[]> supplier2 = () -> new int[10];
        int[] arr = supplier2.get();

        // Использование Supplier методами интерфейса Stream:
        // generate(Supplier<? extends T> s);
        // collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner).
    }
}
