package ricciliao.common.component.aspect;

import org.springframework.boot.context.properties.ConfigurationProperties;
import ricciliao.dynamic.aop.DynamicAspect;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "dynamic-aop")
public class DynamicAspectProperties {

    private List<ExpressionAspect> aspectList = new ArrayList<>();

    public List<ExpressionAspect> getAspectList() {
        return aspectList;
    }

    public void setAspectList(List<ExpressionAspect> aspectList) {
        this.aspectList = aspectList;
    }

    public static class ExpressionAspect {

        private String beanName;
        private String expression;
        private Class<? extends DynamicAspect> aspect;

        public String getBeanName() {
            return beanName;
        }

        public void setBeanName(String beanName) {
            this.beanName = beanName;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public Class<? extends DynamicAspect> getAspect() {
            return aspect;
        }

        public void setAspect(Class<? extends DynamicAspect> aspect) {
            this.aspect = aspect;
        }
    }
}
