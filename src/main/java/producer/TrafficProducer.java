/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producer;

import listening.TrafficTailer;

/**
 *
 * @author ziedi
 */
public class TrafficProducer implements Runnable{
    private TrafficTailer tailer;
    private Thread t;

    public  TrafficProducer(String path, String topic,String serverBoot ) {
                
		tailer = new TrafficTailer(path,topic,serverBoot);
                
		this.t = new Thread(tailer);
		this.t.start();
		// tailer.start();
    }

    @Override
    public void run() {
        
    }
  


}
