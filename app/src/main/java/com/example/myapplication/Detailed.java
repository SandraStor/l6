package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;


public class Detailed extends AppCompatActivity {

    private TextView desc;
    private Landmark landmark;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        mapView = findViewById(R.id.mapView4);
        desc = findViewById(R.id.Description);

        landmark = getIntent().getParcelableExtra(Landmark.class.getCanonicalName());
        desc.setText(landmark.getDesc());
        setTitle(landmark.getName());
        setLandmark();

    }
    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    public void setLandmark() {
        mapView.getMap().move(
                new CameraPosition(landmark.getPoint(), 18f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 2), null);
        mapView.getMap().getMapObjects().addPlacemark(landmark.getPoint());
    }
}