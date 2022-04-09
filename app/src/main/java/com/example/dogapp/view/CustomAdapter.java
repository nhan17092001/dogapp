package com.example.dogapp.view;

import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.model.DogBreed;
import com.example.dogapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>  {
    private static final String TAG = "12";
    private static ArrayList<DogBreed> DogList;
    private  static  int i=0;
    public CustomAdapter(ArrayList<DogBreed> DogList) {
        this.DogList = DogList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.namedog.setText(DogList.get(position).getName());
        holder.description_dog.setText(DogList.get(position).getLifespan());
        Picasso.get().load(DogList.get(position).getUrl()).into(holder.photo);

        holder.tvName1.setText(DogList.get(position).getName());
        holder.origin.setText(DogList.get(position).getOrigin());
        holder.lifespan.setText(DogList.get(position).getLifespan());
        holder.layout2.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return DogList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView  namedog;
        public ImageView photo;
        public TextView description_dog;

        public TextView tvName1;
        public TextView origin;
        public TextView lifespan;
        public TextView temperament;

        public LinearLayout layout2;
        public LinearLayout layout1;


        public ViewHolder(View view) {
            super(view);
            origin = view.findViewById(R.id.tv_origin);
            lifespan = view.findViewById(R.id.tv_lifespan);
            temperament = view.findViewById(R.id.tv_temperament);
            layout1 = view.findViewById(R.id.layout1);

            layout2 = view.findViewById(R.id.layout2);
            tvName1 = view.findViewById(R.id.tv_name1);
            namedog = view.findViewById(R.id.name_dog);
            photo = view.findViewById(R.id.iv_photo);
            description_dog= view.findViewById(R.id.description_dog);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    i++;
                    Handler handler = new Handler();
                    Runnable r = new Runnable() {

                        @Override
                        public void run() {
                            i = 0;
                        }
                    };

                    if (i == 1) {
                        //Single click

                        handler.postDelayed(r, 250);
                        layout1.post(() -> {
                            Point point = new Point();
                            float x = layout1.getX();
                            layout1.getDisplay().getSize(point);
                            float width = layout1.getMeasuredWidth();
                            Log.d(TAG, "onClick: "+x);
                            ObjectAnimator objectAnimator;
                            if(x == 0.0) {
                               layout2.setVisibility(View.VISIBLE);

                                objectAnimator = ObjectAnimator.ofFloat(layout1, "translationX", 0, (width - point.x));
                            }
                            else {
                                layout2.setVisibility(View.GONE);
                                objectAnimator = ObjectAnimator.ofFloat(layout1, "translationX", (width - point.x), 0);
                            }
                            objectAnimator.setRepeatCount(0);
                            objectAnimator.setDuration(1000);
                            objectAnimator.start();


                        });
                    } else if (i == 2) {
                        //Double click
                        i = 0;
                        DogBreed dog = DogList.get(getAdapterPosition());
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("dogBreed",dog);
                        Navigation.findNavController(view).navigate(R.id.detailFragment,bundle);
                    }
                }

            });
        }
    }

}
