package uz.nasiba.avaz.memome.ui.auth;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import uz.nasiba.avaz.memome.R;

public class SignUpFragment extends Fragment {
    private TextInputEditText email, password;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        Button signUp = view.findViewById(R.id.sign_up);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    FirebaseAuth
                            .getInstance()
                            .createUserWithEmailAndPassword(String.valueOf(email.getText()), String.valueOf(password.getText()))
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful() && task.getResult() != null) {
                                        Log.e("onComplete: ", task.getResult().getUser().getEmail());
                                    }
                                }
                            });
                }
            }
        });
        return view;
    }
}
