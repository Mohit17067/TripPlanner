package com.example.tripplanner.ui.main.Database;

import android.database.Cursor;


import com.example.tripplanner.ui.main.Plan;

import java.util.UUID;

public class CursorWrapper extends android.database.CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Plan getPlan(){
        String planid = getString(getColumnIndex(PlanSchema.PlanTable.Columns.ID));
        String plandetail = getString(getColumnIndex(PlanSchema.PlanTable.Columns.PLAN_DETAIL));
        String plandate = getString(getColumnIndex(PlanSchema.PlanTable.Columns.PLAN_DATE));
        String plantime = getString(getColumnIndex(PlanSchema.PlanTable.Columns.PLAN_TIME));

        Plan i = new Plan(UUID.fromString(planid));
        i.setDetails(plandetail);
        i.setDate(plandate);
        i.setTime(plantime);
        return i;
    }
}
