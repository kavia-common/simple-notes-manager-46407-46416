package com.example.notesbackend.mapper;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.model.Note;

/**
 * Utility to map between Note entity and DTOs.
 */
public final class NoteMapper {

    private NoteMapper() {}

    // PUBLIC_INTERFACE
    /** Maps Note entity to NoteResponse. */
    public static NoteResponse toResponse(Note entity) {
        if (entity == null) return null;
        return new NoteResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    // PUBLIC_INTERFACE
    /** Applies request data to an existing or new Note entity. */
    public static Note applyRequest(Note target, NoteRequest request) {
        if (target == null) {
            target = new Note();
        }
        target.setTitle(request.getTitle());
        target.setContent(request.getContent());
        return target;
    }
}
