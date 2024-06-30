package com.anmol.brokenglass.intro;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.anmol.brokenglass.R;
import com.anmol.brokenglass.databinding.FragmentIntroBinding;
import com.anmol.brokenglass.game.GameActivity;

public class IntroFragment extends Fragment {

    private FragmentIntroBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentIntroBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button.setOnClickListener(v -> {
            assert getActivity() != null;
            startActivity(new Intent(getActivity(), GameActivity.class));
            getActivity().overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}