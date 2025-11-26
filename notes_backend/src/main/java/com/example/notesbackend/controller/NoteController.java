package com.example.notesbackend.controller;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST controller exposing CRUD endpoints for notes.
 */
@RestController
@RequestMapping(path = "/notes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Notes", description = "CRUD operations for notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    /**
     * PUBLIC_INTERFACE
     * GET /notes - list all notes
     */
    @GetMapping
    @Operation(summary = "List notes", description = "Returns all notes")
    @ApiResponse(responseCode = "200", description = "List of notes",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = NoteResponse.class)))
    public ResponseEntity<List<NoteResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * PUBLIC_INTERFACE
     * GET /notes/{id} - get a note by id
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get note", description = "Returns a single note by id")
    @ApiResponse(responseCode = "200", description = "Note found")
    @ApiResponse(responseCode = "404", description = "Note not found")
    public ResponseEntity<NoteResponse> getById(
            @Parameter(description = "Note id") @PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * PUBLIC_INTERFACE
     * POST /notes - create a new note
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create note", description = "Creates a new note")
    @ApiResponse(responseCode = "201", description = "Note created")
    @ApiResponse(responseCode = "400", description = "Validation error")
    public ResponseEntity<NoteResponse> create(@Valid @RequestBody NoteRequest request) {
        NoteResponse created = service.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    /**
     * PUBLIC_INTERFACE
     * PUT /notes/{id} - update an existing note
     */
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update note", description = "Updates an existing note by id")
    @ApiResponse(responseCode = "200", description = "Note updated")
    @ApiResponse(responseCode = "404", description = "Note not found")
    @ApiResponse(responseCode = "400", description = "Validation error")
    public ResponseEntity<NoteResponse> update(
            @Parameter(description = "Note id") @PathVariable Long id,
            @Valid @RequestBody NoteRequest request) {
        return service.update(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * PUBLIC_INTERFACE
     * DELETE /notes/{id} - delete a note
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete note", description = "Deletes a note by id")
    @ApiResponse(responseCode = "204", description = "Note deleted")
    @ApiResponse(responseCode = "404", description = "Note not found")
    public ResponseEntity<Void> delete(@Parameter(description = "Note id") @PathVariable Long id) {
        boolean deleted = service.delete(id);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Validation error handler to return useful messages
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<Map<String, Object>> handleValidation(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Validation failed");
        Map<String, String> fieldErrors = new HashMap<>();
        if (ex instanceof MethodArgumentNotValidException manve) {
            manve.getBindingResult().getFieldErrors()
                    .forEach(e -> fieldErrors.put(e.getField(), e.getDefaultMessage()));
        } else if (ex instanceof BindException be) {
            be.getBindingResult().getFieldErrors()
                    .forEach(e -> fieldErrors.put(e.getField(), e.getDefaultMessage()));
        }
        body.put("errors", fieldErrors);
        return ResponseEntity.badRequest().body(body);
    }
}
