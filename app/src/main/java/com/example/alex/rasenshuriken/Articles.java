package com.example.alex.rasenshuriken;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Articles extends AppCompatActivity {
    Button back, next;
    int idArraySubdomains = 0;
    int ok = 1, subok = 1;
    Spinner spinnerTest, spinnersubTest;

    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    public static final String checkDomeniu="domain";
    public static final String checksubDomeniu="subdomain";
    public static final String LESSON_ID="lessonID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        spinnerTest = (Spinner) findViewById(R.id.spinnerTest);
        spinnersubTest = findViewById(R.id.spinnersubTest);
        back = findViewById(R.id.back);
        next = findViewById(R.id.next);

        Intent intent=getIntent();
        String id=intent.getStringExtra(TitleScreen.LESSON_ID);
        Intent intent2=new Intent(getApplicationContext(), LessonActivity.class);
        intent2.putExtra(LESSON_ID, id);

        mAuth= FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Articles.this, TopArticles.class));
            }
        });
        Intent intentL=new Intent(getApplicationContext(), ListArticles.class);
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
                intentL.putExtra(checkDomeniu, domain);
                if (ok != 0) {


                    editor3.putString("filename", domain);
                    editor3.apply();

                    ArrayAdapter<CharSequence> subdomainAdapter = ArrayAdapter.createFromResource(Articles.this, idArraySubdomains, android.R.layout.simple_spinner_item);
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
                            intentL.putExtra(checksubDomeniu,subdomain);
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
                    startActivity(intentL);
                }
                if (ok == 0) {
                    Toast.makeText(Articles.this, "A topic must be selected", Toast.LENGTH_LONG).show();
                } else if (subok == 0) {
                    Toast.makeText(Articles.this, "A subject must be selected", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.activities_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case  R.id.nav_home:
                startActivity(new Intent(this,TopArticles.class));
                break;
            case R.id.nav_articles:
                startActivity(new Intent(this,Articles.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(this,UserProfile.class));
                break;
            case R.id.nav_upload:
                if (currentUser.getDisplayName() != null) {
                    startActivity(new Intent(this, UplArticles.class));
                }else{
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;



        }
        return super.onOptionsItemSelected(item);
    }

}


