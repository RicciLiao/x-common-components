/*
package ricciliao.x.component.response;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.Ordered;

import java.util.Objects;

public class ResponseEmptyDataAspect extends DynamicAspect implements Ordered {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object o = super.invoke(invocation);
        if (Objects.isNull(o) || !(o instanceof ResponseVo<? extends ResponseData> vo)) {

            return o;
        }
        if (Objects.isNull(vo.getData())) {

            return new ResponseVo<>(
                    vo.getCode(),
                    vo.getMessage(),
                    new ResponseEmptyData()
            );
        }

        return o;
    }

    @Override
    public int getOrder() {

        return Ordered.HIGHEST_PRECEDENCE;
    }
}
*/
