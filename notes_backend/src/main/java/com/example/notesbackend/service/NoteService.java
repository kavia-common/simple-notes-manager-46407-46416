package com.example.notesbackend.service;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.dto.NoteResponse;

import java.util.List;
import java.util.Optional;

/**
 * PUBLIC_INTERFACE
 * Service interface for managing notes.
 */
public interface NoteService {

    // PUBLIC_INTERFACE
    /** Returns all notes. */
    List<NoteResponse> findAll();

    // PUBLIC_INTERFACE
    /** Returns a note by id if present. */
    Optional<NoteResponse> findById(Long id);

    // PUBLIC_INTERFACE
    /** Creates a new note from the given request. */
    NoteResponse create(NoteRequest request);

    // PUBLIC_INTERFACE
    /** Updates the note with the given id using data from the request. */
    Optional<NoteResponse> update(Long id, NoteRequest request);

    // PUBLIC_INTERFACE
    /** Deletes the note by id. Returns true if deleted, false if not found. */
    boolean delete(Long id);
}
