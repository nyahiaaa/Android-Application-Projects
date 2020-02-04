package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    TextInputEditText mUsername,mPassword;
    Button mSubmit;
    TextView mGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = (TextInputEditText) findViewById(R.id.text_username);
        mPassword =(TextInputEditText)findViewById(R.id.text_password);
        mSubmit = (Button)findViewById(R.id.button_submit);
        mGrade = (TextView) findViewById(R.id.text_grade);
        String[] User_names = getResources().getStringArray(R.array.users);

        final SharedPreferences sharedPreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("password",12345);
        editor.commit();


        SharedPreferences prefs = getSharedPreferences("PREFERENCES", MODE_PRIVATE);
        float grade = Float.parseFloat(String.valueOf(prefs.getInt("sum", 0)));

        if (grade>0){

            mGrade.setText(getResources().getString(R.string.total_grade)+ "" + grade+"");
        }
        else {
            mGrade.setVisibility(View.GONE);
        }

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(mUsername.getText().toString().isEmpty()){
                    mUsername.setError(getResources().getString(R.string.empty_username));

                }else if (mPassword.getText().toString().isEmpty()){

                    mPassword.setError("enter password");
                }else {


                    int password = sharedPreferences.getInt("password",0);
                    int password2 = Integer.parseInt(mPassword.getText().toString());
                    String[] User_names = getResources().getStringArray(R.array.users);

                    if (password2 == password){

                        Intent intent = new Intent(MainActivity.this,Window2b.class);
                        intent.putExtra("username",mUsername.getText().toString());
                        startActivity(intent);
                        finish();

                    }
                    else {
                        Intent intent = new Intent(MainActivity.this,Window2a.class);
                        startActivity(intent);
                        finish();
                    }

                }




            }
        });
    }
}
