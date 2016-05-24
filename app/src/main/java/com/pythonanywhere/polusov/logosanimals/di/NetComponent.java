package com.pythonanywhere.polusov.logosanimals.di;

import com.pythonanywhere.polusov.logosanimals.view.BaseLevelFragment;
import com.pythonanywhere.polusov.logosanimals.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules=NetModule.class)
public interface NetComponent {
    void inject(MainActivity activity);
    void inject(BaseLevelFragment fragment);
}