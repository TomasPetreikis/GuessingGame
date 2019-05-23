package com.example.laboras;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class Settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    loadSettings();
    }
    public void loadSettings()
    {
        SharedPreferences sharedPref= getSharedPreferences("Options", Context.MODE_PRIVATE);
        String name = sharedPref.getString("name","");
        int age = sharedPref.getInt("age",'0');
        int difficulty = sharedPref.getInt("difficulty",'0');

        EditText nameField = findViewById(R.id.nameId);
        nameField.setText(name);

        EditText ageField = findViewById(R.id.ageId);
        ageField.setText(age+"");


        Spinner spinnerField = findViewById(R.id.spinner2);
        spinnerField.setSelection(difficulty);

    }
    public void onSaveClick(View view)
    {
        EditText nameField = findViewById(R.id.nameId); // pagal id paimame lauka
        EditText ageField = findViewById(R.id.ageId);
        Spinner spinner = findViewById(R.id.spinner2);


        String name = nameField.getText().toString(); // is lauko isgauname informacija
        String ageString = ageField.getText().toString();
        int age = 1;
        try
        {
                age = Integer.parseInt(ageString);
        }
        catch (NumberFormatException e)
        {
            age = 0;
        }

        int difficulty = spinner.getSelectedItemPosition();

        SharedPreferences.Editor sharedPrefEditor = getSharedPreferences("Options", Context.MODE_PRIVATE).edit(); // sukuriame prieiga prie failo
        sharedPrefEditor.putString("name",name); // i faila surasome varda ir amziu
        sharedPrefEditor.putInt("age",age);
        sharedPrefEditor.putInt("difficulty",difficulty);
        sharedPrefEditor.apply();
        finish();
    }
}
