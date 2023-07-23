package ui;

import models.Note;
import persistense.NoteData;
import services.NotesService;

import java.util.Scanner;

public class Console {
    private final NotesService notesService;

    public Console(NotesService notesService) {

        this.notesService = notesService;
    }
    public void run(){
        Commands com = Commands.NONE;
        showHelp();
        while (true) {
            try {
                String command = prompt("Введите команду: ");
                com = Commands.valueOf(command.toUpperCase());
                if (com == Commands.EXIT) return;
                switch (com) {
                    case CREATE:
                        create();
                        break;
                    case READ:
                        read();
                        break;
                    case UPDATE:
                        update();
                        break;
                    case LIST:
                        list();
                        break;
                    case DELETE:
                        deleteNoteId();
                        break;
                    case HELP:
                        showHelp();
                }
            }
            catch(Exception ex) {
                System.out.println("Произошла ошибка " + ex.toString());
            }
        }
    }

    private void deleteNoteId() throws Exception{
        int id = Integer.parseInt(prompt("Идентификатор пользователя: "));
//        String user_id = userController.readUser(id);
        notesService.deleteNoteId(id);
    }

    private void read() throws Exception {
        int id = Integer.parseInt(prompt("Идентификатор id: "));
        Note note_ = notesService.readNote(id);
        System.out.println(note_);
    }
    private void update() throws Exception {
        int userid = Integer.parseInt(prompt("Идентификатор заметки: "));
        String field_name = prompt("Какое поле (title,content): ");
        String param = null;
            param = prompt("Введите то на что хотите изменить");
        Note _note = notesService.readNote(userid);
        notesService.updateNote(_note, NoteData.valueOf(field_name.toUpperCase()), param);
    }

    private void list() throws Exception {
        for (Note date : notesService.getNotes()) {
            System.out.println(date);
        }
    }
    private void create() throws Exception {
        String title = prompt("TITLE: ");
        String content = prompt("CONTENT: ");
        notesService.saveNote(new Note(title,content));

    }
    private void showHelp() {
        System.out.println("Список команд:");
        for(Commands c : Commands.values()) {
            System.out.println(c);
        }
    }
    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}


