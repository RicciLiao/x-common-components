package ricciliao.x.component.utils;

import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class SpringBeanUtils {

    private SpringBeanUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return SpringBeanUtils.applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringBeanUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {

        return applicationContext.getBean(clazz);
    }

    public static Object getBean(String beanName) {

        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {

        return applicationContext.getBean(beanName, clazz);
    }

    public static Set<Object> getBeansWithAnnotation(Class<? extends Annotation> annotationClazz, Predicate<Class<?>> predicate) {
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

}
