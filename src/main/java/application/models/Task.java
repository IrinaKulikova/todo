package application.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class Task implements Serializable {
    int id;
    String title;
    String description;
    Boolean done;

    public Task(String title, String description, Boolean done) {
        this.title = title;
        this.description = description;
        this.done = done;
    }

    public Task() {
    }

    public Task(int id, String title, String description, Boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
    }
}
