package com.example.assignment3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Window2b extends AppCompatActivity {
    private TextView mText_Username;
   private ListView mListview;
   private Button btnReturn;

    ArrayList<Integer> grade = new ArrayList<Integer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window2b);

        final String mUsername = getIntent().getStringExtra("username");

        mText_Username = (TextView)findViewById(R.id.text_user);
        mText_Username.setText(mUsername);
        mListview = (ListView) findViewById(R.id.list_users);
        btnReturn = findViewById(R.id.btn_return);
        final String[] email = {"crosby87@nhl.com","mcdavid87@nhl.com","matthews34@nhl.com"};
        final String [] permanantcode ={"PIT87","EDM97","TOR34"};
        final int[] imgUrl ={R.drawable.penguin,R.drawable.barrel,R.drawable.leaf};

       String [] mUsernames = getResources().getStringArray(R.array.users);
       final ArrayAdapter<String>adapter;

       adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mUsernames);



       mListview.setAdapter(adapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent =new Intent(Window2b.this,Window3.class);
                intent.putExtra("imageurl",imgUrl[position]);
                intent.putExtra("uname",adapter.getItem(position));
                intent.putExtra("email",email[position]);
                intent.putExtra("code",permanantcode[position]);
                intent.putExtra("position",mListview.getPositionForView(view));
                startActivityForResult(intent,1);
            }
        });




        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (grade.size()<=2){


                    Toast.makeText(Window2b.this, getResources().getString(R.string.enter_grade), Toast.LENGTH_SHORT).show();
                }
                else {

                    int sum = 0;
                    for(int d : grade)
                        sum += d;
                    SharedPreferences Preferences = getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = Preferences.edit();
                    editor.putInt("sum",sum);
                    editor.commit();


                    Log.d("suuuu",sum+"");


                    Intent intent = new Intent(Window2b.this,MainActivity.class);
                    startActivity(intent);



                }
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){

                int position = data.getIntExtra("position2",1);
                int value = data.getIntExtra("grade",3);
               if (grade.size()<=2){

                   grade.add(position,value);

               }else {

                   if (grade.get(position)==null){
                       grade.add(position,value);
                   }else {
                       grade.set(position,value);
                   }

               }

               Log.d("qwert",grade+"");


            }
        }
    }
}
