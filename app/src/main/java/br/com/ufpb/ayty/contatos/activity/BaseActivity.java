package br.com.ufpb.ayty.contatos.activity;

import android.annotation.TargetApi;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class BaseActivity extends livroandroid.lib.activity.BaseActivity{

    private static final String TAG = "BaseActivity";

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // Fragment Mananger
        manager = getSupportFragmentManager();
    }

    protected void setUpToolbar(int id){
        Toolbar toolbar = (Toolbar)findViewById(id);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    protected void hideNavigationBar(){
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    protected Typeface getTypeface(){
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/roboto_light.ttf");
        return typeface;
    }

}
