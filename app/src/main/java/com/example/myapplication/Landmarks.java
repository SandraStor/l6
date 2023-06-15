package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.yandex.mapkit.geometry.Point;

import java.io.Serializable;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public class Landmarks extends AppCompatActivity {
    ArrayList<Landmark> landmarks;
    private User user;
    private ListView listView;

    private Button seeExcursions;
    private TextView hello;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmarks);
        setTitle("Достопримечательности");
        hello = findViewById(R.id.hello);
        listView = findViewById(R.id.listView);
        seeExcursions = findViewById(R.id.seeExcursions);
        user = getIntent().getExtras().getParcelable(User.class.getCanonicalName());
        hello.setText("Добро пожаловать," +user.getName() + "!");
        setAdapter();
        listViewListener();
        setListenerOnButton();
    }

    public void listViewListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Landmarks.this, Detailed.class);
                Landmark l = (Landmark) listView.getItemAtPosition(position);
                intent.putExtra(Landmark.class.getCanonicalName(), l);
                startActivity(intent);
            }
        });
    }

    public void setListenerOnButton(){
        seeExcursions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Landmarks.this, Excursions.class);
                intent.putExtra(User.class.getCanonicalName(), user);
                intent.putExtra("landmarks", landmarks);
                startActivity(intent);
            }
        });

    }

    public void setAdapter(){
        landmarks = new ArrayList<>();
        Landmark landmark1 = new Landmark("Ротонда", R.string.ротонда, "11.00-00.00", new Point(51.522815, 46.039680), "-");
        landmarks.add(landmark1);
        Landmark landmark2 = new Landmark("Музей им. Радищева",R.string.музейРадищева, "10.00-19.00", new Point(51.531427, 46.038417), "museum");
        landmarks.add(landmark2);
        Landmark landmark3 = new Landmark("Музей краеведения", R.string.музейКраеведения ,"12.00-18.00", new Point(51.527574, 46.056140), "museum");
        landmarks.add(landmark3);
        Landmark landmark4 = new Landmark("Собор Утоли Мои Печали", R.string.утолиМоиПечали, "7.00-19.30", new Point(51.530167, 46.035923), "church");
        Landmark landmark5 = new Landmark("Свято-Троицкий собор", R.string.троицкийСобор, "7.00-19.00", new Point(51.528042, 46.055271),"church");
        Landmark landmark6 = new Landmark("Консерватория им. Л.В.Собинова", R.string.консерватория, "9.00-18.00", new Point(51.529814, 46.034077),"music");
        Landmark landmark7 = new Landmark("Филармония им. А.Шнитке", R.string.филармония, "10.00-19.00", new Point(51.527113, 46.034589), "music");
        Landmark landmark8 = new Landmark("Особняк Скворцова", R.string.особнякСкворцова, "10.00-15.00", new Point(51.529876, 46.040844),"mansion");
        Landmark landmark9 = new Landmark("Особняк Кузнецова-Бендеря", R.string.особнякКузнецова, "10.00-16.30", new Point( 51.532310, 46.036624 ), "mansion");
        landmarks.add(landmark4); landmarks.add(landmark5);
        landmarks.add(landmark6); landmarks.add(landmark7);
        landmarks.add(landmark8); landmarks.add(landmark9);

        ArrayAdapter<Landmark> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, landmarks);

        listView.setAdapter(adapter);
    }

}