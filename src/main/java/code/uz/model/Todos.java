package code.uz.model;

import code.uz.model.Status;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Todos {
    private Integer id;
    private String title;
    private String content;
    private Status status;
    private LocalDateTime created_date;
    private Timestamp finished_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public Timestamp getFinished_date() {
        return finished_date;
    }

    public void setFinished_date(Timestamp finished_date) {
        this.finished_date = finished_date;
    }

    @Override
    public String toString() {
        return "Todos{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", created_date=" + created_date +
                ", finished_date=" + finished_date +
                '}';
    }
}
