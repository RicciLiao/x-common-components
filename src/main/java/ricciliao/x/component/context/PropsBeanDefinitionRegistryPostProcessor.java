package ricciliao.x.component.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.NonNull;
import ricciliao.x.component.props.YamlPropertiesReader;

public abstract class PropsBeanDefinitionRegistryPostProcessor<T> implements BeanDefinitionRegistryPostProcessor {

    protected PropsBeanDefinitionRegistryPostProcessor(Class<T> propsClass) {
        ConfigurationProperties propsPrefix = propsClass.getAnnotation(ConfigurationProperties.class);
        this.props = new YamlPropertiesReader().getProperty(propsPrefix.value(), propsClass);
    }

    private final T props;

    public T getProps() {
        return props;
    }

    @Override
    public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinitionRegistryPostProcessor.super.postProcessBeanFactory(beanFactory);
    }

    @Override
    public abstract void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry registry) throws BeansException;

}
