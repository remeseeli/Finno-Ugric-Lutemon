package com.palinka.finno_ugric_lutemon;

import android.content.Context;
import android.view.View;

import java.util.HashMap;

//Interface for file handling
public interface FileHandler {
    void saveToFile(Context context);
    void loadFromFile(Context context);
    void clearFile(Context context);
}
