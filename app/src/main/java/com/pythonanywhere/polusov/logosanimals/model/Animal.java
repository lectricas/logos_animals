package com.pythonanywhere.polusov.logosanimals.model;


import io.realm.RealmObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Animal extends RealmObject {


    public Animal() {
    }

    public static final String FIELD_UNIQUE_ID = "uniqueId";
    int uniqueId;

    public static final String FIELD_NAME = "name";
    String name;

    public static final String FIELD_RUS_NAME = "rus_name";
    String rusName;

    public static final String FIELD_PICTURE = "picture";
    String picture;

    public static final String FIELD_IS_RIGHT = "isRight";
    int isRight;

    public static final String FIELD_LEVEL = "level";
    int level;

    public static final String FIELD_SOUND = "sound";
    String sound;


}
