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
        btn = findViewById(R.id.button);
        btn.setOnClickListener(this);
        TextView tvNames= findViewById(R.id.tvName), tvExitTime = findViewById(R.id.tvExitTime), tvReturnTime = findViewById(R.id.tvReturnTime), tvMadrich = findViewById(R.id.tvMadrich), tvGoingTo = findViewById(R.id.tvGoingTo);
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
                    tvNames.setText("Names: " + String.valueOf(data.get("StudentName")));
                    tvExitTime.setText("Exit Time: " + String.valueOf(data.get("ExitTime")));
                    tvReturnTime.setText("Return Time: " + String.valueOf(data.get("ReturnTime")));
                    tvGoingTo.setText("Going to: " + String.valueOf(data.get("GoingTo")));
                    tvMadrich.setText("Madrich: " + String.valueOf(data.get("MadrichName")));
                }
            } else {
                Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });
        String a = String.valueOf(query.get());
        System.out.println(a);
        System.out.println(str);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}