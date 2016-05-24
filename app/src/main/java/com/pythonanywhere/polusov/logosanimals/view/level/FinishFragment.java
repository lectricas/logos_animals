package com.pythonanywhere.polusov.logosanimals.view.level;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pythonanywhere.polusov.logosanimals.R;
import com.pythonanywhere.polusov.logosanimals.model.Animal;
import com.pythonanywhere.polusov.logosanimals.view.BaseLevelFragment;
import com.pythonanywhere.polusov.logosanimals.view.MainActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinishFragment extends BaseLevelFragment {


    @Bind(R.id.finishText)
    TextView finishText;
    @Bind(R.id.start_again)
    Button startAgain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finish, container, false);
        ButterKnife.bind(this, view);
        List<Animal> animals = realmResults;
        int i = 0;
        for (Animal animal : animals) {
            if (animal.getIsRight() == 1) {
                i++;
            }

        }
        finishText.setText(i + " / 12");


        return view;
    }

    public static FinishFragment newInstance() {
        return new FinishFragment();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.start_again)
    public void onClick() {
        prefs.setAnimalNumber(0);
        prefs.setLevel(0);
        LevelZeroFragment fragment = LevelZeroFragment.newInstance();
        fragment.setAnimalId(prefs.getAnimalNumber());
        ((MainActivity) getActivity()).openFragment(fragment);
    }
}