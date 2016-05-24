package com.pythonanywhere.polusov.logosanimals.view.level;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.pythonanywhere.polusov.logosanimals.R;
import com.pythonanywhere.polusov.logosanimals.util.Tools;
import com.pythonanywhere.polusov.logosanimals.view.BaseLevelFragment;
import com.pythonanywhere.polusov.logosanimals.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LevelZeroFragment extends BaseLevelFragment {

    public static final int LEVEL = 0;



    @Bind(R.id.next_word)
    Button nextWord;
    @Bind(R.id.image_zero)
    ImageView imageZero;
    @Bind(R.id.image_zero_name)
    TextView imageZeroName;
    @Bind(R.id.layout_zero)
    LinearLayout layoutZero;
    @Bind(R.id.image_one)
    ImageView imageOne;
    @Bind(R.id.image_one_name)
    TextView imageOneName;
    @Bind(R.id.layout_one)
    LinearLayout layoutOne;

    int j;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level_zero, container, false);
        ButterKnife.bind(this, view);

        configureLevel();

        prefs.setLevel(LevelZeroFragment.LEVEL);
        return view;
    }


    private void configureLevel() {


        imageZeroName.setText(animalsForFragment.get(MY_ANIMAL).getRusName());
        imageOneName.setText(animalsForFragment.get(1).getRusName());


        Drawable img1 = Tools.getDrawableByString(getActivity(), animalsForFragment.get(MY_ANIMAL).getPicture());
        Drawable img2 = Tools.getDrawableByString(getActivity(), animalsForFragment.get(1).getPicture());
        imageZero.setImageDrawable(img1);
        imageOne.setImageDrawable(img2);

        List<LinearLayout> layoutsList = new ArrayList<>();
        layoutsList.add(layoutZero);
        layoutsList.add(layoutOne);

        j = 0;
        final Handler handler = new Handler();
        for (int i = 0; i < layoutsList.size(); i++) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    layoutsList.get(j).setVisibility(View.VISIBLE);
                    MediaPlayer mPlayer = MediaPlayer.create(getContext(), Tools.getRawIdFromString(getActivity(), animalsForFragment.get(j).getSound()));
                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mPlayer.start();
                    YoYo.with(Techniques.RotateIn)
                            .duration(700)
                            .playOn(layoutsList.get(j));
                    j++;
                }
            }, (i + 1) * 1000);
        }

    }

    public static LevelZeroFragment newInstance() {
        return new LevelZeroFragment();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



    @OnClick(R.id.next_word)
    public void onClick() {
        LevelOneFragment fragment = LevelOneFragment.newInstance();
        fragment.setAnimalId(prefs.getAnimalNumber());
        ((MainActivity) getActivity()).openFragment(fragment);
    }
}
