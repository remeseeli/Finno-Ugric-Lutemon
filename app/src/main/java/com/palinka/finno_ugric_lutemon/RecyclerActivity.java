package com.palinka.finno_ugric_lutemon;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LutemonAdapter adapter;
    private ArrayList<Lutemon> lutemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView = findViewById(R.id.lutemonRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Storage storage = Storage.getInstance();
        lutemonList = new ArrayList<>(storage.getLutemonMap().values());

        adapter = new LutemonAdapter(lutemonList);
        recyclerView.setAdapter(adapter);
    }
}
