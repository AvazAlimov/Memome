package uz.nasiba.avaz.memome.ui.intro;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import uz.nasiba.avaz.memome.R;

public class IntroFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        if (getArguments() != null) {
            int animation = getArguments().getInt("animation");
            int color = getArguments().getInt("color");
            int title = getArguments().getInt("title");
            int text = getArguments().getInt("text");

            view.setBackgroundColor(color);
            ((LottieAnimationView) view.findViewById(R.id.animator)).setAnimation(animation);
            ((TextView) view.findViewById(R.id.title)).setText(title);
            ((TextView) view.findViewById(R.id.text)).setText(text);
        }
        return view;
    }
}