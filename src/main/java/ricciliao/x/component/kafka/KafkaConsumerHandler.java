package ricciliao.x.component.kafka;

public interface KafkaConsumerHandler<T extends KafkaMessageDto> {

    void handle(T message);

}
