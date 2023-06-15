package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//добавить поле тип к достопримечательности + окошко экскурсии
// (оно имеет цену), юзеру добавить бюджет
public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText surname;
    private EditText email;
    private EditText budget;
    private User user;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Главная");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        email = findViewById(R.id.email);
        budget = findViewById(R.id.budget);

        addListenerOnButton();

    }

    public void addListenerOnButton(){
        button = findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().equals("")){
                    user = new User(name.getText().toString(), surname.getText().toString(), email.getText().toString(), budget.getText().toString());
                    Intent intent = new Intent(MainActivity.this, Landmarks.class);
                    intent.putExtra(User.class.getCanonicalName(), user);
                    startActivity(intent);
                }
                else Toast.makeText(MainActivity.this, "Вы не ввели имя =с", Toast.LENGTH_SHORT).show();
            }
        }
        );
    }

}