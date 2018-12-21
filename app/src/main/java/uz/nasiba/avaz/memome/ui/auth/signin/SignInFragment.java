package uz.nasiba.avaz.memome.ui.auth.signin;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uz.nasiba.avaz.memome.R;
import uz.nasiba.avaz.memome.databinding.FragmentSignInBinding;
import uz.nasiba.avaz.memome.ui.auth.AuthActivity;
import uz.nasiba.avaz.memome.ui.auth.signup.SignUpFragment;

public class SignInFragment extends Fragment {
    private FragmentSignInBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            SignInViewModel viewModel = ViewModelProviders
                    .of(this, new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()))
                    .get(SignInViewModel.class);
            binding.setViewmodel(viewModel);
            binding.setLifecycleOwner(this);

            binding.getRoot().findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((AuthActivity) getActivity()).switchFragments(new SignUpFragment());
                }
            });

            viewModel.error.observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String error) {
                    if (getView() != null && error != null) {
                        Snackbar.make(getView(), error, Snackbar.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
