package com.palinka.finno_ugric_lutemon.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.palinka.finno_ugric_lutemon.Lutemon;
import com.palinka.finno_ugric_lutemon.R;
import com.palinka.finno_ugric_lutemon.StatisticAdapter;
import com.palinka.finno_ugric_lutemon.Storage;

import java.util.ArrayList;

public class StatisticActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StatisticAdapter adapter;
    private ArrayList<Lutemon> lutemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statrecycler);

        recyclerView = findViewById(R.id.statRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Storage storage = Storage.getInstance();
        lutemonList = new ArrayList<>(storage.getLutemonMap().values());

        adapter = new StatisticAdapter(lutemonList);
        recyclerView.setAdapter(adapter);
    }
}