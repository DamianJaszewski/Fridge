package com.example.fridgewithoutnav;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Date;

public class MainActivity extends AppCompatActivity {

    Button btn_add;
    EditText input_ingredient, input_date;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        input_ingredient = findViewById(R.id.input_ingredient);
        input_date = findViewById(R.id.input_date);
        lv = findViewById(R.id.lv);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    IngredientModel ingredientModel = new IngredientModel(-1, input_ingredient.getText().toString(), Integer.parseInt(input_date.getText().toString()));
                    Toast.makeText(MainActivity.this, ingredientModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Error adding ingredient", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}