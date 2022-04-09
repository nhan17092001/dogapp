package com.example.dogapp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.model.DogBreed;
import com.example.dogapp.R;
import com.example.dogapp.viewmodel.DogApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ListFragment extends Fragment {
    private DogApiService dogApiService;
    private ArrayList<DogBreed> DogList;
    private CustomAdapter customAdapter;
    private RecyclerView rcvDog;
    private static final String TAG ="123";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@androidx.annotation.NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvDog = view.findViewById(R.id.rcv_list);
        DogList = new ArrayList<DogBreed>();
        customAdapter = new CustomAdapter(DogList);
        rcvDog.setAdapter(customAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rcvDog.setLayoutManager(gridLayoutManager);
        dogApiService = new DogApiService();
        dogApiService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {


                    @Override
                    public void onSuccess(@NonNull List<DogBreed> dogBreeds) {
                        for(DogBreed dog : dogBreeds){
                            DogList.add(dog);
                            Log.d("onSuccess: ", dog.getName() );
                        }
                        customAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}