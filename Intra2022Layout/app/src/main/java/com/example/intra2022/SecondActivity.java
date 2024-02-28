package com.example.intra2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class SecondActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private NavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        nav = findViewById(R.id.nav_view);
        dl = findViewById(R.id.drawerLayout);

        nav.setNavigationItemSelectedListener( item ->  {
            int itemID = item.getItemId();
            if(itemID == R.id.pageA){
                startActivity(new Intent(this, MainActivity.class));
            } else if (itemID == R.id.pageB) {
                startActivity(new Intent(this, SecondActivity.class));
            }
            dl.closeDrawers();
            return  true;
        });
    }
}