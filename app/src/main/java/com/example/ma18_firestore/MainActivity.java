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

        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//        Map<String, Object> ost = new HashMap<>();
//
//        ost.put("name", "ost");
//        ost.put("completed", false);
//
//        db.collection("items").document("ost").set(ost);
//
//        CollectionReference itemsRef = db.collection("items");
//
//        itemsRef.add(ost);

 //       DocumentReference ostRef = db.collection("items").document("ost");

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

//        ostRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//                if(documentSnapshot !=null && documentSnapshot.exists()) {
//                    Log.d("David", "" + documentSnapshot.getData());
//                }
//            }
//        });


        for(int i = 0; i < 5; i++) {
            Item item = new Item("item"+i, false);
            db.collection("items").add(item);
        }

        CollectionReference itemsRef = db.collection("items");

        final List<Item> items = new ArrayList<>();

        itemsRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                items.clear();

                for (DocumentSnapshot snapshot : queryDocumentSnapshots) {

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
