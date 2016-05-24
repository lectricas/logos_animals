package com.pythonanywhere.polusov.logosanimals.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Letter{
    private String letter;
    @Setter
    private boolean isInside;
}