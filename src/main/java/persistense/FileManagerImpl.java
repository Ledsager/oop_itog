package persistense;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import models.Note;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileManagerImpl implements FileManager {

    private String fileName;

    public FileManagerImpl(String fileName) {
        this.fileName = fileName;
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Note> readAllLines() {

        List<Note> lines = new ArrayList<>();
        try (
                JsonReader jsonReader = new JsonReader(
                        new InputStreamReader(
                                new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

//            if(jsonReader.hasNext()){
//                System.out.println("test");
//                Note note = gson.fromJson(jsonReader, Note.class);
//                lines.add(note);
//            }

            jsonReader.beginArray(); //start of json array

            while (jsonReader.hasNext()) { //next json array element
                Note note = gson.fromJson(jsonReader, Note.class);
                lines.add(note);
            }
            jsonReader.endArray();

            jsonReader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return lines;
    }

    @Override
    public void saveAllLines(List<Note> lines) {

        try (JsonWriter out = new JsonWriter(new FileWriter(fileName))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = null;
            out.beginArray();

            for (Note line : lines) {
                jsonString = gson.toJson(line);
                out.jsonValue(jsonString);
            }
            out.endArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
