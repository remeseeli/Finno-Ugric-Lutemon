package com.palinka.finno_ugric_lutemon;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LutemonFileHandler implements FileHandler {
    private final String fileName = "lutemons.json";
    private final Gson gson = new Gson();
    //Asked the AI Claude for help with this method.

    @Override
    public void saveToFile (Context context) {
        try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
             OutputStreamWriter writer = new OutputStreamWriter(fos);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            HashMap<Integer, Lutemon> lutemons = Storage.getInstance().getLutemonMap();

            ArrayList<LutemonWrapper> wrappedLutemons = new ArrayList<>();
            for (Lutemon lutemon : lutemons.values()) {
                wrappedLutemons.add(new LutemonWrapper(lutemon.getColor(), lutemon));
            }

            String json = gson.toJson(wrappedLutemons);
            bufferedWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Asked the AI Claude for help with this method.
    @Override
    public void loadFromFile(Context context) {
        try (FileInputStream fis = context.openFileInput(fileName);
             InputStreamReader reader = new InputStreamReader(fis);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            String json = jsonBuilder.toString();

            // Check for empty file content
            if (json == null || json.trim().isEmpty()) {
                // Optional: Clear existing lutemons if loading from empty file
                Storage.getInstance().getLutemonMap().clear();
                return;
            }

            JsonElement parsed = JsonParser.parseString(json);
            if (!parsed.isJsonArray()) {
                throw new IllegalStateException("Expected a JSON array.");
            }

            JsonArray jsonArray = parsed.getAsJsonArray();
            HashMap<Integer, Lutemon> loadedMap = new HashMap<>();
            int id = 0;

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

                if (lutemon != null) {
                    loadedMap.put(id++, lutemon);
                }
            }

            Storage.getInstance().getLutemonMap().clear();
            Storage.getInstance().getLutemonMap().putAll(loadedMap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Asked the AI Claude for help with this method, and the help was about to write and empty array instead of "nothing"
    @Override
    public void clearFile (Context context) {
        try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
            fos.write("[]".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
