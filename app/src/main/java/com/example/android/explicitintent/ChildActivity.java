package com.example.android.explicitintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString(MainActivity.EXTRA_MESSAGE);

        setContentView(R.layout.activity_child);

        TextView displayTextView = findViewById(R.id.tv_display);
        if(message != null)
            displayTextView.setText(message);
    }
}