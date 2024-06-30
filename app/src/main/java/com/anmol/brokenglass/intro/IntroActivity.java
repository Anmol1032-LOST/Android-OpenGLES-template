package com.anmol.brokenglass.intro;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.anmol.brokenglass.databinding.ActivityIntroBinding;

public class IntroActivity extends AppCompatActivity {

    private ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}