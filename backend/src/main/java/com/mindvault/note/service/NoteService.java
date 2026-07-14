package com.mindvault.note.service;

import com.mindvault.note.dto.*;
import com.mindvault.note.entity.Note;
import com.mindvault.note.repository.NoteRepository;
import com.mindvault.user.entity.User;
import com.mindvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteResponse create(CreateNoteRequest request, String email) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Note note = new Note();

        note.setTitle(request.title());
        note.setContent(request.content());
        note.setFavorite(false);
        note.setArchived(false);
        note.setUser(user);

        return map(noteRepository.save(note));
    }

    public List<NoteResponse> findAll(String email) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return noteRepository.findAllByUserOrderByCreatedAtDesc(user)
            .stream()
            .map(this::map)
            .toList();
    }

    public NoteResponse update(
        UUID id,
        UpdateNoteRequest request,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Note note = noteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        validateOwnership(note, user);

        note.setTitle(request.title());
        note.setContent(request.content());

        return map(noteRepository.save(note));
    }

    public void delete(UUID id, String email) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Note note = noteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        validateOwnership(note, user);

        noteRepository.delete(note);
    }

    public List<NoteResponse> search(
        String query,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return noteRepository
            .findByUserAndTitleContainingIgnoreCaseOrUserAndContentContainingIgnoreCaseOrderByCreatedAtDesc(
                user,
                query,
                user,
                query
            )
            .stream()
            .map(this::map)
            .toList();

    }

    public NoteResponse updateArchived(
        UUID id,
        UpdateArchivedRequest request,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Note note = noteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        validateOwnership(note, user);

        note.setArchived(request.archived());

        Note updatedNote = noteRepository.save(note);

        return map(updatedNote);

    }

    public NoteResponse updateFavorite(
        UUID id,
        UpdateFavoriteRequest request,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Note note = noteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Note not found"));

        validateOwnership(note, user);

        note.setFavorite(request.favorite());

        Note updatedNote = noteRepository.save(note);

        return map(updatedNote);

    }

    private void validateOwnership(Note note, User user) {

        if (!note.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Note does not belong to user");
        }

    }

    private NoteResponse map(Note note) {

        return new NoteResponse(
            note.getId(),
            note.getTitle(),
            note.getContent(),
            note.isFavorite(),
            note.isArchived()
        );

    }

}
