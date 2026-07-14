package com.mindvault.note.repository;

import com.mindvault.note.entity.Note;
import com.mindvault.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {

    List<Note> findAllByUserOrderByCreatedAtDesc(User user);

    List<Note> findByUserAndTitleContainingIgnoreCaseOrUserAndContentContainingIgnoreCaseOrderByCreatedAtDesc(
        User user,
        String title,
        User sameUser,
        String content
    );

    long countByUser(User user);
}
