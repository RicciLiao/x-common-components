package ricciliao.x.component.rest;

import jakarta.annotation.Nonnull;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {


    @Nonnull
    @Override
    public ClientHttpResponse intercept(@Nonnull HttpRequest httpRequest,
                                        @Nonnull byte[] bytes,
                                        @Nonnull ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
