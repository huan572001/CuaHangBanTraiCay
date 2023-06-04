package com.example.cuahangbantraicay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.cuahangbantraicay.API.CategoryApi;
import com.example.cuahangbantraicay.API.ProductApi1;
import com.example.cuahangbantraicay.API.ThongKeApi;
import com.example.cuahangbantraicay.Modal.Category;
import com.example.cuahangbantraicay.Modal.Product;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.Utils.BASE_URL;
import com.example.cuahangbantraicay.Utils.VolleyCallback1;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.itextpdf.text.pdf.PdfPTable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarException;

public class ThongKe extends AppCompatActivity {
    PieChart pieChart;
    Toolbar toolbar;
    List<Product> productList;
    Boolean isActive;
    List<Product> PDF = new ArrayList<>();

    Button button, btnXuat;

    String Thang;
    String Nam;
    TextView btnBack;

    Spinner spinnerThang, spinnerNam;
    Bitmap bitmap, scaleBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        setControl();
//        ActionToobar()
        setDataCategory();
        setDataCategory1();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ngocanh);

        scaleBitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, false);
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        String string = new String();
        createPDF();
        setEvent();
    }


    private void setControl() {
        pieChart = findViewById(R.id.piechart);
        toolbar = findViewById(R.id.toolbar);
        spinnerThang = findViewById(R.id.thang);
        spinnerNam = findViewById(R.id.nam);
        button = findViewById(R.id.btnLoc);
        btnXuat = findViewById(R.id.btnXuat);
        btnBack = findViewById(R.id.backTK);
    }

    private void setDataCategory() {

// Khai báo danh sách các giá trị cho Spinner
        String[] values = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

// Tạo một ArrayAdapter để hiển thị danh sách giá trị cho Spinner
        ArrayAdapter<String> adapterThang = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);

// Thiết lập kiểu layout cho danh sách giá trị của Spinner
        adapterThang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Thiết lập ArrayAdapter cho Spinner
        spinnerThang.setAdapter(adapterThang);
    }

    private void setDataCategory1() {

// Khai báo danh sách các giá trị cho Spinner
        String[] values = {"2023", "2024", "2025", "2026", "2027"};

// Tạo một ArrayAdapter để hiển thị danh sách giá trị cho Spinner
        ArrayAdapter<String> adapterNam = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);

// Thiết lập kiểu layout cho danh sách giá trị của Spinner
        adapterNam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Thiết lập ArrayAdapter cho Spinner
        spinnerNam.setAdapter(adapterNam);
    }


    private void CallApi(String thang, String nam) throws JSONException {
        try {
            ThongKeApi.getThongKe(getApplicationContext(), BASE_URL.BASE_ADMIN_URL + "statistical/" + thang + "/" + nam, new VolleyCallback1() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                    JSONArray data = result.getJSONArray("send_data");
                    JSONObject productObj = new JSONObject();
                     productList = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        productObj = (JSONObject) data.get(i);
                        Product tmpP = new Product();
                        tmpP.setId(productObj.getInt("id"));
                        tmpP.setName(productObj.getString("name"));
                        tmpP.setTotal_quantity(productObj.getInt("total_quantity"));
                        productList.add(tmpP);
                        PDF.add(tmpP);

                    }
                    piechart();


                }

                @Override
                public void onError(VolleyError errorMessage) {
//                    System.out.println("hihi" + errorMessage);
                }
            });
        } catch (JarException e) {
            throw new RuntimeException(e);
        }
    }

    private void piechart() {
        List<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            String productName = productList.get(i).getName();
            int productQuantity = productList.get(i).getTotal_quantity();
            entries.add(new PieEntry(productQuantity, productName));
        }

        // Cập nhật dữ liệu cho PieChart

        PieDataSet dataSet = new PieDataSet(entries, "Products");


        PieDataSet pinDataSet = new PieDataSet(entries, "Thong Ke");
        PieData data = new PieData();

        data.setDataSet(pinDataSet);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextColor(Color.BLACK);
        data.setValueTextSize(20f);
        pinDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        pieChart.setData(data);
        pieChart.animateXY(2000, 2000);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawEntryLabels(true);
        pieChart.invalidate();
// refresh lại PieChart


    }

    // api thống kê
    private void setEvent() {
        spinnerThang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Thang = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });
        spinnerNam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Nam = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CallApi(Thang, Nam);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isActive = true;
                Intent intent = new Intent(ThongKe.this, Admin.class);
                startActivity(intent);
            }
        });


    }

    private void createPDF() {
        btnXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PdfDocument myPdfDocument = new PdfDocument();
                Paint myPaint = new Paint();
                PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(250, 400, 1).create();
                PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);

                Canvas canvas = myPage.getCanvas();

                myPaint.setTextAlign(Paint.Align.CENTER);
                myPaint.setTextSize(15.0f);
                canvas.drawText("Cửa hàng bán trái cây", myPageInfo.getPageWidth() / 2, 30, myPaint);


                myPaint.setTextSize(10.0f);
                myPaint.setColor(Color.rgb(0, 0, 0));
                canvas.drawText("Tháng : " + Thang + "/" + Nam, myPageInfo.getPageWidth() / 2, 45, myPaint);


                myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setTextSize(9.0f);
                myPaint.setColor(Color.rgb(0, 0, 0));
                canvas.drawText("Danh sách sản phẩm trong tháng :", 10, 70, myPaint);

                myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setTextSize(9.0f);
                myPaint.setColor(Color.rgb(0, 0, 0));

                int startX = 10;
                int startY = 110;
                int tong = 0;
                canvas.drawLine(10, 95, 200, 95, myPaint);

// Vẽ các dòng của bảng
                canvas.drawText("ID", 10, 90, myPaint);
                canvas.drawText("Tên Sản Phẩm", 40, 90, myPaint);
                canvas.drawText("Số lượng", 120, 90, myPaint);

//                productList = new ArrayList<>();
                System.out.println(PDF.size());
                for (int i = 0; i < PDF.size(); i++) {
                    int productId = PDF.get(i).getId();
                    String productName = PDF.get(i).getName();
                    int productQuantity = PDF.get(i).getTotal_quantity();
                    tong+=productQuantity;

                    // Vẽ các cột của bảng
                    canvas.drawLine(10, startY+5, 200, startY+5, myPaint);
                    canvas.drawText(String.valueOf(productId), 10, startY, myPaint);
                    canvas.drawText(String.valueOf(productName), 40, startY, myPaint);
                    canvas.drawText(String.valueOf(productQuantity), 120, startY, myPaint);

                startY+=20;
                }

                canvas.drawLine(10, startY+5, 200, startY+5, myPaint);
                canvas.drawText("Tổng", 10, startY, myPaint);
                canvas.drawText("", 40, startY, myPaint);
                canvas.drawText(String.valueOf(tong), 120, startY, myPaint);

                myPdfDocument.finishPage(myPage);
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), Thang + "_" + Nam + ".pdf");

                try {
                    myPdfDocument.writeTo(new FileOutputStream(file));
                    Toast.makeText(getApplicationContext(), "File exported to " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                myPdfDocument.close();
            }
        });
    }
//    private void ActionToobar() {
////        setSupportActionBar(toolbar);
////        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }


}





