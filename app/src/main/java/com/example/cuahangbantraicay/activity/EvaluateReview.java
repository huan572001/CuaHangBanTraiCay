package com.example.cuahangbantraicay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.cuahangbantraicay.Modal.WaitingForReview;
import com.example.cuahangbantraicay.R;

public class EvaluateReview extends AppCompatActivity {
    WaitingForReview waitingForReview;
    Button btn_send;

    EditText edt_ER;

    CardView cv_upload;
    RatingBar ratingBar;
    TextView tv_ER_quality, tv_ER_price, tv_ER_name;
    ImageView img_ER;

    LinearLayout open_IMG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate_review);
        setControl();
        getData();
        createView();
        setEvent();
    }

    private void setEvent() {
        open_IMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, PICK_IMAGE_REQUEST);
//                startActionMode(intent,PICK_IMAGE_REQUEST);
            }
        });
    }

    private void setControl() {
        btn_send = findViewById(R.id.btn_send_ER);
        edt_ER = findViewById(R.id.edt_evaluate);
        cv_upload = findViewById(R.id.cv_upload_IMG);
        ratingBar = findViewById(R.id.rating);
        tv_ER_quality = findViewById(R.id.tv_ER_quality);
        tv_ER_price = findViewById(R.id.tv_ER_price);
        tv_ER_name = findViewById(R.id.tv_ER_name);
        img_ER = findViewById(R.id.img_ER);
        open_IMG=findViewById(R.id.open_IMG);
    }
    private void getData() {
        Bundle b = getIntent().getExtras();
        waitingForReview = (WaitingForReview) b.getParcelable("review");

    }

    private void createView() {
        tv_ER_name.setText(waitingForReview.getName());
        tv_ER_price.setText(String.valueOf(waitingForReview.getPrice()) );
        tv_ER_quality.setText("x"+waitingForReview.getQuantity());

    }
}