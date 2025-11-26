package com.example.notesbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * PUBLIC_INTERFACE
 * Represents the payload for creating or updating a note.
 */
public class NoteRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must be at most 200 characters")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    public NoteRequest() {
    }

    public NoteRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // PUBLIC_INTERFACE
    /** Returns the title for the note. */
    public String getTitle() {
        return title;
    }

    public NoteRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    // PUBLIC_INTERFACE
    /** Returns the content for the note. */
    public String getContent() {
        return content;
    }

    public NoteRequest setContent(String content) {
        this.content = content;
        return this;
    }
}
