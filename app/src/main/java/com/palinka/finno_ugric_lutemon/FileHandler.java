package com.palinka.finno_ugric_lutemon;

import android.view.View;

import java.util.HashMap;

//Interface for file handling
public interface FileHandler {
    void saveToFile(View.OnClickListener context, String fileName, HashMap<Integer, Lutemon> data);
    HashMap<Integer, Lutemon> loadFromFile(String fileName);
    void clearFile(String fileName);
}
