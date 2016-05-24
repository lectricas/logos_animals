package com.pythonanywhere.polusov.logosanimals.util;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;



public class Tools {

    public static Drawable getDrawable(Context context, int drawableId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(drawableId, context.getTheme());
        } else {
            return context.getResources().getDrawable(drawableId);
        }
    }

    public static Drawable getDrawableByString(Context context, String drawableString){
        int imageResource = context.getResources()
                .getIdentifier("drawable/" + drawableString, null, context.getPackageName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(imageResource, context.getTheme());
        } else {
            return context.getResources().getDrawable(imageResource);
        }

    }


    public static int getRawIdFromString(Context context, String raw){
        return context.getResources()
                .getIdentifier("raw/" + raw, null, context.getPackageName());
    }
}
