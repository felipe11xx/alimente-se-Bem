package com.example.web.alimentesebem.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.web.alimentesebem.R;

/**
 * Created by WEB on 28/03/2018.
 */

public class SplashScreenActivity extends AppCompatActivity{
    // Tempo que a nossa SplashScreen ficará visível para o usuário
    private final int SPLASH_DISPLAY_LENGTH = 0;//3500;
    private TextView tvLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screeen);

        // Iremos criar um método carregar para realizar a nossa ação
        carregar();

    }

    private void carregar() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_splash);
        animation.reset();

        // Buscando o objeto que foi criado no layout (nossa imagem)
        ImageView imageView = findViewById(R.id.img_sesi_splash);
        tvLogo = findViewById(R.id.tv_splash_logo);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/tahu.ttf");
        tvLogo.setTypeface(typeface);

        if (imageView != null && tvLogo != null) {
            imageView.clearAnimation();
            imageView.startAnimation(animation);
            tvLogo.clearAnimation();
            tvLogo.startAnimation(animation);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Após o tempo definido irá executar a próxima
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                // Como vimos anteriormente para irmos para uma nova Activity
                startActivity(intent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
