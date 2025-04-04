package com.palinka.finno_ugric_lutemon;

public class Home {
    //I still don't know how to implement this class, maybe it should be in the mainActivity

    public Lutemon createLutemon(String type, String name) {
        switch (type) {
            case "Black":
                Black black = new Black();
                black.setName(name);
                storage.addLutemon(black);
                return black;
            case "Orange":
                Orange orange = new Orange();
                orange.setName(name);
                return orange;
            case "Green":
                Green green = new Green();
                green.setName(name);
                return green;
            case "Pink":
                Pink pink = new Pink();
                pink.setName(name);
                return pink;
            case "White":
                White white = new White();
                white.setName(name);
                return white;
            case "Chromatic":
                Chromatic chromatic = new Chromatic();
                chromatic.setName(name);
                return chromatic;
            default:
                throw new IllegalArgumentException("Unknown color: " + type);
        }
    }

}
