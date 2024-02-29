package org.depinfo.intra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import org.depinfo.intra.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavigationView nav;
    private DrawerLayout drawerLayout;
    private TextInputEditText idPoke;
    private Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        drawerLayout = findViewById(R.id.drawerlayout);
        nav = findViewById(R.id.nav_View);

        nav.setNavigationItemSelectedListener(item -> {
            int itemID = item.getItemId();
            if(itemID == R.id.page1){
                startActivity(new Intent(this, MainActivity.class));
            } else if (itemID == R.id.page2) {
                startActivity(new Intent(this, SecondActivity.class));
            }
            drawerLayout.closeDrawers();
            return false;
        });

        btnGo = binding.button;
        idPoke = binding.idPoke;

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(idPoke.getText().toString()) > 0
                        && Integer.parseInt(idPoke.getText().toString()) < 1009){
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("idPoke", Integer.parseInt(idPoke.getText().toString()));
                    startActivity(intent);
                } else{
                    Toast.makeText(MainActivity.this,"This Pokemon id is invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}