package ricciliao.x.component.response.data;


import java.util.List;

public interface SimpleData extends ResponseData {

    record Collection<T extends ResponseData>(List<T> result) implements SimpleData {

        public static <T extends ResponseData> Collection<T> data(List<T> result) {

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
