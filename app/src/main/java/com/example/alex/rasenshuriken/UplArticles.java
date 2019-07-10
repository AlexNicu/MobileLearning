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
Button back,next;
public int ok=0;
public int subok=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upl_articles);

        Spinner spinnerTest = (Spinner) findViewById(R.id.spinnerTest);
        Spinner spinnersubTest=findViewById(R.id.spinnersubTest);
        back=findViewById(R.id.back);
        next=findViewById(R.id.next);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UplArticles.this, TopArticles.class));
            }
        });



        ArrayAdapter<CharSequence> adapterTest = ArrayAdapter.createFromResource(this,R.array.spinnerTest, android.R.layout.simple_spinner_item);
        adapterTest.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTest.setAdapter(adapterTest);
        spinnerTest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sSelected=parent.getItemAtPosition(position).toString();
                SharedPreferences sharedpref3=getSharedPreferences("uploadFile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor3=sharedpref3.edit();
                String domain ;
                switch(sSelected) {
                    case "Economy":
                        domain="Economy";
                        ok=1;
                        ArrayAdapter<CharSequence> EconomyAdapter = ArrayAdapter.createFromResource(UplArticles.this,R.array.Economy, android.R.layout.simple_spinner_item);
                        spinnersubTest.setAdapter(EconomyAdapter);
                        spinnersubTest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String selected=parent.getItemAtPosition(position).toString();
                                String subdomain="";
                                switch (selected){
                                    case "Microeconomy":
                                       subdomain="Microeconomy";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=1;
                                        }
                                        break;
                                    case"Macroeconomy":
                                        subdomain="Macroeconomy";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=1;
                                        }
                                        break;
                                    case"Select a subject" :
                                        subdomain="Select a subject";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=0;
                                        }
                                    default:
                                        subdomain="Select a subject";
                                }
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                        break;
                    case "Mathematics":
                        domain="Mathematics";
                        ok=1;
                        ArrayAdapter<CharSequence> MathematicsAdapter = ArrayAdapter.createFromResource(UplArticles.this,R.array.Mathematics, android.R.layout.simple_spinner_item);
                        spinnersubTest.setAdapter(MathematicsAdapter);
                        spinnersubTest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void  onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String subdomain="";
                                String selected=parent.getItemAtPosition(position).toString();
                                switch (selected){
                                    case "Algebra":
                                        subdomain="Algebra";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=1;
                                        }
                                        break;
                                    case"Mathematical Analysis":
                                        subdomain="Mathematical Analysis";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=1;
                                        }
                                        break;
                                    case "Select a subject" :
                                        subdomain="Select a subject";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=0;
                                        }
                                    default:
                                        subdomain="Select a subject";
                            }
                        }
                         @Override
                          public void onNothingSelected(AdapterView<?> parent) {

                         }
                           });
                        break;
                    case "Physics":
                        domain="Physics";
                        ok=1;
                        ArrayAdapter<CharSequence> PhysicsyAdapter = ArrayAdapter.createFromResource(UplArticles.this,R.array.Physics, android.R.layout.simple_spinner_item);
                        spinnersubTest.setAdapter(PhysicsyAdapter);
                        spinnersubTest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String subdomain="";
                                String selected=parent.getItemAtPosition(position).toString();
                                switch (selected){
                                    case "Daily Physics":
                                        subdomain="Daily Physics";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=1;
                                        }
                                        break;
                                    case "Gravitational Physics":
                                        subdomain="Gravitational Physics";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=1;
                                        }
                                        break;
                                    case "Basic Physics":
                                        subdomain="Basic Physics";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=1;
                                        }
                                        break;
                                    case "Select a subject":
                                        subdomain="Select a subject";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=0;
                                        }
                                    default:
                                        subdomain="Select a subject";
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case "Programming":
                        domain="Programming";
                        ok=1;
                        ArrayAdapter<CharSequence> ProgrammingAdapter = ArrayAdapter.createFromResource(UplArticles.this,R.array.Programming, android.R.layout.simple_spinner_item);
                        spinnersubTest.setAdapter(ProgrammingAdapter);
                        spinnersubTest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String subdomain="";
                                String selected=parent.getItemAtPosition(position).toString();
                                switch (selected){
                                    case "C#":
                                        subdomain="C#";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=1;
                                        }
                                        break;
                                    case"C++":
                                        subdomain="C++";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=1;
                                        }
                                        break;
                                    case"Java":
                                        subdomain="Java";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=1;
                                        }
                                        break;
                                    case"Python":
                                        subdomain="Python";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=1;
                                        }
                                        break;
                                    case "Select a subject":
                                        subdomain="Select a subject";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=0;
                                        }
                                    default :
                                        subdomain="Select a subject";
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;

                    case "Biology":
                        domain="Biology";
                        ok=1;
                        ArrayAdapter<CharSequence> BiologyAdapter = ArrayAdapter.createFromResource(UplArticles.this,R.array.Biology, android.R.layout.simple_spinner_item);
                        spinnersubTest.setAdapter(BiologyAdapter);
                        spinnersubTest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String subdomain="";
                                String selected=parent.getItemAtPosition(position).toString();
                                switch (selected){
                                    case "Animal Anatomy":
                                        subdomain="Animal Anatomy";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=1;
                                        }
                                        break;
                                    case"Vegetal Anatomy":
                                        subdomain="Vegetal Anatomy";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=1;
                                        }
                                        break;
                                    case"Human Anatomy":
                                        subdomain="Human Anatomy";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=1;
                                        }
                                        break;

                                    case "Select a subject":
                                        subdomain="Select a subject";
                                        if(subdomain != "") {
                                            editor3.putString("subfilename", subdomain);
                                            subok=0;
                                        }
                                        break;
                                    default:
                                        subdomain="Select a subject";
                                }
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                        break;
                    case "Select a topic":
                        ok=0;
                        domain="Select a topic";
                        break;
                    default:
                        domain="Select a topic";

                }


                editor3.putString("filename",domain);
                editor3.apply();

               // Toast.makeText(UplArticles.this,sSelected,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if((ok!=0 )&&(subok!=0)){
                    startActivity(new Intent(UplArticles.this, TitleScreen.class));
                }
                else if (ok==0){
                    Toast.makeText(UplArticles.this,"A topic must be selected",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(UplArticles.this,"A subject must be selected",Toast.LENGTH_LONG).show();
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

