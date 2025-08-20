package ricciliao.x.component.context;

import jakarta.annotation.Nonnull;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

public final class TypedLifecycleBeanPostProcessor<T> implements InstantiationAwareBeanPostProcessor {

    private final LifecycleProcessor<T> processor;

    private TypedLifecycleBeanPostProcessor(LifecycleProcessor<T> processor) {
        this.processor = processor;
    }

    public static <T> TypedLifecycleBeanPostProcessor<T> processor(LifecycleProcessor<T> processor) {

        return new TypedLifecycleBeanPostProcessor<>(processor);
    }

    @Override
    public Object postProcessBeforeInstantiation(@Nonnull Class<?> beanClass, @Nonnull String beanName) throws BeansException {
        if (processor.getBeanType().equals(beanClass) && processor.supports()) {

            return processor.beforeInstantiation(this.cast(beanClass), beanName);
        }

        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(@Nonnull Object bean, @Nonnull String beanName) {
        if (processor.getBeanType().isInstance(bean) && processor.supports()) {

            return processor.afterInstantiation(this.cast(bean), beanName);
        }

        return true;
    }

    @Override
    public PropertyValues postProcessProperties(@Nonnull PropertyValues pvs, @Nonnull Object bean, @Nonnull String beanName) {
        if (processor.getBeanType().isInstance(bean) && processor.supports()) {

            return processor.properties(pvs, this.cast(bean), beanName);
        }

        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(@Nonnull Object bean, @Nonnull String beanName) {
        if (processor.getBeanType().isInstance(bean) && processor.supports()) {

            return processor.beforeInitialization(this.cast(bean), beanName);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(@Nonnull Object bean, @Nonnull String beanName) {
        if (processor.getBeanType().isInstance(bean) && processor.supports()) {

            return processor.afterInitialization(this.cast(bean), beanName);
        }

        return bean;
    }

    @SuppressWarnings("unchecked")
    private Class<T> cast(@Nonnull Class<?> beanClass) {

        return (Class<T>) beanClass;
    }

    @SuppressWarnings("unchecked")
    private T cast(@Nonnull Object bean) {

        return (T) bean;
    }

    public interface LifecycleProcessor<T> {

        boolean supports();

        Class<T> getBeanType();

        default T beforeInstantiation(@Nonnull Class<T> bean, @Nonnull String beanName) {

            return null;
        }

        default boolean afterInstantiation(@Nonnull T bean, @Nonnull String beanName) {

            return true;
        }

        default PropertyValues properties(@Nonnull PropertyValues pvs, @Nonnull T bean, @Nonnull String beanName) {

            return pvs;
        }

        default T beforeInitialization(@Nonnull T bean, @Nonnull String beanName) {

            return bean;
        }

        default T afterInitialization(@Nonnull T bean, @Nonnull String beanName) {

            return bean;
        }

    }

}
