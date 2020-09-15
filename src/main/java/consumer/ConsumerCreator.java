/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumer;

import general.IKafkaConstants;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 *
 * @author ziedi
 */
public class ConsumerCreator {
    
    static Consumer<String, String> createConsumer(String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", IKafkaConstants.KAFKA_BROKERS);
        props.put("acks", IKafkaConstants.ACKS);
        props.put("compression.type", IKafkaConstants.COMPRESSION_TYPE);
        props.put("retries", IKafkaConstants.RETRIES);
        props.put("batch.size", IKafkaConstants.BATCH_SIZE);
        props.put("buffer.memory", IKafkaConstants.BUFFER_MEMORY);
        props.put("key.serializer", IKafkaConstants.KEY_SERIALIZER);
        //props.put("value.serializer", "org.apache.kafka.connect.json.JsonSerializer");
        props.put("value.serializer", IKafkaConstants.VALUE_SERIALIZER);
        
        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));
        return consumer;
    }
}
