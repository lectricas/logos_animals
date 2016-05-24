package com.pythonanywhere.polusov.logosanimals.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pythonanywhere.polusov.logosanimals.LogosApplication;
import com.pythonanywhere.polusov.logosanimals.R;
import com.pythonanywhere.polusov.logosanimals.di.LogosPrefs;
import com.pythonanywhere.polusov.logosanimals.model.Animal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public abstract class BaseLevelFragment extends Fragment {

    private static final int NUMBER_OF_ANIMALS_IN_FRAGMENT = 4;
    public static final int MY_ANIMAL = 0;

    @Inject
    RealmConfiguration realmConfiguration;
    @Inject
    public LogosPrefs prefs;


    Realm realm;
    public RealmResults realmResults;



    private int animalId;
    protected Map<Integer, Animal> animalsForFragment;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((LogosApplication) getActivity().getApplication()).getMNetComponent().inject(this);
        realm = Realm.getInstance(realmConfiguration);
        realmResults = realm.allObjects(Animal.class);
        animalsForFragment = createAnimals();
    }




    private Map<Integer, Animal> createAnimals() {
        List<Animal> animals = realm.copyFromRealm(realmResults);
        Map<Integer, Animal> animalMap = new HashMap<>();
        for (Animal animal: animals){
            if (animal.getUniqueId() == animalId) {
                animals.remove(animal);
                animalMap.put(MY_ANIMAL, animal);
                break;
            }
        }
        Random random = new Random();

        for (int i = 1; i < NUMBER_OF_ANIMALS_IN_FRAGMENT; i++){
            Animal animal = animals.get(random.nextInt(animals.size()));
            animalMap.put(i, animal);
            animals.remove(animal);
        }
        Log.d("BaseLevelFragment", "animalMap.size():" + animalMap.size());
        return animalMap;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    public void setAnimalId(int animalId){
        this.animalId = animalId;
    }

}
