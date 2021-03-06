package com.example.ma18_firestore;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // en referens till vår databas
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // i firestore sparas alla dokument som key-value par
        // vi kan skriva direkt till firestore genom att skapa en hashmap


  /*
        Map<String, Object> ost = new HashMap<>();

        // lägg till ett par key- value par i hash-mapen
        ost.put("name", "ost");
        ost.put("completed", false);

        // ta dokumentet ost som finns i collection items och sätt det till vår hashmap ost
        db.collection("items").document("ost").set(ost);

        // skapa en genväg till en collection
        CollectionReference itemsRef = db.collection("items");

        // lägg till ost till items. Firestore ger documentet ett eget namn
        itemsRef.add(ost);

        //skapa en genväg till ett dokument
        DocumentReference ostRef = db.collection("items").document("ost");
*/


        // vi kan läsa från vår databas endast en gång:
        // (men oftast vill vi i ställlet prenumerera på förändringar)
//        ostRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if(task.isSuccessful()) {
//                    DocumentSnapshot ostDoc = task.getResult();
//                    Log.d("David", "" + ostDoc.getData());
//
//
//                }
//            }
//        });

        // vi lyssnar efter förändringar på vår "ost"
//        ostRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//                if(documentSnapshot !=null && documentSnapshot.exists()) {
//                    Log.d("David", "" + documentSnapshot.getData());
//                }
//            }
//        });

        // i stället för hash-maps vill vi vanligtvis använda java-object
        // enkla objekt kan automatiskt konverteras mellan firstore-key-value par
        // och java objekt


        //skapa 5 st Item-objekt ochh lägg till dom i vår databas
//        for(int i = 0; i < 5; i++) {
//            Item item = new Item("item"+i, false);
//            db.collection("items").add(item);
//        }

        CollectionReference itemsRef = db.collection("items");


        //skapa en array som vi kan spara våra objekt som finns på firestore
        final List<Item> items = new ArrayList<>();


        //lyssna efter förändringar som görs i vår items-collection
        itemsRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                items.clear();

               // för varje document som finns i items-collection
                for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                    // gör om det documentet till ett Item objekt och lägg till i vår lista
                    Item item = snapshot.toObject(Item.class);
                    items.add(item);
                }

                Log.d("David","size:" + items.size());

                for(Item item : items) {
                    Log.d("David", item.name);
                    Log.d("David", "" +item.done);
                }

            }
        });

    }
}
