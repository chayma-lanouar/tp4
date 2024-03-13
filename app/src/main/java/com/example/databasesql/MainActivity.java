package com.example.databasesql;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button b1, b2;
    private EditText nom, mail, phone;
    DataBase dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);

        nom = (EditText) findViewById(R.id.editTextText1);
        mail = (EditText) findViewById(R.id.editTextText2);
        phone = (EditText) findViewById(R.id.editTextText3);

        dbHelper = new DataBase(this);

        b1.setOnClickListener(v -> {
            if (!nom.getText().toString().equalsIgnoreCase("") &&
                    !mail.getText().toString().equalsIgnoreCase("") &&
                    !phone.getText().toString().equalsIgnoreCase("")) {
                boolean inserted = dbHelper.insertData(nom.getText().toString(),
                        mail.getText().toString(), phone.getText().toString());
                if (inserted) {
                    Toast.makeText(this, "Insertion avec succes", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Echec d'insertion", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Tous les champs doivent etre remplis", Toast.LENGTH_SHORT).show();
            }

        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(MainActivity.this, ManagingDB.class);
                startActivity(int1);
            }
        });


    }

}