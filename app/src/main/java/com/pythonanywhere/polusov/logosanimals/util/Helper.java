package com.pythonanywhere.polusov.logosanimals.util;

import com.pythonanywhere.polusov.logosanimals.model.Animal;

import io.realm.Realm;
import io.realm.RealmResults;


public class Helper {

    public static Animal getCurrentAnimal(Realm realm, int animalId) {
        return realm.where(Animal.class)
                .equalTo(Animal.FIELD_UNIQUE_ID, animalId)
                .findFirst();
    }
}
