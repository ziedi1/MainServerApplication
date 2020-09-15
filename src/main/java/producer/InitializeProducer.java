/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer;

import general.IKafkaConstants;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

/**
 *
 * @author ziedi
 */
public class InitializeProducer {
    

    public static Producer<String, String> createProducer() {
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


//configure the following three settings for SSL Encryption
       /*props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
       props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "/var/private/ssl/kafka.client.truststore.jks");
       props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,  "******");

       // configure the following three settings for SSL Authentication
       props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, "/var/private/ssl/kafka.client.keystore.jks");
       props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "*******");
       props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "*******");*/


        return new KafkaProducer<>(props);
    }

    
}
