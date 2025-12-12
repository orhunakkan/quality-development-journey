package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    private String id;
    private String title;
    private String description;
    private String category;
    private Boolean completed;
    private String created_at;
    private String updated_at;
    private String user_id;

    public Note(String title, String description, String category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }
}
