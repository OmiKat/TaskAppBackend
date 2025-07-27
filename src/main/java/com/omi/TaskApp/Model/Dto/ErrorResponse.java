package com.omi.TaskApp.Model.Dto;

public record ErrorResponse(
        int status ,
        String message,
        String details
) {
}
