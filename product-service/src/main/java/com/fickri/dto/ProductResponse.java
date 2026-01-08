package com.fickri.dto;

public record ProductResponse(
        Long id,
        String name,
        Double price,
        String description
) {}
