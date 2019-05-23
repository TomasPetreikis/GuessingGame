package com.example.laboras;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Winner extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        TextView resultField = findViewById(R.id.textViewWon1);
        Bundle b = new Bundle();
        b=getIntent().getExtras();
        int current_turn= b.getInt("current_turns");
        int random_number= b.getInt("random_number");
        resultField.setText(random_number + " was the correct number !");
    }

}
