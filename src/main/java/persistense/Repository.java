package persistense;

import models.Note;

import java.util.List;

public interface Repository {
    public int createNote(Note note);
    public void getNoteById(Note note, NoteData data, String param);
    public List<Note> getAllNotes();
    public void delNote(int note_id);

}
