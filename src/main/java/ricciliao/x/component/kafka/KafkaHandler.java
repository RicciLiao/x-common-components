package ricciliao.x.component.kafka;

public interface KafkaHandler<T extends KafkaMessageDto> {

    void handle(T message);

}
