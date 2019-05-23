package com.example.laboras;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class Play extends Activity {

    int difficulty = 0, limit_to, limit_from, entered_number = 0, random_number = 0,
            current_turn = 1, turns, multiplier, score;
    TextView resultField1, resultField2;
    EditText inputField;
    String inputString;
    Button button1;
    Bundle bundle;
    Intent wIntent,lIntent;
    DatabaseHelper db;
    private String name;


    public void loadSettings() {
        resultField1 = findViewById(R.id.resultField1);
        resultField2 = findViewById(R.id.resultField2);
        inputField = findViewById(R.id.guessNumber);
        button1 = findViewById(R.id.button6);
        bundle =new Bundle();
        wIntent = new Intent(this, Winner.class);
        lIntent = new Intent(this, Loser.class);
        SharedPreferences sharedPref = getSharedPreferences("Options", Context.MODE_PRIVATE);
        difficulty = sharedPref.getInt("difficulty", '0');
        name=sharedPref.getString("name","");
    }

    public void setDifficulty() {
        if (difficulty == 0) {
            limit_to = 10;
            limit_from = 0;
            multiplier = 10;
            turns = 5;
        } else if (difficulty == 1) {
            limit_to = 100;
            limit_from = 0;
            multiplier = 100;
            turns = 7;
        } else {
            limit_to = 1000;
            limit_from = 0;
            multiplier = 1000;
            turns = 10;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        loadSettings();
        setDifficulty();
        updateFields();
        db = new DatabaseHelper(this);
    }

    // Pirma karta sukurus
    protected void updateFields() {
        Random ran = new Random();
        random_number = ran.nextInt(limit_to + 1);
        resultField2.setText("You have " + Integer.toString(turns) + " turns left \n" +
                "Pick a number between " + Integer.toString(limit_from) + " and " + Integer.toString(limit_to));
    }

    public void onGuessClick(View view) {

        inputString = inputField.getText().toString();
        if (TextUtils.isEmpty(inputString)) {
            inputField.setError("Write number");
            return;
        }
        entered_number = Integer.parseInt(inputString);
        if (entered_number > limit_to || entered_number < limit_from) {
            inputField.setError("Write number between " + Integer.toString(limit_from) + " and " + Integer.toString(limit_to));
        } else {
            if (entered_number == random_number) {
                    score = (turns + 1 - current_turn) * multiplier;
                    db.addData(name,Integer.toString(score));
                    bundle.putInt("random_number",random_number);
                    bundle.putInt("current_turns",current_turn);
                    wIntent.putExtras(bundle);
                    startActivity(wIntent);
                    finish();

            } else if (turns - current_turn == 0) {
                bundle.putInt("random_number",random_number);
                bundle.putInt("current_turns",current_turn);
                lIntent.putExtras(bundle);
                startActivity(lIntent);
                finish();
            } else {
                resultField1.setText("Turn: " + Integer.toString(current_turn));
                resultField2.setText("Pick a number between " + Integer.toString(limit_from) + " and " + Integer.toString(limit_to)+
                "\n You have " + Integer.toString(turns - current_turn) + " left");
                if (entered_number - random_number < 0) {
                    resultField1.append("\n" + Integer.toString(entered_number) + " is too low, try higher!");
                } else {
                    resultField1.append("\n" + Integer.toString(entered_number) + " is too high, try lower!");
                }
            }
            current_turn++;
        }
    }

}
