package com.example.alex.rasenshuriken;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class TopArticles extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
TextView lines;
FirebaseUser currentUser;
FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_articles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (currentUser!=null) {
            updateNavHeader();
        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(this,TopArticles.class));
        } else if (id == R.id.nav_articles) {
            startActivity(new Intent(this,ListArticles.class));
        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(this,UserProfile.class));
        } else if (id == R.id.nav_upload) {
            if(currentUser.getDisplayName()==null) {
                startActivity(new Intent(this, MainActivity.class));
            }
            else{
                startActivity(new Intent(this, UplArticles.class));
            }

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void btnRedirect(View v){
        Intent i = new Intent(TopArticles.this,LessonActivity.class);
        startActivity(i);
    }

    public void updateNavHeader(){
        if(currentUser.getDisplayName()!=null) {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            View headerView = navigationView.getHeaderView(0);
            TextView navUsername = headerView.findViewById(R.id.usernameNavView);
            ImageView navUserPhoto = headerView.findViewById(R.id.ImageNavView);

            navUsername.setText(currentUser.getDisplayName());
            if(currentUser.getPhotoUrl()!=null) {
                Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhoto);
            }else{
                Glide.with(this).load(R.drawable.logo);
            }
        }

    }


}
