/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import consumer.BotnetResultConsumer;
import consumer.UserResultsConsumer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import producer.*;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author ziedi
 */
public class StartApplication {
    private static TrafficProducer trafficProd;
    private static Thread trafficThread;
    private static TailProducer logFileProd;
    private static Thread logFileThread;
    private static BotnetResultConsumer botConsumer;
    private static UserResultsConsumer userConsumer;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream serviceAccount = new FileInputStream("/home/ziedi/NetBeansProjects/PFE_Projects/FindUsersConsumer/src/main/resources/firebase/fluttertuto-bc334-firebase-adminsdk-mje62-bcb09d0f8a.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(credentials)
            .build();
        FirebaseApp.initializeApp(options);
    trafficProd = new TrafficProducer("/home/ziedi/NetBeansProjects/PFE_Projects/TrafficProcessingProducer/src/main/resources/property/ra.conf","traffic" ,"localhost:9092");
                
    trafficThread = new Thread(trafficProd);
    trafficThread.start();
    logFileProd = new TailProducer("/var/log/messages","localhost:9092","logfile",31536000,500);
    logFileThread = new Thread(logFileProd);
    logFileThread.start();
    }
    
}
