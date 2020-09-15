/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumer;

import general.IKafkaConstants;
import static general.SendToFirebase.sendToFB;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

/**
 *
 * @author ziedi
 */
public class BotnetResultConsumer implements Runnable{
    static void runConsumer() throws Exception {
        Consumer<String, String> consumer = ConsumerCreator.createConsumer(IKafkaConstants.BOTNET_RESULT_TOPIC_NAME);
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records){
                //System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
                String[] value=record.value().split(",");
                String name=value[0];
                int status=Integer.parseInt(value[1]);
                sendToFB(name,status);
            }
        }
    }

    @Override
    public void run() {
        
    }
}
