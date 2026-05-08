package diary.dto;

import java.time.LocalDate;

public class DiaryEntry {
    private int id;
    private String title;
    private String content;
    private LocalDate date;

    public DiaryEntry(int id, String title, String content, LocalDate date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public LocalDate getDate() { return date; }
}
