package stream;

public class Action {
    public static int sumCharCode(String str) {
        return str.codePoints().reduce(0, (v1, v2) -> v1 + v2);
    }
}
