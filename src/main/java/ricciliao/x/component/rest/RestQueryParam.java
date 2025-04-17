package ricciliao.x.component.rest;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

public interface RestQueryParam extends Serializable {

    default MultiValueMap<String, String> toQueryParams() {
        MultiValueMap<String, String> result = new LinkedMultiValueMap<>();
        for (Field declaredField : this.getClass().getDeclaredFields()) {
            ReflectionUtils.makeAccessible(declaredField);
            try {
                if (Collection.class.isAssignableFrom(declaredField.getType())) {
                    Collection<?> collection = (Collection<?>) declaredField.get(this);
                    for (Object object : collection) {
                        result.add(declaredField.getName(), object.toString());
                    }
                } else if (declaredField.getType().isArray()) {
                    Object array = declaredField.get(this);
                    int length = Array.getLength(array);
                    for (int i = 0; i < length; i++) {
                        Object element = Array.get(array, i);
                        result.add(declaredField.getName(), element.toString());
                    }
                } else {
                    result.add(declaredField.getName(), declaredField.get(this).toString());
                }
            } catch (Exception e) {
                // TODO
            }
        }

        return result;
    }

}
