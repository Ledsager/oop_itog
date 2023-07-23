package persistense;

import models.Note;

import java.util.List;

public interface FileManager {

    List<Note> readAllLines();

    void saveAllLines(List<Note> lines);
}
