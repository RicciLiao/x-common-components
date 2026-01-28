package ricciliao.x.component.payload;


import java.util.List;

public interface SimplePayloadData extends PayloadData {

    static <T extends PayloadData> Collection<T> of(List<T> data) {

        return new Collection<>(data);
    }

    static Bool of(boolean data) {

        return new Bool(data);
    }

    static Str of(String data) {

        return new Str(data);
    }

    static Blank blank() {

        return new Blank();
    }

    static FieldViolation of(String fieldName, String message) {

        return new FieldViolation(fieldName, message);
    }

    record Collection<T extends PayloadData>(List<T> data) implements SimplePayloadData {

        public static <T extends PayloadData> Collection<T> data(List<T> data) {

            return new Collection<>(data);
        }
    }

    record Bool(boolean data) implements SimplePayloadData {
    }

    record Str(String data) implements SimplePayloadData {
    }

    record Blank() implements SimplePayloadData {
    }

    record FieldViolation(String fieldName, String message) implements SimplePayloadData {
    }

}
