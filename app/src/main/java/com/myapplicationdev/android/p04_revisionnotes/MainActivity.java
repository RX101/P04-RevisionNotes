package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNote;
    Button btnInsert, btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNote = (EditText)findViewById(R.id.editTextNote);
        btnInsert = (Button)findViewById(R.id.buttonInsertNote);
        btnShowList =(Button)findViewById(R.id.buttonShowList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                String content  = etNote.getText().toString();
                // Get the RadioGroup object
                RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupStars);
                // Get the Id of the selected radio button in the RadioGroup
                int selectedButtonId = rg.getCheckedRadioButtonId();
                // Get the radio button object from the Id we had gotten above
                RadioButton rb = (RadioButton) findViewById(selectedButtonId);
                int selectedRB = Integer.parseInt(rb.getText().toString());
                // Insert a task
                db.insertNote(content,selectedRB);
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                etNote.setText("");
                rg.check(0);
                db.close();
            }
        });
        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });


    }
}
