package uz.nasiba.avaz.memome.ui.intro;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

import uz.nasiba.avaz.memome.R;
import uz.nasiba.avaz.memome.ui.auth.AuthActivity;
import uz.nasiba.avaz.memome.utils.LocaleManager;

public class IntroActivity extends AppCompatActivity {
    private int index = 0;
    private ArrayList<Integer> animations;
    private ArrayList<Integer> titles;
    private ArrayList<Integer> texts;
    private LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        initialize();
        update();
    }

    private void initialize() {
        animations = new ArrayList<>();
        animations.add(R.raw.intro_one);
        animations.add(R.raw.intro_two);
        animations.add(R.raw.intro_three);

        titles = new ArrayList<>();
        titles.add(R.string.intro_one);
        titles.add(R.string.intro_two);
        titles.add(R.string.intro_three);

        texts = new ArrayList<>();
        texts.add(R.string.intro_one_text);
        texts.add(R.string.intro_two_text);
        texts.add(R.string.intro_three_text);

        animationView = findViewById(R.id.animator);
    }

    private void update() {
        animationView.setAnimation(animations.get(index));
        animationView.resumeAnimation();

        ((TextView) findViewById(R.id.title)).setText(titles.get(index));
        ((TextView) findViewById(R.id.text)).setText(texts.get(index));
        if (index == 0) {
            findViewById(R.id.previous).setVisibility(View.GONE);
            findViewById(R.id.next).setVisibility(View.VISIBLE);
            findViewById(R.id.finish).setVisibility(View.GONE);
        } else if (index == animations.size() - 1) {
            findViewById(R.id.previous).setVisibility(View.VISIBLE);
            findViewById(R.id.next).setVisibility(View.GONE);
            findViewById(R.id.finish).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.previous).setVisibility(View.VISIBLE);
            findViewById(R.id.next).setVisibility(View.VISIBLE);
            findViewById(R.id.finish).setVisibility(View.GONE);
        }
    }

    public void next(View view) {
        if (index < animations.size() - 1) {
            index++;
        }
        update();
    }

    public void previous(View view) {
        if (index > 0) {
            index--;
        }
        update();
    }

    public void finish(View view) {
        Intent intent = new Intent(this, AuthActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }
}