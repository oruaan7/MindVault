package com.mindvault.note.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateNoteRequest(

    @NotBlank
    @Size(max = 150)
    String title,

    @NotBlank
    String content

) {
}
