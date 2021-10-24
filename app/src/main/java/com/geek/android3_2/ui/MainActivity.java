package com.geek.android3_2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.geek.android3_2.R;
import com.geek.android3_2.data.models.Films;
import com.geek.android3_2.databinding.ActivityMainBinding;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

//    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
//
//        viewModel.getFilms();
//
//        viewModel.films.observe(this, new Observer<List<Films>>() {
//            @Override
//            public void onChanged(List<Films> films) {
//                Log.d("TAG", "onChanged: ");
//            }
//        });
    }
}