package com.mindvault.note.controller;

import com.mindvault.note.dto.CreateNoteRequest;
import com.mindvault.note.dto.NoteResponse;
import com.mindvault.note.dto.UpdateNoteRequest;
import com.mindvault.note.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public NoteResponse create(
        @Valid @RequestBody CreateNoteRequest request,
        Authentication authentication
    ) {
        return noteService.create(request, authentication.getName());
    }

    @GetMapping
    public List<NoteResponse> findAll(Authentication authentication) {
        return noteService.findAll(authentication.getName());
    }

    @PutMapping("/{id}")
    public NoteResponse update(
        @PathVariable UUID id,
        @Valid @RequestBody UpdateNoteRequest request,
        Authentication authentication
    ) {
        return noteService.update(id, request, authentication.getName());
    }

}
