package services;

import models.Note;
import persistense.NoteData;
import persistense.Repository;
import java.util.List;

public class NotesService {
    private final Repository notesRepository;

    public NotesService(Repository notesRepository) {

        this.notesRepository = notesRepository;
    }
    public void saveNote(Note note) throws Exception {
        notesRepository.createNote(note);
    }
    public void deleteNoteId(int note_id) throws Exception {
        notesRepository.delNote(note_id);

    }
    public void updateNote(Note note, NoteData data, String param) throws Exception {
        notesRepository.getNoteById(note, data, param);

    }
    public Note readNote(int noteId) throws Exception {
        List<Note> notes = notesRepository.getAllNotes();
        for (Note date : notes) {
            if (date.getId()==noteId) {
                return date;
            }
        }

        throw new Exception("User not found");
    }
    public List<Note> getNotes() throws Exception {
        return notesRepository.getAllNotes();
    }
}

