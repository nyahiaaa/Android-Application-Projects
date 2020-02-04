package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Window3 extends AppCompatActivity {


  private   ImageView imageView;
  private TextView mName,mEmail,mPermanantCode;
  private   EditText mGrade;
  private  Button btnReturn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window3);

        final Intent intent = getIntent();


        imageView =  findViewById(R.id.img_user);
        mName =(TextView) findViewById(R.id.text_name);
        mEmail=(TextView) findViewById(R.id.text_email);
        mPermanantCode = (TextView)findViewById(R.id.text_code);
        btnReturn =(Button)findViewById(R.id.button_return);
        mGrade = (EditText)findViewById(R.id.edit_grade);




        imageView.setImageResource(intent.getIntExtra("imageurl",0));
        mName.setText(intent.getStringExtra("uname"));
        mEmail.setText(intent.getStringExtra("email"));

        mPermanantCode.setText(intent.getStringExtra("code"));

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value = Integer.valueOf(mGrade.getText().toString());

                if (value<=3){
                    Intent i = new Intent();
                    i.putExtra("grade",Integer.parseInt(mGrade.getText().toString()) );
                    i.putExtra("position2",intent.getIntExtra("position",1));
                    setResult(RESULT_OK, i);
                    finish();
                }

                else {
                    Toast.makeText(Window3.this, getResources().getString(R.string._enter_correct_grade), Toast.LENGTH_SHORT).show();
                }



            }
        });




    }
}
