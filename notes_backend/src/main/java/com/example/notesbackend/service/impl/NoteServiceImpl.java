package com.example.notesbackend.service.impl;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.mapper.NoteMapper;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.repository.NoteRepository;
import com.example.notesbackend.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Default implementation of NoteService using JPA repository.
 */
@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;

    public NoteServiceImpl(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteResponse> findAll() {
        return repository.findAll().stream()
                .map(NoteMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NoteResponse> findById(Long id) {
        return repository.findById(id).map(NoteMapper::toResponse);
    }

    @Override
    public NoteResponse create(NoteRequest request) {
        Note entity = NoteMapper.applyRequest(new Note(), request);
        Note saved = repository.save(entity);
        return NoteMapper.toResponse(saved);
    }

    @Override
    public Optional<NoteResponse> update(Long id, NoteRequest request) {
        return repository.findById(id)
                .map(existing -> {
                    NoteMapper.applyRequest(existing, request);
                    Note saved = repository.save(existing);
                    return NoteMapper.toResponse(saved);
                });
    }

    @Override
    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
