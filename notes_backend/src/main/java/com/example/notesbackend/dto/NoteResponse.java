package com.example.notesbackend.dto;

import java.time.Instant;

/**
 * PUBLIC_INTERFACE
 * Represents a Note returned by the API.
 */
public class NoteResponse {
    private Long id;
    private String title;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;

    public NoteResponse() {
    }

    public NoteResponse(Long id, String title, String content, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // PUBLIC_INTERFACE
    /** Returns the note id. */
    public Long getId() {
        return id;
    }

    public NoteResponse setId(Long id) {
        this.id = id;
        return this;
    }

    // PUBLIC_INTERFACE
    /** Returns the note title. */
    public String getTitle() {
        return title;
    }

    public NoteResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    // PUBLIC_INTERFACE
    /** Returns the note content. */
    public String getContent() {
        return content;
    }

    public NoteResponse setContent(String content) {
        this.content = content;
        return this;
    }

    // PUBLIC_INTERFACE
    /** Returns when the note was created. */
    public Instant getCreatedAt() {
        return createdAt;
    }

    public NoteResponse setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    // PUBLIC_INTERFACE
    /** Returns when the note was last updated. */
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public NoteResponse setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
