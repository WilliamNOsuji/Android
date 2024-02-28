package com.example.intra2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button scdBtn;
    NavigationView nav;
    DrawerLayout dl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().show();

        scdBtn = findViewById(R.id.button2);
        scdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

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