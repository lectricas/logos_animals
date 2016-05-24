package com.pythonanywhere.polusov.logosanimals.view.level;

import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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


public class LevelOneFragment extends BaseLevelFragment {

    public static final int LEVEL = 1;
    @Bind(R.id.image_zero)
    ImageView imageZero;
    @Bind(R.id.image_one)
    ImageView imageOne;
    @Bind(R.id.image_two)
    ImageView imageTwo;
    @Bind(R.id.image_three)
    ImageView imageThree;


    Animal animal0;
    Animal animal1;
    Animal animal2;
    Animal animal3;

    Integer i;
    Integer j;
    List<Drawable> drawables;


    @Bind(R.id.image_zero_state)
    ImageView imageZeroState;
    @Bind(R.id.layout_zero)
    FrameLayout layoutZero;
    @Bind(R.id.layout_one)
    FrameLayout layoutOne;
    @Bind(R.id.layout_two)
    FrameLayout layoutTwo;
    @Bind(R.id.layout_three)
    FrameLayout layoutThree;
    @Bind(R.id.image_one_state)
    ImageView imageOneState;
    @Bind(R.id.image_two_state)
    ImageView imageTwoState;
    @Bind(R.id.image_three_state)
    ImageView imageThreeState;
    @Bind(R.id.next_word)
    Button nextWord;
    @Bind(R.id.animals_linear)
    LinearLayout animalsLinear;
    @Bind(R.id.animalName)
    TextView animalName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level_one, container, false);
        ButterKnife.bind(this, view);
        ((MainActivity) getActivity()).setTitle("Level One");


        i = 0;

        List<FrameLayout> layoutsList = new ArrayList<>();
        layoutsList.add(layoutZero);
        layoutsList.add(layoutOne);
        layoutsList.add(layoutTwo);
        layoutsList.add(layoutThree);

        List<ImageView> imageViewsAnimals = new ArrayList<>();
        imageViewsAnimals.add(imageZero);
        imageViewsAnimals.add(imageOne);
        imageViewsAnimals.add(imageTwo);
        imageViewsAnimals.add(imageThree);

        animalName.setText(animalsForFragment.get(0).getRusName());


        List<Animal> animals = new ArrayList<Animal>(animalsForFragment.values());
        Collections.shuffle(animals);
        animal0 = animals.get(0);
        animal1 = animals.get(1);
        animal2 = animals.get(2);
        animal3 = animals.get(3);


        drawables = new ArrayList<>();
        imageZero.setImageDrawable(Tools.getDrawableByString(getActivity(), animal0.getPicture()));
        imageZero.setTag(animal0.getName());

        imageOne.setImageDrawable(Tools.getDrawableByString(getActivity(), animal1.getPicture()));
        imageOne.setTag(animal1.getName());

        imageTwo.setImageDrawable(Tools.getDrawableByString(getActivity(), animal2.getPicture()));
        imageTwo.setTag(animal2.getName());

        imageThree.setImageDrawable(Tools.getDrawableByString(getActivity(), animal3.getPicture()));
        imageThree.setTag(animal3.getName());


        j = 0;
        final Handler handler = new Handler();
        for (i = 0; i < layoutsList.size(); i++) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    layoutsList.get(j).setVisibility(View.VISIBLE);
                    MediaPlayer mPlayer = MediaPlayer.create(getContext(), Tools.getRawIdFromString(getActivity(), animals.get(j).getSound()));
                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mPlayer.start();
                    YoYo.with(Techniques.BounceIn)
                            .duration(700)
                            .playOn(layoutsList.get(j));
                    j++;
                }
            }, (i + 1) * 1000);
        }


        prefs.setLevel(LevelOneFragment.LEVEL);


        return view;

    }

    public static LevelOneFragment newInstance() {
        return new LevelOneFragment();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.layout_zero, R.id.layout_one, R.id.layout_two, R.id.layout_three})
    public void onClick(View view) {
        layoutZero.setEnabled(false);
        layoutOne.setEnabled(false);
        layoutTwo.setEnabled(false);
        layoutThree.setEnabled(false);
        switch (view.getId()) {
            case R.id.layout_zero:
                if (imageZero.getTag().equals(animalsForFragment.get(0).getName())) {
                    imageZeroState.setImageDrawable(Tools.getDrawable(getContext(), R.drawable.ok));
                } else {
                    imageZeroState.setImageDrawable(Tools.getDrawable(getContext(), R.drawable.bad));
                    ((MainActivity) getActivity()).setWrong();

                }
                YoYo.with(Techniques.Pulse)
                        .duration(700)
                        .playOn(imageZeroState);
                nextWord.setVisibility(View.VISIBLE);
                break;
            case R.id.layout_one:

                if (imageOne.getTag().equals(animalsForFragment.get(0).getName())) {
                    imageOneState.setImageDrawable(Tools.getDrawable(getContext(), R.drawable.ok));
                } else {
                    imageOneState.setImageDrawable(Tools.getDrawable(getContext(), R.drawable.bad));
                    ((MainActivity) getActivity()).setWrong();

                }
                YoYo.with(Techniques.Pulse)
                        .duration(700)
                        .playOn(imageOneState);
                nextWord.setVisibility(View.VISIBLE);
                break;
            case R.id.layout_two:

                if (imageTwo.getTag().equals(animalsForFragment.get(0).getName())) {
                    imageTwoState.setImageDrawable(Tools.getDrawable(getContext(), R.drawable.ok));
                } else {
                    imageTwoState.setImageDrawable(Tools.getDrawable(getContext(), R.drawable.bad));
                    ((MainActivity) getActivity()).setWrong();
                }
                YoYo.with(Techniques.Pulse)
                        .duration(700)
                        .playOn(imageTwoState);
                nextWord.setVisibility(View.VISIBLE);
                break;
            case R.id.layout_three:

                if (imageThree.getTag().equals(animalsForFragment.get(0).getName())) {
                    imageThreeState.setImageDrawable(Tools.getDrawable(getContext(), R.drawable.ok));
                } else {
                    imageThreeState.setImageDrawable(Tools.getDrawable(getContext(), R.drawable.bad));
                    ((MainActivity) getActivity()).setWrong();
                }
                YoYo.with(Techniques.Pulse)
                        .duration(700)
                        .playOn(imageThreeState);
                nextWord.setVisibility(View.VISIBLE);
                break;
        }
    }


    @OnClick(R.id.next_word)
    public void onClick() {
        nextWord.setEnabled(false);
        LevelTwoFragment fragment = LevelTwoFragment.newInstance();
        fragment.setAnimalId(prefs.getAnimalNumber());
        ((MainActivity) getActivity()).openFragment(fragment);
    }
}