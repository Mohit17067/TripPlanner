package com.example.tripplanner.ui.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tripplanner.ui.main.Database.CursorWrapper;
import com.example.tripplanner.ui.main.Database.InitialHelper;
import com.example.tripplanner.ui.main.Database.PlanSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.tripplanner.ui.main.Database.PlanSchema.PlanTable.Columns.ID;
import static com.example.tripplanner.ui.main.Database.PlanSchema.PlanTable.Columns.PLAN_DATE;
import static com.example.tripplanner.ui.main.Database.PlanSchema.PlanTable.Columns.PLAN_DETAIL;
import static com.example.tripplanner.ui.main.Database.PlanSchema.PlanTable.Columns.PLAN_TIME;

public class PlanDatabase {

    private Context curContext;
    private SQLiteDatabase database;

    private static PlanDatabase inst;

    public static PlanDatabase get(Context context){
        if (inst == null){
            inst = new PlanDatabase(context);
        }
        return inst;
    }

    public PlanDatabase(Context context){
        curContext = context.getApplicationContext();
        database = new InitialHelper(curContext).getWritableDatabase();
    }

    public List<Plan> getItems() {
        List<Plan> items = new ArrayList<>();
        Cursor curcursor = database.query(
                PlanSchema.PlanTable.TableName,
                null, // Columns - null selects all columns
                null,
                null,
                null, // groupBy
                null, // having
                PLAN_DATE+" ASC, " + PLAN_TIME + " ASC"// orderBy
        );
        CursorWrapper cursor = new CursorWrapper(curcursor);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                items.add(cursor.getPlan());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return items;
    }

    public void addItem(Plan i){
        ContentValues values = new ContentValues();
        values.put(ID, i.getId().toString());
        values.put(PLAN_DETAIL, i.getDetails());
        values.put(PLAN_DATE, i.getDate());
        values.put(PLAN_TIME, i.getTime());

        database.insert(PlanSchema.PlanTable.TableName, null, values);
    }

    public Plan getItem(UUID id) {

        Cursor curcursor = database.query(
                PlanSchema.PlanTable.TableName,
                null, // Columns - null selects all columns
                ID + " = ?",
                new String[]{id.toString()},
                null, // groupBy
                null, // having
                null  // orderBy
        );
        CursorWrapper cursor = new CursorWrapper(curcursor);


        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getPlan();
        } finally {
            cursor.close();
        }
    }
}
