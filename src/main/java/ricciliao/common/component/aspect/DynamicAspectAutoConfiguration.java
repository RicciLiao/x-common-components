package ricciliao.common.component.aspect;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.lang.NonNull;
import ricciliao.common.component.context.PropsBeanDefinitionRegistryPostProcessor;

import java.util.Objects;

@AutoConfiguration
@Conditional({DynamicAspectAutoConfiguration.ConfigurationCondition.class})
public class DynamicAspectAutoConfiguration extends PropsBeanDefinitionRegistryPostProcessor<DynamicAspectProperties> {

    public DynamicAspectAutoConfiguration() {
        super(DynamicAspectProperties.class);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(@NonNull BeanDefinitionRegistry registry) throws BeansException {
        for (DynamicAspectProperties.ExpressionAspect aspect : this.getProps().getAspectList()) {
            if (isBlank(aspect.getBeanName())) {

                throw new BeanCreationException("can not define Dynamic Aspect without bean name!");
            }
            if (aspect.getAspect() == null) {

                throw new BeanCreationException("can not define Dynamic Aspect without aspect class!");
            }
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(aspect.getAspect());
            registry.registerBeanDefinition(aspect.getBeanName(), builder.getBeanDefinition());
        }
    }

    private static boolean isBlank(CharSequence cs) {
        int strLen = cs == null ? 0 : cs.length();
        if (strLen != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {

                    return false;
                }
            }
        }

        return true;
    }

    static class ConfigurationCondition implements Condition {

        @Override
        public boolean matches(@NonNull ConditionContext context, @NonNull AnnotatedTypeMetadata metadata) {

            return Objects.nonNull(context.getEnvironment().getProperty("dynamic-aop.aspect-list[0].expression"));
        }

    }

}
