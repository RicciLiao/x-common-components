package ricciliao.x.component.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

public abstract class KafkaProducer<T extends KafkaMessageDto> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    protected KafkaProducer(ProducerFactory<String, T> producerFactory) {
        this.kafkaTemplate = new KafkaTemplate<>(producerFactory);
    }

    public abstract String getTopic();

    public CompletableFuture<SendResult<String, T>> send(T message) {

        return kafkaTemplate.send(this.getTopic(), message);
    }

}
