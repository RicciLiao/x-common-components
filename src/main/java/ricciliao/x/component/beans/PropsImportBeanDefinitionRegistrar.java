package ricciliao.x.component.beans;

import jakarta.annotation.Nonnull;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import ricciliao.x.component.props.ApplicationProperties;
import ricciliao.x.component.utils.CoreUtils;

public abstract class PropsImportBeanDefinitionRegistrar<T extends ApplicationProperties> implements ImportBeanDefinitionRegistrar {

    private final T props;

    protected PropsImportBeanDefinitionRegistrar(@Nonnull Class<T> propsClass) {
        this.props = CoreUtils.convert2Properties(propsClass);
    }

    public T getProps() {
        return props;
    }

}
