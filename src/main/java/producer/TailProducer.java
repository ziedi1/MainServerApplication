/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer;

import listening.LogFileTailerListener;
import java.io.File;
import listening.LogFileTailer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import static producer.InitializeProducer.createProducer;

/**
 *
 * @author ziedi
 */
public class TailProducer implements LogFileTailerListener,Runnable{
    private LogFileTailer tailer;
    private Producer<String, String> producer;
    private String topic;
    private Thread t;

	/**
	 * Creates a new Tail instance to follow the specified file
	 */
	public TailProducer(String filename, String serverBoot, String topic,int runtime, long interval) {
            
		int curtime = (int) (System.currentTimeMillis() / 1000);
		
		this.producer = createProducer();
		this.topic = topic;
                System.out.println("Curtime"+curtime+"  Runtime"+runtime);
		tailer = new LogFileTailer(new File(filename), interval, true, curtime
				+ runtime);
                
		tailer.addLogFileTailerListener(this);
		this.t = new Thread(tailer);
		this.t.start();
		// tailer.start();
	}

	/**
	 * A new line has been added to the tailed log file
	 * 
	 * @param line
	 *            The new line that has been added to the tailed log file
	 */
	public void newLogFileLine(String line) {
            System.out.println(line);
		ProducerRecord<String, String> data = new ProducerRecord<String, String>(
				this.topic, line);
		this.producer.send(data);
	}

	public boolean getState() {
		return this.t.isAlive();
	}

	@Override
	public void close() {
		this.producer.close();
}

    @Override
    public void run() {
        
    }


    
       
}


