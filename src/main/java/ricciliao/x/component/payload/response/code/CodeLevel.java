package ricciliao.x.component.payload.response.code;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

public interface CodeLevel extends Serializable {

    int getId();

    String getMessage();

    default CodeLevel format(Object... args) {
        if (this.getMessage() == null || !this.getMessage().contains("{}")) {

            return this;
        }

        int id = this.getId();
        String message = this.getMessage();

        return new CodeLevel() {
            @Serial
            private static final long serialVersionUID = -8435448001526187190L;

            @Override
            public int getId() {

                return id;
            }

            @Override
            public String getMessage() {

                return CodeLevel.format(message, args);
            }
        };
    }

    private static String format(String message, Object... args) {
        StringBuilder result = new StringBuilder(message.length() + args.length * 10);
        int argIndex = 0;
        int cursor = 0;
        int patternLength = message.length();

        while (cursor < patternLength) {
            if (cursor < patternLength - 1) {
                char current = message.charAt(cursor);
                char next = message.charAt(cursor + 1);
                if (escape(current, next)) {
                    result.append(next);
                    cursor += 2;
                } else if (placeholder(current, next)) {
                    if (argIndex < args.length) {
                        result.append(deep2String(args[argIndex]));
                        argIndex++;
                    } else {
                        result.append("{}");
                    }
                    cursor += 2;
                } else {
                    result.append(current);
                    cursor++;
                }
            } else {
                result.append(message.charAt(cursor));
                cursor++;
            }
        }

        return result.toString();
    }

    private static boolean escape(char current, char next) {

        return current == '\\' && (next == '{' || next == '}');
    }

    private static boolean placeholder(char current, char next) {

        return current == '{' && next == '}';
    }

    private static String deep2String(Object obj) {
        if (obj == null) {

            return "null";
        }
        if (obj.getClass().isArray()) {

            return array2String(obj);
        }
        if (obj instanceof Iterable) {

            return iterable2String((Iterable<?>) obj);
        }
        if (obj instanceof java.util.Map) {

            return map2String((java.util.Map<?, ?>) obj);
        }

        return obj.toString();
    }

    private static String array2String(Object array) {
        if (array instanceof Object[] objectArray) {

            return java.util.Arrays.deepToString(objectArray);
        } else if (array instanceof int[] intArray) {

            return java.util.Arrays.toString(intArray);
        } else if (array instanceof long[] longArray) {

            return java.util.Arrays.toString(longArray);
        } else if (array instanceof double[] doubleArray) {

            return java.util.Arrays.toString(doubleArray);
        } else if (array instanceof boolean[] booleanArray) {

            return java.util.Arrays.toString(booleanArray);
        } else if (array instanceof char[] charArray) {

            return java.util.Arrays.toString(charArray);
        } else if (array instanceof byte[] byteArray) {

            return java.util.Arrays.toString(byteArray);
        } else if (array instanceof float[] floatArray) {

            return java.util.Arrays.toString(floatArray);
        } else if (array instanceof short[] shortArray) {

            return java.util.Arrays.toString(shortArray);
        }
        return array.toString();
    }

    private static String iterable2String(Iterable<?> iterable) {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (Object item : iterable) {
            if (!first) sb.append(", ");
            sb.append(deep2String(item));
            first = false;
        }
        sb.append("]");

        return sb.toString();
    }

    private static String map2String(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (java.util.Map.Entry<?, ?> entry : map.entrySet()) {
            if (!first) sb.append(", ");
            sb.append(deep2String(entry.getKey()))
                    .append("=")
                    .append(deep2String(entry.getValue()));
            first = false;
        }
        sb.append("}");

        return sb.toString();
    }

}
