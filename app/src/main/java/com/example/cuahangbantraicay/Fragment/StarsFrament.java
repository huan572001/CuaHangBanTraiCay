package com.example.cuahangbantraicay.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cuahangbantraicay.R;

public class StarsFrament extends Fragment {
    ImageView stars1,stars2,stars3,stars4,stars5;
    TextView tv_stars;
    private double startStars=5;
    private Boolean status=false;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.frament_stars, container, false);

        setControl(view);
        setEvent();
        return view;
    }
    public StarsFrament() {
    }
    public StarsFrament(double startStars) {
        this.startStars=startStars;
    }
    public StarsFrament(double startStars,Boolean status) {
        this.startStars=startStars;
        this.status=status;
    }

    private void setControl(View view){
        stars1=view.findViewById(R.id.img_stars_1);
        stars2=view.findViewById(R.id.img_stars_2);
        stars3=view.findViewById(R.id.img_stars_3);
        stars4=view.findViewById(R.id.img_stars_4);
        stars5=view.findViewById(R.id.img_stars_5);
        tv_stars=view.findViewById(R.id.tv_stars);
    }
    private void setEvent() {
        if(status){
            tv_stars.setText("");
            stars1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    constructerStars(1.0);
                }
            });
            stars2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    constructerStars(2.0);
                }
            });
            stars3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    constructerStars(3.0);
                }
            });
            stars4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    constructerStars(4.0);
                }
            });
            stars5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    constructerStars(5.0);
                }
            });
        } else {
            tv_stars.setText(String.valueOf(startStars));
        }
        constructerStars(this.startStars);

    }
    private void setCloseStart(){
        stars1.setImageResource(R.drawable.baseline_star_24_black);
        stars2.setImageResource(R.drawable.baseline_star_24_black);
        stars3.setImageResource(R.drawable.baseline_star_24_black);
        stars4.setImageResource(R.drawable.baseline_star_24_black);
        stars5.setImageResource(R.drawable.baseline_star_24_black);
    }
    private void constructerStars(double startStars){
        setCloseStart();
        if(startStars>=1){
            stars1.setImageResource(R.drawable.baseline_star_24);
            if(startStars>1&&startStars<2){
                stars1.setImageResource(R.drawable.baseline_star_half_24);
            }
            else if(startStars>2) {
                stars2.setImageResource(R.drawable.baseline_star_24);
                if(startStars>2&&startStars<3){
                    stars2.setImageResource(R.drawable.baseline_star_half_24);
                }
                else if(startStars>=3) {
                    stars3.setImageResource(R.drawable.baseline_star_24);
                    if(startStars>3&&startStars<4){
                        stars3.setImageResource(R.drawable.baseline_star_half_24);
                    }
                    else if(startStars>=4) {
                        stars4.setImageResource(R.drawable.baseline_star_24);
                        if(startStars>4&&startStars<5){
                            stars4.setImageResource(R.drawable.baseline_star_half_24);
                        }
                        else if(startStars==5){
                            stars5.setImageResource(R.drawable.baseline_star_24);
                        }
                    }
                }
            }
        }

    }

}
