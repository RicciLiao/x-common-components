package ricciliao.x.component.payload;


import java.util.List;

public interface SimpleData extends PayloadData {

    static <T extends PayloadData> Collection<T> of(List<T> result) {

        return new Collection<>(result);
    }

    static Bool of(boolean result) {

        return new Bool(result);
    }

    static Str of(String result) {

        return new Str(result);
    }

    static Blank blank() {

        return new Blank();
    }

    static FieldViolation of(String fieldName, String message) {

        return new FieldViolation(fieldName, message);
    }

    record Collection<T extends PayloadData>(List<T> result) implements SimpleData {

        public static <T extends PayloadData> Collection<T> data(List<T> result) {

            return new Collection<>(result);
        }
    }

    record Bool(boolean result) implements SimpleData {
    }

    record Str(String result) implements SimpleData {
    }

    record Blank() implements SimpleData {
    }

    record FieldViolation(String fieldName, String message) implements SimpleData {
    }

}
