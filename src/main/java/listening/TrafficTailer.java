/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listening;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;
import listening.LogFileTailer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import static producer.InitializeProducer.createProducer;

/**
 *
 * @author ziedi
 */
public class TrafficTailer implements Runnable{
    static Logger log = Logger.getLogger(LogFileTailer.class.getName());
    private String path;
    private Producer<String, String> producer;
    private String topic;
    private String serverBoot;

    public TrafficTailer(String path, String topic, String serverBoot) {
        this.path = path;
        this.topic = topic;
        this.serverBoot = serverBoot;
    }

   
    
    

    @Override
    public void run() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        System.out.println(this.path+" "+this.serverBoot+" "+this.topic);

        // -- Linux --

        // Run a shell command
         
        processBuilder.command("bash", "-c", "ra -S localhost:561 -n -F "+this.path);
         // Run a shell script
        //processBuilder.command("path/to/hello.sh");// Run a shell script
        //processBuilder.command("path/to/hello.sh");


        try {
            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
			new InputStreamReader(process.getInputStream()));
            
            /*+++++++++++++final Producer<String, String> producer = SendTrafficToKafka.createProducer();
           
            ProducerRecord<String, String> record;*/
            this.producer = createProducer();
            ProducerRecord<String, String> record;
            String line=null;
            while ((line = reader.readLine()) != null) {
		//output.append(line + "\n");
                System.out.println(line);
                record =new ProducerRecord<String, String>(this.topic, line);
                this.producer.send(record);
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
		System.out.println("Success!");
		System.out.println(output);
		System.exit(0);
            } else {
		//abnormal...
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
    }
    }

}
