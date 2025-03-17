package ricciliao.x.component.cache.consumer;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ricciliao.x.component.cache.CacheConstants;
import ricciliao.x.component.cache.pojo.CacheDto;
import ricciliao.x.component.cache.pojo.ConsumerIdentifierDto;
import ricciliao.x.component.cache.pojo.ConsumerOperationDto;
import ricciliao.x.component.response.ResponseSimpleData;
import ricciliao.x.component.response.ResponseVo;

import java.util.Map;
import java.util.Objects;

public class ConsumerCacheRestService<T extends CacheDto> {

    public ConsumerCacheRestService(ConsumerCacheProperties.OperationProperties props,
                                    ConsumerIdentifierDto identifier,
                                    RestTemplate restTemplate) {
        this.props = props;
        this.identifier = identifier;
        this.restTemplate = restTemplate;
    }

    protected final RestTemplate restTemplate;
    protected final ConsumerCacheProperties.OperationProperties props;
    protected final ConsumerIdentifierDto identifier;

    public ResponseSimpleData.Bool create(ConsumerOperationDto<T> operation) throws RestClientException {
        UriComponentsBuilder uriComponentsBuilder = props.getCreate().toBuilder();
        ResponseEntity<ResponseVo<ResponseSimpleData.Bool>> response =
                restTemplate.exchange(
                        RequestEntity
                                .method(props.getCreate().getMethod(), uriComponentsBuilder.build().encode().toUri())
                                .header(CacheConstants.HTTP_HEADER_FOR_CACHE_STORE, identifier.getStore())
                                .header(CacheConstants.HTTP_HEADER_FOR_CACHE_CUSTOMER, identifier.getConsumer())
                                .body(operation),
                        new ParameterizedTypeReference<>() {
                        }
                );
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            ResponseVo<ResponseSimpleData.Bool> body = response.getBody();
            if (Objects.nonNull(body)) {

                return body.getData();
            }
        }

        return null;
    }

    public ResponseSimpleData.Bool update(ConsumerOperationDto<T> operation) throws RestClientException {
        UriComponentsBuilder uriComponentsBuilder = props.getUpdate().toBuilder();
        ResponseEntity<ResponseVo<ResponseSimpleData.Bool>> response =
                restTemplate.exchange(
                        RequestEntity
                                .method(props.getUpdate().getMethod(), uriComponentsBuilder.build().encode().toUri())
                                .header(CacheConstants.HTTP_HEADER_FOR_CACHE_STORE, identifier.getStore())
                                .header(CacheConstants.HTTP_HEADER_FOR_CACHE_CUSTOMER, identifier.getConsumer())
                                .body(operation),
                        new ParameterizedTypeReference<>() {
                        }
                );
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            ResponseVo<ResponseSimpleData.Bool> body = response.getBody();
            if (Objects.nonNull(body)) {

                return body.getData();
            }
        }

        return null;
    }

    public ResponseSimpleData.Bool delete(String id) throws RestClientException {
        UriComponentsBuilder uriComponentsBuilder = props.getDelete().toBuilder();
        uriComponentsBuilder.uriVariables(Map.of("id", id));
        ResponseEntity<ResponseVo<ResponseSimpleData.Bool>> response =
                restTemplate.exchange(
                        RequestEntity
                                .method(props.getDelete().getMethod(), uriComponentsBuilder.build().encode().toUri())
                                .header(CacheConstants.HTTP_HEADER_FOR_CACHE_STORE, identifier.getStore())
                                .header(CacheConstants.HTTP_HEADER_FOR_CACHE_CUSTOMER, identifier.getConsumer())
                                .build(),
                        new ParameterizedTypeReference<>() {
                        }
                );
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            ResponseVo<ResponseSimpleData.Bool> body = response.getBody();
            if (Objects.nonNull(body)) {

                return body.getData();
            }
        }

        return null;
    }

    public ConsumerOperationDto<T> get(String id) throws RestClientException {
        UriComponentsBuilder uriComponentsBuilder = props.getRetrieve().toBuilder();
        uriComponentsBuilder.uriVariables(Map.of("id", id));
        ResponseEntity<ResponseVo<ConsumerOperationDto<T>>> response =
                restTemplate.exchange(
                        RequestEntity
                                .method(props.getRetrieve().getMethod(), uriComponentsBuilder.build().encode().toUri())
                                .header(CacheConstants.HTTP_HEADER_FOR_CACHE_STORE, identifier.getStore())
                                .header(CacheConstants.HTTP_HEADER_FOR_CACHE_CUSTOMER, identifier.getConsumer())
                                .build(),
                        new ParameterizedTypeReference<>() {
                        }
                );
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            ResponseVo<ConsumerOperationDto<T>> body = response.getBody();
            if (Objects.nonNull(body)) {

                return body.getData();
            }
        }

        return null;
    }

}
