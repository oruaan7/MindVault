package com.mindvault.note.service;

import com.mindvault.note.dto.CreateNoteRequest;
import com.mindvault.note.dto.NoteResponse;
import com.mindvault.note.entity.Note;
import com.mindvault.note.repository.NoteRepository;
import com.mindvault.user.entity.User;
import com.mindvault.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        return new NoteResponse(
            savedNote.getId(),
            savedNote.getTitle(),
            savedNote.getContent(),
            savedNote.isFavorite(),
            savedNote.isArchived()
        );

    }

}
