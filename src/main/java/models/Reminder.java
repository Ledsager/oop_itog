package models;

import java.time.LocalDateTime;

public class Reminder extends Note{
    private LocalDateTime alertDateTime;

    public Reminder(String title, String content) {
        super(title, content);
    }
}
