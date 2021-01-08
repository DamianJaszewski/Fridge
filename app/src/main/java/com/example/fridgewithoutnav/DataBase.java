package com.example.fridgewithoutnav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    public static final String INGREDIENT_TABLE = "INGREDIENT_TABLE";
    public static final String COLUMN_INGREDIENT_NAME = "INGREDIENT_NAME";
    public static final String COLUMN_INGREDIENT_DATE = "INGREDIENT_DATE";
    public static final String COLUMN_ID = "ID";

    public DataBase(@Nullable Context context) {
        super(context, "ingredient.db", null, 1);
    }

    //this is called the first time a database is accessed. There should be code in here to create a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + INGREDIENT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_INGREDIENT_NAME + " TEXT, " + COLUMN_INGREDIENT_DATE + " INT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean addOne(IngredientModel ingredientModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_INGREDIENT_NAME, ingredientModel.getName());
        cv.put(COLUMN_INGREDIENT_DATE, ingredientModel.getDate());

        long insert = db.insert(INGREDIENT_TABLE, null, cv);
        if (insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean deleteOne(IngredientModel ingredientModel){

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM" + INGREDIENT_TABLE + " WHERE " + COLUMN_ID + " = " + ingredientModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }

    }

    public List<IngredientModel> getAll(){
        List<IngredientModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + INGREDIENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            do {
                int ingredientId = cursor.getInt(0);
                String ingredientName = cursor.getString(1);
                int ingredientDate = cursor.getInt(2);

                IngredientModel newIngredient = new IngredientModel(ingredientId, ingredientName, ingredientDate);
                returnList.add(newIngredient);

            } while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }
}
