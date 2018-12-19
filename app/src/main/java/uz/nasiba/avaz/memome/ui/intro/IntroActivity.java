package uz.nasiba.avaz.memome.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import uz.nasiba.avaz.memome.R;
import uz.nasiba.avaz.memome.ui.auth.AuthActivity;

public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ViewPager pager = findViewById(R.id.pager);
        PagerAdapter adapter = new SlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

    @Override
    public void finishActivity(int requestCode) {
        if (requestCode == 0) {
            Intent intent = new Intent(this, AuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private class SlidePagerAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Integer> animations;
        private ArrayList<Integer> colors;
        private ArrayList<Integer> titles;
        private ArrayList<Integer> texts;

        SlidePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

            animations = new ArrayList<>();
            animations.add(R.raw.intro_one);
            animations.add(R.raw.intro_two);
            animations.add(R.raw.intro_three);

            colors = new ArrayList<>();
            colors.add(0xFFFFF9C4);
            colors.add(0xFFC8E6C9);
            colors.add(0xFFB3E5FC);

            titles = new ArrayList<>();
            titles.add(R.string.intro_one);
            titles.add(R.string.intro_two);
            titles.add(R.string.intro_three);

            texts = new ArrayList<>();
            texts.add(R.string.intro_one_text);
            texts.add(R.string.intro_two_text);
            texts.add(R.string.intro_three_text);
        }

        @Override
        public Fragment getItem(int position) {
            IntroFragment fragment = new IntroFragment();
            Bundle arguments = new Bundle();
            arguments.putInt("animation", animations.get(position));
            arguments.putInt("color", colors.get(position));
            arguments.putInt("title", titles.get(position));
            arguments.putInt("text", texts.get(position));
            arguments.putBoolean("isFinish", position == animations.size() - 1);
            fragment.setArguments(arguments);
            return fragment;
        }

        @Override
        public int getCount() {
            return animations.size();
        }
    }
}