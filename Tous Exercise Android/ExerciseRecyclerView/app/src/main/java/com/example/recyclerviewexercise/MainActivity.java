package com.example.recyclerviewexercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Random;

// Une liste  <-> un seul adapter <-> un seul recyclerView

public class MainActivity extends AppCompatActivity {

    PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialisation du recycler
        this.initRecycler();
        this.remplirRecycler();

        this.remplacer();
    }

    private void remplacer(){
        adapter.list.clear();
        for (int i = 0 ; i < 10 ; i++) {
            Person p = new Person();
            p.nom = "Charles " + i;
            p.age = 20 + (new Random().nextInt(20));
            adapter.list.add(p);
        }
        adapter.notifyDataSetChanged();
    }


    private void remplirRecycler() {
        for (int i = 0 ; i < 100 ; i++) {
            Person p = new Person();
            p.nom = "Bob " + i;
            p.age = 20 + (new Random().nextInt(20));
            adapter.list.add(p);
        }
        adapter.notifyDataSetChanged();
    }

    private void initRecycler(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        adapter = new PersonAdapter();
        recyclerView.setAdapter(adapter);
    }
}