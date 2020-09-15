/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author ziedi
 */
public class SendToFirebase {
    public static void sendToFB(String name,int status) throws FileNotFoundException, IOException, InterruptedException, ExecutionException{
        
        

        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("users").document(name);
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("status", status);
        
        System.out.println("tessssssssst");
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
        // ...
        // result.get() blocks on response
        System.out.println("Update time : " + result.get().getUpdateTime());
       
        
    }
    
}
