package com.palinka.finno_ugric_lutemon;

import java.util.HashMap;
import java.util.Map;
//Interface for file handling
public interface FileHandler {
    void saveToFile(String fileName, HashMap<Integer, Lutemon> data);
    HashMap<Integer, Lutemon> loadFromFile(String fileName);
    void clearFile(String fileName);
}
