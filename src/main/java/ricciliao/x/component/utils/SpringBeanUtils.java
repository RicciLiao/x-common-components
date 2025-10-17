package ricciliao.x.component.utils;

import jakarta.annotation.Nonnull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

public class SpringBeanUtils {

    private static ApplicationContext applicationContext;

    private SpringBeanUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(@Nonnull Class<T> clazz) {
        check();

        return applicationContext.getBean(clazz);
    }

    public static Object getBean(@Nonnull String beanName) {
        check();

        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(@Nonnull String beanName, @Nonnull Class<T> clazz) {
        check();

        return applicationContext.getBean(beanName, clazz);
    }

    public static Set<Object> getBeansWithAnnotation(@Nonnull Class<? extends Annotation> annotationClazz,
                                                     @Nonnull Predicate<Class<?>> predicate) {
        check();

        Set<Object> beanSet = new HashSet<>();
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(annotationClazz);
        Set<Map.Entry<String, Object>> beanSetEntry = beanMap.entrySet();
        for (Map.Entry<String, Object> entry : beanSetEntry) {
            if (predicate.test(entry.getValue().getClass())) {
                beanSet.add(entry.getValue());
            }
        }

        return beanSet;
    }

    private static void check() {
        if (Objects.isNull(applicationContext)) {

            throw new IllegalStateException("SpringContextHolder is not ready to use!");
        }
    }

}
