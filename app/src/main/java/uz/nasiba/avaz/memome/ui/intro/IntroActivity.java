package uz.nasiba.avaz.memome.ui.intro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import uz.nasiba.avaz.memome.R;

public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);

        ViewPager pager = findViewById(R.id.pager);
        PagerAdapter adapter = new SlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

    private class SlidePagerAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Integer> animations;
        private ArrayList<Integer> colors;
        private ArrayList<Integer> titles;
        private ArrayList<Integer> texts;

        SlidePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

            animations = new ArrayList<>();
            animations.add(R.raw.intro_box);
            animations.add(R.raw.intro_construction);
            animations.add(R.raw.intro_box);

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
            fragment.setArguments(arguments);
            return fragment;
        }

        @Override
        public int getCount() {
            return animations.size();
        }
    }
}