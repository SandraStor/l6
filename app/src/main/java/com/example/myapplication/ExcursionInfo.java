package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yandex.mapkit.mapview.MapView;

import java.util.ArrayList;

public class ExcursionInfo extends AppCompatActivity {
    private ArrayList<Landmark> landmarks;
    private Excursion excursion;
    private ListView listView;
    private TextView desc, budg;
    private User user;
    private Button booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excursion_info);
        user = getIntent().getExtras().getParcelable(User.class.getCanonicalName());
        excursion = getIntent().getParcelableExtra(Excursion.class.getCanonicalName());
        Bundle bundle = getIntent().getExtras();
        landmarks = bundle.getParcelableArrayList("landmarks");
        listView = findViewById(R.id.listView);
        setAdapter();
        setTitle(excursion.getName());
        listViewListener();
        desc = findViewById(R.id.excursioninfo);
        desc.setText(excursion.getDesc());
        budg = findViewById(R.id.yourBudget);
        budg.setText("Ваш бюджет составляет:" + user.getBudget() +"\nЦена билета:" + excursion.getPrice());
        booking = findViewById(R.id.booking);
        setListenerOnButton();
    }

    public void listViewListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ExcursionInfo.this, Detailed.class);
                Landmark l = (Landmark) listView.getItemAtPosition(position);
                intent.putExtra(Landmark.class.getCanonicalName(), l);
                startActivity(intent);
            }
        });
    }

    public void setListenerOnButton(){
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getBudget().equals("")) Toast.makeText(ExcursionInfo.this, "Нет данных о бюджете", Toast.LENGTH_SHORT).show();
                else if (Integer.parseInt(user.getBudget())>=excursion.getPrice()){
                    user.setBudget( String.valueOf((Integer.parseInt(user.getBudget())-excursion.getPrice())));
                    budg.setText("Ваш бюджет составляет:" + user.getBudget() +"\nЦена билета:" + excursion.getPrice());
                    Toast.makeText(ExcursionInfo.this, "Вы купили билет на посещение экскурсии", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(ExcursionInfo.this, "Вашего бюджета не хватает на покупку билета", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void setAdapter(){
        ArrayAdapter<Landmark> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, landmarks);
        listView.setAdapter(adapter);
    }

}