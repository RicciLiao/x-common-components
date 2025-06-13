package ricciliao.x.component.kafka;

import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListener;

public abstract class KafkaConsumer implements KafkaHandler {

    protected KafkaConsumer(ConsumerFactory<?, ?> consumerFactory) {
        ContainerProperties props = new ContainerProperties(this.getTopic());
        props.setGroupId(this.getGroup());
        props.setClientId(this.getTopic() + "-" + this.getGroup());
        props.setMessageListener((MessageListener<String, String>) data -> handle(data.value()));
        ConcurrentMessageListenerContainer<?, ?> container =
                new ConcurrentMessageListenerContainer<>(consumerFactory, props);
        container.start();
    }


    public abstract String getTopic();

    public abstract String getGroup();

}
