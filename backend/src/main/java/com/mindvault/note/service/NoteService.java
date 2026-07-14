package com.mindvault.note.service;

import com.mindvault.note.dto.CreateNoteRequest;
import com.mindvault.note.dto.NoteResponse;
import com.mindvault.note.entity.Note;
import com.mindvault.note.repository.NoteRepository;
import com.mindvault.user.entity.User;
import com.mindvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteResponse create(
        CreateNoteRequest request,
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        Note note = new Note();

        note.setTitle(request.title());
        note.setContent(request.content());
        note.setFavorite(false);
        note.setArchived(false);
        note.setUser(user);

        Note savedNote = noteRepository.save(note);

        return map(savedNote);

    }

    public List<NoteResponse> findAll(
        String email
    ) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));

        return noteRepository
            .findAllByUserOrderByCreatedAtDesc(user)
            .stream()
            .map(this::map)
            .toList();

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
