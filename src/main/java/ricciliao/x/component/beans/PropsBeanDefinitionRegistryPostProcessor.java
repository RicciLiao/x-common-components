package ricciliao.x.component.beans;

import jakarta.annotation.Nonnull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import ricciliao.x.component.props.ApplicationProperties;
import ricciliao.x.component.utils.CoreUtils;

public abstract class PropsBeanDefinitionRegistryPostProcessor<T extends ApplicationProperties> implements BeanDefinitionRegistryPostProcessor {

    private final T props;

    protected PropsBeanDefinitionRegistryPostProcessor(@Nonnull Class<T> propsClass) {
        this.props = CoreUtils.convert2Properties(propsClass);
    }

    public T getProps() {
        return props;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(@Nonnull BeanDefinitionRegistry registry) throws BeansException {
    }

}
