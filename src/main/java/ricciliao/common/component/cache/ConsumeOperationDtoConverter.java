package ricciliao.common.component.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.NonNull;
import ricciliao.common.component.cache.pojo.ConsumerIdentifierDto;
import ricciliao.common.component.cache.pojo.ConsumerOperationDto;
import ricciliao.common.component.cache.pojo.CacheDto;
import ricciliao.common.component.exception.CmnParameterException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ConsumeOperationDtoConverter extends AbstractHttpMessageConverter<ConsumerOperationDto<? extends CacheDto>> {

    private final ObjectMapper objectMapper;
    private final CacheProviderSelector cacheProvider;

    public ConsumeOperationDtoConverter(ObjectMapper objectMapper, CacheProviderSelector cacheProvider) {
        super(MediaType.APPLICATION_JSON);
        this.objectMapper = objectMapper;
        this.cacheProvider = cacheProvider;
    }

    @Override
    protected boolean supports(@NonNull Class<?> clazz) {

        return ConsumerOperationDto.class.isAssignableFrom(clazz);
    }

    @NonNull
    @Override
    protected ConsumerOperationDto<? extends CacheDto> readInternal(@NonNull Class<? extends ConsumerOperationDto<? extends CacheDto>> clazz,
                                                                    @NonNull HttpInputMessage inputMessage) throws HttpMessageNotReadableException {

        Optional<Field> dataFieldOptional = Arrays.stream(clazz.getDeclaredFields()).filter(f -> f.isAnnotationPresent(ConsumerData.class)).findFirst();
        if (dataFieldOptional.isEmpty()) {

            throw new HttpMessageNotReadableException("missing @interface " + ConsumerData.class.getSimpleName(), inputMessage);
        }
        try {
            List<String> customer = inputMessage.getHeaders().get(Constants.HTTP_HEADER_FOR_CACHE_CUSTOMER);
            List<String> store = inputMessage.getHeaders().get(Constants.HTTP_HEADER_FOR_CACHE_STORE);
            if (CollectionUtils.isEmpty(customer) || customer.size() > 1
                    || CollectionUtils.isEmpty(store) || store.size() > 1) {

                throw new CmnParameterException();
            }
            ConsumerIdentifierDto identifier = new ConsumerIdentifierDto(customer.get(0), store.get(0));
            Class<? extends CacheDto> cacheClass = cacheProvider.getCacheClass(identifier);
            if (Objects.isNull(cacheClass)) {

                throw new CmnParameterException();
            }
            Type type = objectMapper.getTypeFactory().constructParametricType(ConsumerOperationDto.class, cacheClass);

            return objectMapper.readValue(inputMessage.getBody(), new TypeReference<>() {
                @Override
                public Type getType() {

                    return type;
                }
            });
        } catch (Exception e) {

            throw new HttpMessageNotReadableException("can not convert to " + ConsumerIdentifierDto.class, inputMessage);
        }
    }

    @Override
    protected void writeInternal(@NonNull ConsumerOperationDto<? extends CacheDto> consumerOperationDto,
                                 @NonNull HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        objectMapper.writeValue(outputMessage.getBody(), consumerOperationDto);
    }

}
