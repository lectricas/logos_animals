package com.pythonanywhere.polusov.logosanimals.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.pythonanywhere.polusov.logosanimals.LogosApplication;
import com.pythonanywhere.polusov.logosanimals.R;
import com.pythonanywhere.polusov.logosanimals.di.LogosPrefs;
import com.pythonanywhere.polusov.logosanimals.model.Animal;
import com.pythonanywhere.polusov.logosanimals.view.level.LevelOneFragment;
import com.pythonanywhere.polusov.logosanimals.view.level.LevelThreeFragment;
import com.pythonanywhere.polusov.logosanimals.view.level.LevelTwoFragment;
import com.pythonanywhere.polusov.logosanimals.view.level.LevelZeroFragment;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {


    @Inject
    public LogosPrefs prefs;
    @Inject
    RealmConfiguration realmConfiguration;


    Realm realm;
    RealmResults wordsList;

    Map<Integer, BaseLevelFragment> fragments;
    @Bind(R.id.fragment_container)
    FrameLayout fragmentContainer;
//    @Bind(R.id.old_pics)
//    Button oldPics;
//    @Bind(R.id.new_pics)
//    Button newPics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        ((LogosApplication) getApplication()).getMNetComponent().inject(this);
        realm = Realm.getInstance(realmConfiguration);



        ButterKnife.bind(this);

        wordsList = realm.allObjects(Animal.class);

        fragments = new LinkedHashMap<>();
        fragments.put(0, LevelZeroFragment.newInstance());
        fragments.put(1, LevelOneFragment.newInstance());
        fragments.put(2, LevelTwoFragment.newInstance());
        fragments.put(3, LevelThreeFragment.newInstance());

        setListsOld();
        BaseLevelFragment fragment = fragments.get(prefs.getLevel());
        fragment.setAnimalId(prefs.getAnimalNumber());
        openFragment(fragment);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (wordsList.size() != 0){
//            oldPics.setVisibility(View.INVISIBLE);
//            newPics.setVisibility(View.INVISIBLE);
            BaseLevelFragment fragment = fragments.get(prefs.getLevel());
            fragment.setAnimalId(prefs.getAnimalNumber());
            openFragment(fragment);

        }

    }

    public void openFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


//    @OnClick(R.id.next_word)
//    public void onClick() {
//        Toast.makeText(this, "prefs.getAnimalNumber():" + prefs.getAnimalNumber(), Toast.LENGTH_SHORT).show();
//        if (prefs.getAnimalNumber() == wordsList.size()) {
//            prefs.setAnimalNumber(0);
//            openFragment(FinishFragment.newInstance());
//        } else {
//            if (prefs.getLevel() == LevelThreeFragment.LEVEL){
//                prefs.setAnimalNumber(prefs.getAnimalNumber()+1);
//            }
//            BaseLevelFragment fragment = fragments.get(prefs.getLevel());
//            fragment.setAnimalId(prefs.getAnimalNumber());
//            openFragment(fragment);
//        }
//    }

//    @OnClick(R.id.wrong_answer)
//    public void onClick(View view) {

//    }

    public void setWrong() {
        Animal worngAnimal = realm.where(Animal.class).equalTo(Animal.FIELD_UNIQUE_ID, prefs.getAnimalNumber()).findFirst();
        realm.beginTransaction();
        worngAnimal.setIsRight(0);
        realm.commitTransaction();
    }


    private void setListsOld() {
        String[] animalsNames = {
                "cat",
                "dog",
                "cow",
                "goat",
                "horse",
                "sheep",
                "pig",
                "donkey",
                "chicken",
                "duck",
                "goose",
                "turkey"
        };


        String[] animalsRusNames = {
                "кошка",
                "собака",
                "корова",
                "коза",
                "лошадь",
                "овца",
                "свинья",
                "осел",
                "курица",
                "утка",
                "гусь",
                "индюк"
        };

        int animalId = 0;
        for (String name : animalsNames) {
            realm.beginTransaction();
            Animal animal = new Animal();
            animal.setRusName(animalsRusNames[animalId]);
            animal.setName(name);
            animal.setIsRight(1);
            animal.setUniqueId(animalId);
            animal.setPicture("ic_" + name);
            animal.setSound("s_" + name);
            realm.copyToRealm(animal);
            realm.commitTransaction();
            animalId++;
        }
    }


    private void setListsNew() {
        String[] animalsNames = {
                "cat",
                "dog",
                "cow",
                "goat",
                "horse",
                "sheep",
                "pig",
                "donkey",
                "chicken",
                "duck",
                "goose",
                "turkey"
        };


        String[] animalsRusNames = {
                "кошка",
                "собака",
                "корова",
                "коза",
                "лошадь",
                "овца",
                "свинья",
                "осел",
                "курица",
                "утка",
                "гусь",
                "индюк"
        };

        int animalId = 0;
        for (String name : animalsNames) {
            realm.beginTransaction();
            Animal animal = new Animal();
            animal.setName(name);
            animal.setRusName(animalsRusNames[animalId]);
            animal.setIsRight(1);
            animal.setUniqueId(animalId);
            animal.setPicture("ict_" + name);
            animal.setSound("p_" + name);
            realm.copyToRealm(animal);
            realm.commitTransaction();
            animalId++;
        }
    }


//    @OnClick({R.id.old_pics, R.id.new_pics})
//    public void onClick(View view) {
//
//        oldPics.setVisibility(View.INVISIBLE);
//        newPics.setVisibility(View.INVISIBLE);
//
//        switch (view.getId()) {
//            case R.id.old_pics:
//                setListsOld();
//                break;
//            case R.id.new_pics:
//                setListsNew();
//                break;
//        }
//
//        BaseLevelFragment fragment = fragments.get(prefs.getLevel());
//        fragment.setAnimalId(prefs.getAnimalNumber());
//        openFragment(fragment);
//    }
}










