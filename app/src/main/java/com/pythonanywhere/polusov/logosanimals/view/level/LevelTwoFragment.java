package com.pythonanywhere.polusov.logosanimals.view.level;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.pythonanywhere.polusov.logosanimals.R;
import com.pythonanywhere.polusov.logosanimals.model.Animal;
import com.pythonanywhere.polusov.logosanimals.util.Tools;
import com.pythonanywhere.polusov.logosanimals.view.BaseLevelFragment;
import com.pythonanywhere.polusov.logosanimals.view.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LevelTwoFragment extends BaseLevelFragment {

    public static final int LEVEL = 2;


    @Bind(R.id.next_word)
    Button nextWord;

    @Bind(R.id.var1)
    Button var1;
    @Bind(R.id.var2)
    Button var2;
    @Bind(R.id.var3)
    Button var3;
    @Bind(R.id.var4)
    Button var4;


    @Bind(R.id.image_one)
    ImageView imageOne;
    @Bind(R.id.image_one_state)
    ImageView imageOneState;
    @Bind(R.id.layout_one)
    FrameLayout layoutOne;

    private Animal myAnimal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level_two, container, false);
        ButterKnife.bind(this, view);

        List<Animal> animals = new ArrayList<Animal>(animalsForFragment.values());
        Collections.shuffle(animals);
        Animal animal0 = animals.get(0);
        Animal animal1 = animals.get(1);
        Animal animal2 = animals.get(2);
        Animal animal3 = animals.get(3);


        var1.setText(animal0.getRusName());
        var2.setText(animal1.getRusName());
        var3.setText(animal2.getRusName());
        var4.setText(animal3.getRusName());


        myAnimal = animalsForFragment.get(MY_ANIMAL);
        imageOne.setImageDrawable(Tools.getDrawableByString(getActivity(), myAnimal.getPicture()));

        layoutOne.setVisibility(View.VISIBLE);
        MediaPlayer mPlayer = MediaPlayer.create(getContext(), Tools.getRawIdFromString(getActivity(), myAnimal.getSound()));
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.start();
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .playOn(imageOne);


        prefs.setLevel(LevelTwoFragment.LEVEL);
        return view;
    }

    public static LevelTwoFragment newInstance() {
        return new LevelTwoFragment();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.next_word)
    public void onClick() {
        nextWord.setEnabled(false);
        LevelThreeFragment fragment = LevelThreeFragment.newInstance();
        fragment.setAnimalId(prefs.getAnimalNumber());
        ((MainActivity) getActivity()).openFragment(fragment);
    }

    @OnClick({R.id.var1, R.id.var2, R.id.var3, R.id.var4})
    public void onClick(View view) {
        nextWord.setVisibility(View.VISIBLE);
        layoutOne.setEnabled(false);
        switch (view.getId()) {
            case R.id.var1:

                if (var1.getText().toString().equals(myAnimal.getRusName())) {
                    imageOneState.setImageDrawable(Tools.getDrawable(getContext(), R.drawable.ok));
                } else {
                    imageOneState.setImageDrawable(Tools.getDrawable(getContext(), R.drawable.bad));
                    ((MainActivity) getActivity()).setWrong();

                }
                YoYo.with(Techniques.Pulse)
                        .duration(1000)
                        .playOn(imageOneState);
                break;
            case R.id.var2:
                if (var2.getText().toString().equals(myAnimal.getRusName())) {
                    imageOneState.setImageDrawable(Tools.getDrawable(getContext(), R.drawable.ok));
                } else {
                    imageOneState.setImageDrawable(Tools.getDrawable(getContext(), R.drawable.bad));
                    ((MainActivity) getActivity()).setWrong();
                }
                YoYo.with(Techniques.Pulse)
                        .duration(1000)
                        .playOn(imageOneState);
                break;
            case R.id.var3:
                if (var3.getText().toString().equals(myAnimal.getRusName())) {
                    imageOneState.setImageDrawable(Tools.getDrawable(getContext(), R.drawable.ok));
                } else {
                    imageOneState.setImageDrawable(Tools.getDrawable(getContext(), R.drawable.bad));
                    ((MainActivity) getActivity()).setWrong();
                }
                YoYo.with(Techniques.Pulse)
                        .duration(1000)
                        .playOn(imageOneState);
                break;
            case R.id.var4:
                if (var4.getText().toString().equals(myAnimal.getRusName())) {
                    imageOneState.setImageDrawable(Tools.getDrawable(getContext(), R.drawable.ok));
                } else {
                    imageOneState.setImageDrawable(Tools.getDrawable(getContext(), R.drawable.bad));

                    ((MainActivity) getActivity()).setWrong();
                }
                YoYo.with(Techniques.Pulse)
                        .duration(1000)
                        .playOn(imageOneState);
                break;
        }
    }


    @OnClick(R.id.image_one)
    public void onClickImage() {
        MediaPlayer mPlayer = MediaPlayer.create(getContext(), Tools.getRawIdFromString(getActivity(), myAnimal.getSound()));
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.start();
    }
}