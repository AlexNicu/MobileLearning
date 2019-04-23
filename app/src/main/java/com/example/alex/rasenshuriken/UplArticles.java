package com.example.alex.rasenshuriken;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class UplArticles extends AppCompatActivity implements AdapterView.OnItemSelectedListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upl_articles);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        Spinner spinner5 = (Spinner) findViewById(R.id.spinner5);


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.spinner1, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.spinner2, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.spinner3, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.spinner4, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        spinner4.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.spinner5, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);
        spinner5.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sSelected=parent.getItemAtPosition(position).toString();
        String subdomain;
        String domain ;
        switch(sSelected) {
            case "Microeconomie":
               subdomain="Microeconomie";
               domain="Economie";
                startActivity(new Intent(this,UploadActivity.class));
                break;
            case "Macroeconomie":
                subdomain="Macroeconomie";
                domain="Economie";
                startActivity(new Intent(this,UploadActivity.class));
                break;
            case "Algebra":
                subdomain="Algebra";
                domain="Matematica";
                startActivity(new Intent(this,UploadActivity.class));
                break;
            case "Analiza Matematica":
                subdomain="Analiza Matematica";
                domain="Matematica";
                startActivity(new Intent(this,UploadActivity.class));
                break;
            case "C#":
                subdomain="C#";
                domain="Limbaje de programare";
                startActivity(new Intent(this,UploadActivity.class));
                break;
            case "C++":
                subdomain="C++";
                domain="Limbaje de programare";
                startActivity(new Intent(this,UploadActivity.class));
                break;
            case "Java":
                subdomain="Java";
                domain="Limbaje de programare";
                startActivity(new Intent(this,UploadActivity.class));
                break;
            case "Python":
                subdomain="Python";
                domain="Limbaje de programare";
                startActivity(new Intent(this,UploadActivity.class));
                break;
            case "Daily Physics":
                subdomain="Daily Physics";
                domain="Fizica";
                startActivity(new Intent(this,UploadActivity.class));
                break;
            case "Gravitational Physics":
                subdomain="Gravitational Physics";
                domain="Fizica";
                startActivity(new Intent(this,UploadActivity.class));
                break;
            case "Basic Physics":
                subdomain="Basic Physics";
                domain="Fizica";
                startActivity(new Intent(this,UploadActivity.class));
                break;
            case "Anatomia animala":
                subdomain="Anatomia animala";
                domain="Biologie";
                startActivity(new Intent(this,UploadActivity.class));
                break;
            case "Anatomia vegetala":
                subdomain="Anatomia vegetala";
                domain="Biologie";
                startActivity(new Intent(this,UploadActivity.class));
                break;
            case "Anatomia omului":
                subdomain="Anatomia omului";
                domain="Biologie";
                startActivity(new Intent(this,UploadActivity.class));
                break;
            default:
                subdomain="Nualuatcase-ul";
                domain="Eroare";
        }
        SharedPreferences sharedpref3=getSharedPreferences("uploadFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor3=sharedpref3.edit();
        editor3.putString("subfilename",subdomain);
        editor3.putString("filename",domain);
        editor3.apply();

        Toast.makeText(this,sSelected,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
