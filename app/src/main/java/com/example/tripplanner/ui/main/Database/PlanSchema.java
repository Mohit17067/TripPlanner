package com.example.tripplanner.ui.main.Database;

public class PlanSchema {
    public static final class PlanTable{
        public static final String TableName = "plans";

        public static final class Columns {
            public static  final String ID = "id";
            public static final String PLAN_DETAIL = "detail";
            public static final String PLAN_DATE = "date";
            public static final String PLAN_TIME = "time";
        }
    }

}
