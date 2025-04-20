package com.palinka.finno_ugric_lutemon;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * LutemonFileHandler handles saving, loading, and clearing Lutemon data
 * using JSON serialization with Gson.
 */
public class LutemonFileHandler implements FileHandler {

    private final String fileName = "lutemons.json";
    private final Gson gson = new Gson();

    /**
     * Saves the current Lutemon data to a JSON file.
     * I have got help from AI in order to understand how to write the list into a file
     */
    @Override
    public void saveToFile(Context context) {
        try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
             OutputStreamWriter writer = new OutputStreamWriter(fos);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            // Get all Lutemons from storage
            HashMap<Integer, Lutemon> lutemons = Storage.getInstance().getLutemonMap();

            // Wrap each Lutemon with its color to preserve subclass type for serialization (because of using json)
            ArrayList<LutemonWrapper> wrappedLutemons = new ArrayList<>();
            for (Lutemon lutemon : lutemons.values()) {
                wrappedLutemons.add(new LutemonWrapper(lutemon.getColor(), lutemon));
            }

            // Convert wrapped Lutemon list to JSON and write to file
            String json = gson.toJson(wrappedLutemons);
            bufferedWriter.write(json);

        } catch (IOException e) {
            e.printStackTrace(); // Print error if saving fails for some reason (IO is the most )
        }
    }

    /**
     * Loads Lutemon data from the JSON file and restores it into storage.
     * I have used AI help to deserialise the data.
     */
    @Override
    public void loadFromFile(Context context) {
        try (FileInputStream fis = context.openFileInput(fileName);
             InputStreamReader reader = new InputStreamReader(fis);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            // Read the entire JSON content into a string
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            String json = jsonBuilder.toString();

            // If file is empty, clear the current Lutemon map and return
            if (json == null || json.trim().isEmpty()) {
                Storage.getInstance().getLutemonMap().clear();
                return;
            }

            // Parse the JSON content as a JSON array
            JsonElement parsed = JsonParser.parseString(json);
            if (!parsed.isJsonArray()) {
                throw new IllegalStateException("Expected a JSON array.");
            }

            JsonArray jsonArray = parsed.getAsJsonArray();
            HashMap<Integer, Lutemon> loadedMap = new HashMap<>();
            int id = 0;

            // Deserialize each wrapped Lutemon based on its color
            for (JsonElement element : jsonArray) {
                JsonObject wrapperObj = element.getAsJsonObject();
                String color = wrapperObj.get("color").getAsString();
                JsonObject data = wrapperObj.get("data").getAsJsonObject();

                Lutemon lutemon = null;
                switch (color.toLowerCase()) {
                    case "pink":
                        lutemon = gson.fromJson(data, Pink.class);
                        break;
                    case "green":
                        lutemon = gson.fromJson(data, Green.class);
                        break;
                    case "black":
                        lutemon = gson.fromJson(data, Black.class);
                        break;
                    case "white":
                        lutemon = gson.fromJson(data, White.class);
                        break;
                    case "orange":
                        lutemon = gson.fromJson(data, Orange.class);
                        break;
                }

                // If successfully deserialized, add to the map with a generated ID
                if (lutemon != null) {
                    loadedMap.put(id++, lutemon);
                }
            }

            // Replace the current Lutemon map with the loaded data
            Storage.getInstance().getLutemonMap().clear();
            Storage.getInstance().getLutemonMap().putAll(loadedMap);

        } catch (IOException e) {
            e.printStackTrace(); // Print error if loading fails
        }
    }

    /**
     * Clears the Lutemon JSON file by writing an empty array ("[]").
     * I used AI help to clear the file and load back the empty array.
     * My previous solution caused crashes.
     */
    @Override
    public void clearFile(Context context) {
        try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
            // Write empty array to clear existing data
            fos.write("[]".getBytes());
        } catch (IOException e) {
            e.printStackTrace(); // Print error if clearing fails
        }
    }
}