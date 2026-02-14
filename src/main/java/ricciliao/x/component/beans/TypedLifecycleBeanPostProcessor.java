package ricciliao.x.component.beans;

import jakarta.annotation.Nonnull;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * Ensure the method is a static method when you use a @Bean factory method to register {@link BeanPostProcessor}, because it can prevent the @Configuration class too early to be initialized,
 * otherwise springframe will pop up this warning message: "is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying). Is this bean getting eagerly injected into a currently created BeanPostProcessor."
 * <p>
 * IF need to inject any other beans, please ensure that use {@link ObjectProvider} to inject.
 */
public abstract class TypedLifecycleBeanPostProcessor<T> implements InstantiationAwareBeanPostProcessor {

    public abstract boolean supports();

    public abstract Class<T> getBeanType();

    /**
     * @param beanClass May be used for further computation in overriding classes
     * @param beanName  May be used for further computation in overriding classes
     */
    public T beforeInstantiation(@Nonnull Class<T> beanClass, @Nonnull String beanName) {

        return null;
    }

    /**
     * @param bean     May be used for further computation in overriding classes
     * @param beanName May be used for further computation in overriding classes
     */
    public boolean afterInstantiation(@Nonnull T bean, @Nonnull String beanName) {

        return true;
    }

    /**
     * @param bean     May be used for further computation in overriding classes
     * @param beanName May be used for further computation in overriding classes
     */
    public PropertyValues properties(@Nonnull PropertyValues pvs, @Nonnull T bean, @Nonnull String beanName) {

        return pvs;
    }

    /**
     * @param beanName May be used for further computation in overriding classes
     */
    public T beforeInitialization(@Nonnull T bean, @Nonnull String beanName) {

        return bean;
    }

    /**
     * @param bean     May be used for further computation in overriding classes
     * @param beanName May be used for further computation in overriding classes
     */
    public T afterInitialization(@Nonnull T bean, @Nonnull String beanName) {

        return bean;
    }

    @Override
    public final Object postProcessBeforeInstantiation(@Nonnull Class<?> beanClass, @Nonnull String beanName) throws BeansException {
        if (this.getBeanType().equals(beanClass) && this.supports()) {

            return this.beforeInstantiation(this.cast(beanClass), beanName);
        }

        return null;
    }

    @Override
    public final boolean postProcessAfterInstantiation(@Nonnull Object bean, @Nonnull String beanName) {
        if (this.getBeanType().isInstance(bean) && this.supports()) {

            return this.afterInstantiation(this.cast(bean), beanName);
        }

        return true;
    }

    @Override
    public final PropertyValues postProcessProperties(@Nonnull PropertyValues pvs, @Nonnull Object bean, @Nonnull String beanName) {
        if (this.getBeanType().isInstance(bean) && this.supports()) {

            return this.properties(pvs, this.cast(bean), beanName);
        }

        return pvs;
    }

    @Override
    public final Object postProcessBeforeInitialization(@Nonnull Object bean, @Nonnull String beanName) {
        if (this.getBeanType().isInstance(bean) && this.supports()) {

            return this.beforeInitialization(this.cast(bean), beanName);
        }

        return bean;
    }

    @Override
    public final Object postProcessAfterInitialization(@Nonnull Object bean, @Nonnull String beanName) {
        if (this.getBeanType().isInstance(bean) && this.supports()) {

            return this.afterInitialization(this.cast(bean), beanName);
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

}
