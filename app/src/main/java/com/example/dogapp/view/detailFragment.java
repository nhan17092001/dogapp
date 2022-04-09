package com.example.dogapp.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.dogapp.R;
import com.example.dogapp.databinding.FragmentDetailBinding;
import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;


public class detailFragment extends Fragment {
    private DogBreed dogBreed;
    private FragmentDetailBinding binding;
    private ImageView img;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dogBreed = (DogBreed) getArguments().getSerializable("dogBreed");


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_detail,null,false);
        View viewroot = binding.getRoot();
         img = viewroot.findViewById(R.id.iv_photo);
         Picasso.get().load(dogBreed.getUrl()).into(img);
        binding.setDog(dogBreed);
        return viewroot;
    }

}