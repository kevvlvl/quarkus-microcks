package org.acme.dto;

import java.time.LocalDateTime;

public record GreetingsDto(String message, LocalDateTime currentDate) {}