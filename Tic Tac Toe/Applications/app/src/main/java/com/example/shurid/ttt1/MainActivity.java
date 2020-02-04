package com.example.shurid.ttt1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private int roundCount;

    private TextView textViewPlayer2;

    private Button b0;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;

    private Button breset;

    private Boolean french = false;

    private static final Random RANDOM = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer2 = findViewById(R.id.text_view_p2);

        breset = findViewById(R.id.button_reset);

        b0 = findViewById(R.id.button_00);
        b1 = findViewById(R.id.button_01);
        b2 = findViewById(R.id.button_02);

        b3 = findViewById(R.id.button_10);
        b4 = findViewById(R.id.button_11);
        b5 = findViewById(R.id.button_12);

        b6 = findViewById(R.id.button_20);
        b7 = findViewById(R.id.button_21);
        b8 = findViewById(R.id.button_22);



        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        String lc = java.util.Locale.getDefault().getLanguage();
        if(!lc.equals("en")){
            breset.setText("Nouvelle Partie");
            french = true;
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        boolean next = true;
        roundCount++;

        ((Button) v).setText("X");


        if (checkForWin()) {
                player1Wins();
                return;
        }
        else if (roundCount == 5) {
            draw();
            return;
        } else {

        }

        if(roundCount==1){
            if(b4.getText().equals("X"))
            {
                b0.setText("O");
            }
            else{
                b4.setText("O");
            }
        }
        else if(roundCount<=4){
            do {
                int position = RANDOM.nextInt(9);
                switch (position){
                    case 0:
                        if(b0.getText().equals("")){
                            b0.setText("O");
                            next = false;
                        }
                        break;
                    case 1:
                        if(b1.getText().equals("")){
                            b1.setText("O");
                            next = false;
                        }
                        break;
                    case 2:
                        if(b2.getText().equals("")){
                            b2.setText("O");
                            next = false;
                        }
                        break;
                    case 3:
                        if(b3.getText().equals("")){
                            b3.setText("O");
                            next = false;
                        }
                        break;
                    case 4:
                        if(b4.getText().equals("")){
                            b4.setText("O");
                            next = false;
                        }
                        break;
                    case 5:
                        if(b5.getText().equals("")){
                            b5.setText("O");
                            next = false;
                        }
                        break;
                    case 6:
                        if(b6.getText().equals("")){
                            b6.setText("O");
                            next = false;
                        }
                        break;
                    case 7:
                        if(b7.getText().equals("")){
                            b7.setText("O");
                            next = false;
                        }
                        break;
                    case 8:
                        if(b8.getText().equals("")){
                            b8.setText("O");
                            next = false;
                        }
                        break;
                }
            } while (next);
        }
        else{

        }

        if (checkForWin()) {
            player2Wins();
            return;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {

                buttons[i][0].setTextColor(Color.RED);
                buttons[i][1].setTextColor(Color.RED);
                buttons[i][2].setTextColor(Color.RED);
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                buttons[0][i].setTextColor(Color.RED);
                buttons[1][i].setTextColor(Color.RED);
                buttons[2][i].setTextColor(Color.RED);
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            buttons[0][0].setTextColor(Color.RED);
            buttons[1][1].setTextColor(Color.RED);
            buttons[2][2].setTextColor(Color.RED);
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            buttons[0][2].setTextColor(Color.RED);
            buttons[1][1].setTextColor(Color.RED);
            buttons[2][0].setTextColor(Color.RED);
            return true;
        }

        return false;
    }

    private void player1Wins() {
        textViewPlayer2.setText("Player 1 Wins !");
        if(french){
            textViewPlayer2.setText("Joueur 1 à Gagné !");
        }
        endBoard();

    }

    private void player2Wins() {
        textViewPlayer2.setText("Player 2 Wins !");
        if(french){
            textViewPlayer2.setText("Joueur 2 à Gagné !");
        }
        endBoard();
    }

    private void draw() {
        textViewPlayer2.setText("Draw !");
        if(french){
            textViewPlayer2.setText("Partie Nulle !");
        }
        endBoard();
    }

    private void endBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setClickable(false);
            }
        }
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setClickable(true);
                buttons[i][j].setTextColor(Color.BLACK);
            }
        }

        roundCount = 0;
        textViewPlayer2.setText("");
    }

    private void resetGame() {
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
    }
}