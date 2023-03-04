package com.konstdev.petekschecker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn;
    TextView tv;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        //FirebaseFirestore db = FirebaseFirestore.getInstance();

        btn = findViewById(R.id.btn0);
        tv = findViewById(R.id.textView);
        editText = findViewById(R.id.etCode);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        tv.setText(editText.getText());
        Intent intent = new Intent(this, ShowInfoActivity.class);
        //String str  = String.valueOf(editText.getText());
        intent.putExtra("CONFIRMATION_CODE", String.valueOf(editText.getText()));
        startActivity(intent);

    }
}