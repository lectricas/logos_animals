package com.pythonanywhere.polusov.logosanimals.model;


import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Level extends RealmObject {

    public Level() {
    }

    int number;
    boolean isPassed;
    int type;

    RealmList<Animal> animals;


}
