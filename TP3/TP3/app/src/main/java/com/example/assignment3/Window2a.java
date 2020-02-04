package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Window2a extends AppCompatActivity {

    private Button mButton_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window2a);


        mButton_back = (Button) findViewById(R.id.button_back);

        mButton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent( Window2a.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
