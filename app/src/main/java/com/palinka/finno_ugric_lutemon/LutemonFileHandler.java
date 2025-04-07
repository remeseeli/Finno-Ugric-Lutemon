package com.palinka.finno_ugric_lutemon;

import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class LutemonFileHandler implements FileHandler {

    /**
     * This method saves the content of the HashMap to the .ser file.
     * @param fileName
     * @param data
     */
    @Override
    public void saveToFile(String fileName, HashMap<Integer, Lutemon> data) {
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName))) {
            writer.writeObject(data); //Write the data(the id and the lutemon object) into a .ser file (hopefully)
        } catch (IOException e){
            System.out.println("Writing was interrupted due to: " + e.getMessage());
        }
    }

    /**
     * This method loads the lutemon objects back from the .ser file to the HashMap.
     * @param fileName
     */
    @Override
    public HashMap<Integer, Lutemon> loadFromFile(String fileName) {
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName))) {
            return (HashMap<Integer, Lutemon>) reader.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Loading was interrupted due to: " + e.getMessage());
        }
        return new HashMap<>(); //In case loading it interrupted or failed, it will return an empty HashMap
    }

    /**
     * This method is to clear the chosen .ser file, allowing to reuse the save "slots".
     * @param fileName
     */
    @Override
    public void clearFile(String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(new byte[0]);
        } catch (IOException e) {
            System.out.println("Cleaning the file was interrupted due to: " + e.getMessage());
        }
    }
}
