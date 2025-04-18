package com.palinka.finno_ugric_lutemon;

public class LutemonWrapper {
    //This class helps serializing the lutemon objects for saving them

    String color;
    Lutemon data;

    LutemonWrapper(String color, Lutemon data) {
        this.color = color;
        this.data = data;
    }
}
