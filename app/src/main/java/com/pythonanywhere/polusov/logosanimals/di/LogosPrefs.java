package com.pythonanywhere.polusov.logosanimals.di;

import proxypref.annotation.DefaultInteger;


public interface LogosPrefs {

    @DefaultInteger(0)
    Integer getLevel();
    void setLevel(Integer x);

    @DefaultInteger(0)
    Integer getAnimalNumber();
    void setAnimalNumber(Integer x);
}