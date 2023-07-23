package persistense;

import models.Note;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl implements Repository{

    private FileManager fileManager;
    public RepositoryImpl(FileManager fileManager) {
    this.fileManager=fileManager;

    }
    @Override
    public int createNote(Note note) {
        List<Note> notes = getAllNotes();
        int max = 0;
        for (Note item : notes) {
            int id = item.getId();
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        note.setId(newId);
        notes.add(note);
        List<Note> lines = new ArrayList<>();
        for (Note item: notes) {
            lines.add(item);
        }
        fileManager.saveAllLines(lines);
        return newId;
    }
    @Override
    public List<Note> getAllNotes() {
        List<Note> lines = fileManager.readAllLines();
        return lines;
    }
    @Override
    public void getNoteById(Note note, NoteData data, String param) {
        if(data == NoteData.TITLE) {
            note.setTitle(param);
        }
        else if(data == NoteData.CONTENT) {
            note.setContent(param);
        }
        saveNote(note);
    }
    private void saveNote(Note note) {
        List<Note> lines = new ArrayList<>();
        List<Note> notes = getAllNotes();
        for (Note item: notes) {
            if(note.getId()==item.getId()) {
                lines.add(note);
            }
            else {
                lines.add(item);
            }
        }
        fileManager.saveAllLines(lines);
    }

    @Override
    public void delNote(int note_id) {
        List<Note> lines = new ArrayList<>();
        List<Note> notes = getAllNotes();
        for (Note item: notes) {
            if(item.getId()!=note_id) {
                lines.add(item);
            }
        }
        fileManager.saveAllLines(lines);
    }
}
