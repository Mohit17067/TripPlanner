package com.example.tripplanner.ui.main.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InitialHelper extends SQLiteOpenHelper {


    public InitialHelper(Context c){
        super(c, "temp", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + PlanSchema.PlanTable.TableName + "(" + " _id integer primary key autoincrement, " + PlanSchema.PlanTable.Columns.ID + ", " + PlanSchema.PlanTable.Columns.PLAN_DETAIL + "," + PlanSchema.PlanTable.Columns.PLAN_DATE + "," + PlanSchema.PlanTable.Columns.PLAN_TIME + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
