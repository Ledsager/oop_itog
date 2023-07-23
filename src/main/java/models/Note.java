package models;

public class Note {

    private int id;

    private String title;

    private String content;

    public Note(String title, String content) {
//        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Note(int id, String title, String content) {
        this(title,content);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.length()>100){
            throw new RuntimeException("To long title");
        }
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString(){
        return "Notes{" +
                "id='" + id + '\'' +
                ", title=" + title +
                ", content=" + content +
                '}';
    }
}
