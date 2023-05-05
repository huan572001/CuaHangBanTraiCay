package com.example.cuahangbantraicay.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cuahangbantraicay.Modal.WaitingForReview;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.CustomToast;

public class EvaluateReview extends AppCompatActivity {
    WaitingForReview waitingForReview;
    Button btn_send;

    EditText edt_ER;

    CardView cv_upload;
    RatingBar ratingBar;
    TextView tv_ER_quality, tv_ER_price, tv_ER_name;
    ImageView img_ER,img_Review;

    LinearLayout open_IMG;
    private static final int PICK_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });
        img_Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_Review.setVisibility(View.GONE);
                cv_upload.setVisibility(View.VISIBLE);
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendReview();
                onBackPressed();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed(); // gọi phương thức gốc
    }
    private void sendReview() {
        CustomToast.makeText(getApplicationContext(), "Thành công !", CustomToast.LENGTH_SHORT, CustomToast.ERROR, true).show();

        onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Kiểm tra xem phản hồi có phải là cho yêu cầu chọn tệp tin ảnh không
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            // Lấy URI của tệp tin ảnh đã chọn
            Uri uri = data.getData();


            System.out.println(uri+"=====================================");
            // Thực hiện các thao tác với tệp tin ảnh ở đây
            // ...
            Glide.with(this).load(uri).into(img_Review);
            img_Review.setVisibility(View.VISIBLE);
            cv_upload.setVisibility(View.GONE);

        }
    }
    private void setControl() {
        setTitle("EVALUATE REVIEW");
        btn_send = findViewById(R.id.btn_send_ER);
        edt_ER = findViewById(R.id.edt_evaluate);
        cv_upload = findViewById(R.id.cv_upload_IMG);
        ratingBar = findViewById(R.id.rating_review);
        tv_ER_quality = findViewById(R.id.tv_ER_quality);
        tv_ER_price = findViewById(R.id.tv_ER_price);
        tv_ER_name = findViewById(R.id.tv_ER_name);
        img_ER = findViewById(R.id.img_ER);
        open_IMG=findViewById(R.id.open_IMG);
        img_Review=findViewById(R.id.img_Review);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}