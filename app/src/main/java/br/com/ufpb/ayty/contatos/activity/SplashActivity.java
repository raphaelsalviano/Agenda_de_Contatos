package br.com.ufpb.ayty.contatos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.ufpb.ayty.contatos.R;

public class SplashActivity extends BaseActivity {

    public static final String TAG = "SplashActivity";

    private final int TIME_SCREEN = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        hideNavigationBar();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME_SCREEN);
    }
}
