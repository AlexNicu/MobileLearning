package com.example.alex.rasenshuriken;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;


public class UplArticles extends AppCompatActivity {
    Button back, next;
    int idArraySubdomains = 0;
    int ok = 1, subok = 1;
    Spinner spinnerTest, spinnersubTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upl_articles);

        spinnerTest = (Spinner) findViewById(R.id.spinnerTest);
        spinnersubTest = findViewById(R.id.spinnersubTest);
        back = findViewById(R.id.back);
        next = findViewById(R.id.next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UplArticles.this, TopArticles.class));
            }
        });

        ArrayAdapter<CharSequence> adapterTest = ArrayAdapter.createFromResource(this, R.array.spinnerTest, android.R.layout.simple_spinner_item);
        adapterTest.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTest.setAdapter(adapterTest);
        spinnerTest.setSelection(0,false);//sa nu faca fire pe ontiemselected la intializare!


        spinnerTest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String domain = parent.getItemAtPosition(position).toString();
                SharedPreferences sharedpref3 = getSharedPreferences("uploadFile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor3 = sharedpref3.edit();

                switch (domain) {
                    case "Economy":
                        idArraySubdomains = R.array.Economy;
                        break;
                    case "Mathematics":
                        idArraySubdomains = R.array.Mathematics;
                        break;
                    case "Physics":
                        idArraySubdomains = R.array.Physics;
                        break;
                    case "Programming":
                        idArraySubdomains = R.array.Programming;
                        break;
                    case "Biology":
                        idArraySubdomains = R.array.Biology;
                        break;
                    case "Select a topic":
                        ok = 0;
                        break;
                }

                if (ok != 0) {


                    editor3.putString("filename", domain);
                    editor3.apply();

                    ArrayAdapter<CharSequence> subdomainAdapter = ArrayAdapter.createFromResource(UplArticles.this, idArraySubdomains, android.R.layout.simple_spinner_item);
                    spinnersubTest.setAdapter(subdomainAdapter);
                    spinnersubTest.setSelection(0,false);//sa nu faca fire pe ontiemselected la intializare!
                    spinnersubTest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String subdomain = parent.getItemAtPosition(position).toString();

                            if (subdomain.equals("Select a subject")) {
                                subok = 0;
                            } else {
                                editor3.putString("subfilename", subdomain);
                                editor3.apply();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((ok != 0) && (subok != 0)) {
                    startActivity(new Intent(UplArticles.this, TitleScreen.class));
                }
                if (ok == 0) {
                    Toast.makeText(UplArticles.this, "A topic must be selected", Toast.LENGTH_LONG).show();
                } else if (subok == 0) {
                    Toast.makeText(UplArticles.this, "A subject must be selected", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}


//spinner cu domeniu ---> onitemselected apare sub-domeniu
//spiner cu sub-domeniu

//in loc de onitemselected -> un buton "adauga articol"
//obligare setare profil
//top articole -> daca e gol, un view in care sa spuna ca nu sunt articole in acest moment -->pe jumatate, doar vizual facut, nu si in spate
//butonul de upload sa fie in butonul de next page ---> finalizat
//buton de finalizare cu pop up :articolul a fost publicat --->finalizat

