package com.mindvault.note.dto;

import java.util.UUID;

public record NoteResponse(

    UUID id,

    String title,

    String content,

    boolean favorite,

    boolean archived

) {
}
