package com.palinka.finno_ugric_lutemon;
/**
 * LutemonWrapper class is used to serialize Lutemon objects for saving them.
 * It contains the color and data of the Lutemon.
 */
public class LutemonWrapper {
    //This class helps serializing the lutemon objects for saving them

    String color;
    Lutemon data;

    LutemonWrapper(String color, Lutemon data) {
        this.color = color;
        this.data = data;
    }
}
