package ricciliao.x.component.kafka;

import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListener;

public abstract class KafkaConsumer<T extends KafkaMessageDto> implements KafkaHandler<T> {

    private final Class<T> messageClass;

    protected KafkaConsumer(ConsumerFactory<String, T> consumerFactory,
                            Class<T> messageClass) {
        ContainerProperties props = new ContainerProperties(this.getTopic());
        props.setGroupId(this.getGroup());
        props.setClientId(this.getTopic() + "-" + this.getGroup());
        props.setMessageListener((MessageListener<String, T>) data -> handle(data.value()));
        ConcurrentMessageListenerContainer<String, T> container =
                new ConcurrentMessageListenerContainer<>(consumerFactory, props);
        container.start();
        this.messageClass = messageClass;
    }

    public abstract String getTopic();

    public abstract String getGroup();

    public Class<T> getMessageClass() {
        return messageClass;
    }
}
