package uz.nasiba.avaz.memome.ui.menu.memories;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uz.nasiba.avaz.memome.R;
import uz.nasiba.avaz.memome.databinding.FragmentMemoriesBinding;
import uz.nasiba.avaz.memome.ui.create.CreateActivity;

public class MemoriesFragment extends Fragment {
    private FragmentMemoriesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_memories, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            MemoriesViewModel viewModel = ViewModelProviders
                    .of(this, new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()))
                    .get(MemoriesViewModel.class);
            binding.setViewmodel(viewModel);
            binding.setLifecycleOwner(this);

            binding.getRoot().findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CreateActivity.class);
                    getActivity().startActivity(intent);
                }
            });
        }
    }
}
