package com.pythonanywhere.polusov.logosanimals.view.level;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pythonanywhere.polusov.logosanimals.R;
import com.pythonanywhere.polusov.logosanimals.model.Letter;
import com.pythonanywhere.polusov.logosanimals.util.Tools;
import com.pythonanywhere.polusov.logosanimals.view.BaseLevelFragment;
import com.pythonanywhere.polusov.logosanimals.view.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LevelThreeFragment extends BaseLevelFragment {


    @Bind(R.id.relative_parent)
    RelativeLayout relativeParent;

    @Bind(R.id.frame_for_letters)
    FrameLayout frameForLetters;

    Map<Integer, View> viewMap;
    List<Letter> lettersToCheck;
    List<View> letterViews;

    @Bind(R.id.next_word)
    public Button nextWord;

    String animalname;

    public static final int LEVEL = 3;



    @Bind(R.id.image_one_state)
    ImageView imageOneState;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_level_three, container, false);
        ButterKnife.bind(this, root);
        prefs.setLevel(LevelThreeFragment.LEVEL);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Log.d("LevelThreeFragment", "size.x:" + size.x);
        viewMap = new HashMap<>();
        lettersToCheck = new ArrayList<>();
        letterViews = new ArrayList<>();

        RelativeLayout.LayoutParams frameLP = new RelativeLayout.LayoutParams(100 * animalsForFragment.size(), 250);
        frameLP.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        frameForLetters.setLayoutParams(frameLP);

        animalname = animalsForFragment.get(0).getRusName();
        char[] charArray = animalname.toCharArray();

        Random random = new Random();
        for (int i = 0; i < charArray.length; i++) {
            lettersToCheck.add(new Letter(String.valueOf(charArray[i]), false));
            TextView textView = new TextView(getActivity());
            int letterX = random.nextInt(size.x - 200);
            int letterY = random.nextInt(size.y - 200);
            Log.d("LevelThreeFragment", letterX + " - " + letterY);
            textView.setX(letterX);
            textView.setY(letterY);
            textView.setText(charArray[i] + "");
            textView.setBackground(Tools.getDrawable(getContext(), R.drawable.oval_letter));
            textView.setTextSize(50f);

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);


            textView.setLayoutParams(layoutParams);
            textView.setOnTouchListener(touchListener);
            letterViews.add(textView);
            relativeParent.addView(textView);
        }


        return root;
    }


    View.OnTouchListener touchListener = new View.OnTouchListener() {
        float dX, dY;

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            String letterOnView = ((TextView) view).getText().toString();
            for (int i = 0; i < lettersToCheck.size(); i++) {
                if (lettersToCheck.get(i).getLetter().equals(letterOnView)) {
                    lettersToCheck.get(i).setInside(doViewsIntersect(frameForLetters, view));
                    Log.d("LevelThreeFragment", "doViewsIntersect(frameForLetters, view):" + doViewsIntersect(frameForLetters, view));
                }
            }

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    dX = view.getX() - event.getRawX();
                    dY = view.getY() - event.getRawY();
                    break;

                case MotionEvent.ACTION_MOVE:

                    view.animate()
                            .x(event.getRawX() + dX)
                            .y(event.getRawY() + dY)
                            .setDuration(0)
                            .start();
                    break;
                default:
                    return false;
            }

            boolean isAllInBox = true;
            for (Letter letter : lettersToCheck) {
                if (!letter.isInside()) {
                    isAllInBox = false;
                    break;
                } else {
                    isAllInBox = true;
                }

            }

            Log.d("LevelThreeFragment", "isAllInBox:" + isAllInBox);
            if (isAllInBox) {
                nextWord.setVisibility(View.VISIBLE);
            } else {
                nextWord.setVisibility(View.INVISIBLE);
            }


            return true;


        }
    };

    private Rect getScreenBounds(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);

        return new Rect(location[0], location[1], location[0] + view.getWidth(), location[1] + view.getHeight());
    }

    private boolean doViewsIntersect(View target, View item) {
        Rect targetRect = getScreenBounds(target);
        Rect itemRect = getScreenBounds(item);

        return targetRect.intersects(itemRect.left, itemRect.top, itemRect.right, itemRect.bottom);
    }


    private boolean isPassed(List<View> views) {
        Collections.sort(views, new Comparator<View>() {
            @Override
            public int compare(View lhs, View rhs) {
                return Float.compare(lhs.getX(), rhs.getX());
            }
        });
        String result = "";
        for (View view : views) {
            result += ((TextView) view).getText().toString();
        }

        Log.d("LevelThreeFragment", result);

        if (animalname.equals(result)) {
            return true;
        } else {
            return false;
        }

    }





    public static LevelThreeFragment newInstance() {
        return new LevelThreeFragment();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.next_word)
    public void onClickNextWord() {
        nextWord.setEnabled(false);

        if (isPassed(letterViews)) {
            imageOneState.setImageDrawable(Tools.getDrawable(getActivity(), R.drawable.ok));
        } else {
            imageOneState.setImageDrawable(Tools.getDrawable(getActivity(), R.drawable.bad));
            ((MainActivity) getActivity()).setWrong();
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (prefs.getAnimalNumber() == realmResults.size()) {
                    FinishFragment fragmentFinish = FinishFragment.newInstance();
                    ((MainActivity) getActivity()).openFragment(fragmentFinish);
                } else {
                    LevelZeroFragment fragment = LevelZeroFragment.newInstance();
                    int nextAnimal = prefs.getAnimalNumber() + 1;
                    prefs.setAnimalNumber(nextAnimal);
                    fragment.setAnimalId(prefs.getAnimalNumber());
                    ((MainActivity) getActivity()).openFragment(fragment);
                }



            }
        }, 3000);


    }

}