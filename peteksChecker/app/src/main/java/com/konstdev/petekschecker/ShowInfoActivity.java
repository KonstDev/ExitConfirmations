package com.konstdev.petekschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class ShowInfoActivity extends AppCompatActivity implements View.OnClickListener {
    final String TAG = "logTag";
    TextView tv;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);

        tv = findViewById(R.id.tvShow);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(this);


        Intent intent = getIntent();
        String str = intent.getStringExtra("CONFIRMATION_CODE");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference confirms = db.collection("confirms");
        Query query = confirms.whereEqualTo("confirmation", str);
        query.get().addOnCompleteListener(task -> {
            if (task.getResult().isEmpty())
                tv.setText("Invalid code!");
            else if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                for (QueryDocumentSnapshot document : querySnapshot) {
                    Map<String, Object> data = document.getData();
                    //Log.d(TAG, "User data: " + data);
                    //String a = String.valueOf(data);
                    tv.setText(String.valueOf(data));
                }
            } else {
                Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });
        if (tv.getText() == "BEBRA")
            tv.setText("Error getting documents.");
        String a = String.valueOf(query.get());
        System.out.println(a);
        //String result = String.valueOf(query);
        System.out.println(str);
        //tv.setText(String.valueOf(query));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}