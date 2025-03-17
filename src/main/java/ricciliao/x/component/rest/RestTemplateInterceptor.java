package ricciliao.x.component.rest;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;

import java.io.IOException;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {


    @NonNull
    @Override
    public ClientHttpResponse intercept(@NonNull HttpRequest httpRequest,
                                        @NonNull byte[] bytes,
                                        @NonNull ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
