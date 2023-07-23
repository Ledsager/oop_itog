//          Написать проект, "Записки", содержащий работу с записками из консоли
//        (можно прочитать все записки, создать одну записку, отредактировать записку,
//        удалить, и прочитать одну записку по ID), записка содержит в себе как минимум 3 поля Id,
//        заголовок и текст, можно добавить дату. Хранение по вашему выбору, можно в текстовом файле
//        (или каждая записка в одном файле, как вам удобнее). Можно сделать по аналогии с программой
//        из 5 урока. Если все не успеете, то часть задания можно перенести на следующий урок

package notes;

import models.Note;
import persistense.FileManager;
import persistense.FileManagerImpl;
import persistense.Repository;
import persistense.RepositoryImpl;
import services.NotesService;
import ui.Console;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManagerImpl("users.json");
        Repository notesrepository = new RepositoryImpl(fileManager);
        NotesService notesService = new NotesService(notesrepository);
        Console view = new Console(notesService);
        view.run();


//        List<Note> lines = fileManager.readAllLines();
//        for (Note item : lines) {
//            System.out.println(item.getId());
//            System.out.println(item);
//        }
//        Note note1=new Note(10,"Test","Helo helo");
//        List<Note> notes=new ArrayList<>();
//        lines.add(note1);
//        fileManager.saveAllLines(lines);
    }
}
