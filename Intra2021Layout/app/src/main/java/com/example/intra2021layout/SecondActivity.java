package com.example.intra2021layout;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class SecondActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);

        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_page1) {
                startActivity(new Intent(this, MainActivity.class));
            } else if (itemId == R.id.menu_page2) {
                startActivity(new Intent(this, SecondActivity.class));
            } else if (itemId == R.id.menu_page3) {
                startActivity(new Intent(this, ThirdActivity.class));
            }
            drawerLayout.closeDrawers();
            return true;
        });
    }
}
