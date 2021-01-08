package com.example.fridgewithoutnav;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_add,btn_viewAll;
    EditText input_ingredient, input_date;
    ListView lv;
    ArrayAdapter ingredientArrayAdapter;
    DataBase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        input_ingredient = findViewById(R.id.input_ingredient);
        input_date = findViewById(R.id.input_date);
        lv = findViewById(R.id.lv);

        dataBase = new DataBase(MainActivity.this);

        ShowIngredientOnListView(dataBase);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IngredientModel ingredientModel;

                try{
                    ingredientModel = new IngredientModel(-1, input_ingredient.getText().toString(), Integer.parseInt(input_date.getText().toString()));
                    Toast.makeText(MainActivity.this, ingredientModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Error adding ingredient", Toast.LENGTH_SHORT).show();
                    ingredientModel = new IngredientModel(-1, "error", 0);
                }

                DataBase dataBase = new DataBase(MainActivity.this);

                boolean success = dataBase.addOne(ingredientModel);

                Toast.makeText(MainActivity.this, "Success " + success, Toast.LENGTH_SHORT).show();
                ShowIngredientOnListView(dataBase);
            }
        });

        btn_viewAll.setOnClickListener(view -> {

                DataBase dataBase = new DataBase(MainActivity.this);

            ShowIngredientOnListView(dataBase);
            //Toast.makeText(MainActivity.this, all.toString() , Toast.LENGTH_SHORT).show();
        });


    }

    private void ShowIngredientOnListView(DataBase dataBase2) {
        ingredientArrayAdapter = new ArrayAdapter<IngredientModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBase2.getAll());
        lv.setAdapter(ingredientArrayAdapter);
    }
}