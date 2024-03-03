package com.example.organicagriculture;

import static com.example.organicagriculture.R.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        drawerLayout = findViewById(id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(id.toolbar);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(HomePage.this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationActions();

        loadFragment(new AllProducts());
    }

    private void navigationActions() {
        navigationView.setNavigationItemSelectedListener(item -> {

            int itemId = item.getItemId();

            switch (itemId) {
                case R.id.home:
                    loadFragment(new AllProducts());
                    break;
                case R.id.lure:
                    loadFragment(new Lure());
                    break;
                case id.trap:
                    loadFragment(new Traps());
                    break;
                case id.logout:
                    Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(this,LogIn.class);
                    startActivity(intent);
                    finish();
                    break;
                case id.share:
                    Toast.makeText(this, "Sharing", Toast.LENGTH_SHORT).show();
                    break;
                case id.about:
                    Toast.makeText(this, "About us", Toast.LENGTH_SHORT).show();
                    loadFragment(new About());
                    break;
                default:
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id.constraintLayout, fragment);
        ft.commit();
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}