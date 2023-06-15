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

public class Excursions extends AppCompatActivity {
    private ArrayList<Landmark> landmarks;
    private ArrayList<Excursion> excursions;
    private ListView listView;
    private User user;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursions);
        listView = findViewById(R.id.listView);
        Bundle bundle = getIntent().getExtras();
        landmarks = bundle.getParcelableArrayList("landmarks");
        user = getIntent().getExtras().getParcelable(User.class.getCanonicalName());

        setAdapter();
        listViewListener();
        setTitle("Экскурсии");

    }
    public void listViewListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Excursions.this, ExcursionInfo.class);
                Excursion E = (Excursion) listView.getItemAtPosition(position);
                intent.putExtra(User.class.getCanonicalName(), user);
                intent.putExtra(Excursion.class.getCanonicalName(), E);
                intent.putExtra("landmarks", E.getLandmarks());
                startActivity(intent);
            }
        });
    }
    public void setAdapter(){
        Excursion excursionMuseums = new Excursion("Музеи", "museum", 1000, R.string.экскурсияМузеи);
        Excursion excursionChurches = new Excursion("Церкви", "church", 800,R.string.экскурсияЦеркви);
        Excursion excursionMusic = new Excursion("Музыка", "music", 900,R.string.экскурсияМузыка);
        Excursion excursionMansions = new Excursion("Известные здания", "mansion", 500,R.string.экскурсияОсобняки);
        excursions = new ArrayList<>();
       for (int i = 0; i < landmarks.size(); i++){
            if (landmarks.get(i).getType().equals(excursionMuseums.getType())){excursionMuseums.addLandmark(landmarks.get(i));}
            if (landmarks.get(i).getType().equals(excursionMusic.getType())){excursionMusic.addLandmark(landmarks.get(i));}
            if (landmarks.get(i).getType().equals(excursionChurches.getType())){excursionChurches.addLandmark(landmarks.get(i));}
            if (landmarks.get(i).getType().equals(excursionMansions.getType())){excursionMansions.addLandmark(landmarks.get(i));}
        }
        excursions.add(excursionChurches); excursions.add(excursionMansions);
        excursions.add(excursionMuseums); excursions.add(excursionMusic);
        ArrayAdapter<Excursion> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, excursions);
        listView.setAdapter(adapter);
    }



}