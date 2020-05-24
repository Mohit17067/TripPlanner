package com.example.tripplanner.ui.main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripplanner.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Adaptor extends RecyclerView.Adapter<ViewHolder>{

    List<Plan> items;

    public Adaptor(List<Plan> items){
        this.items = items;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater infl = LayoutInflater.from(parent.getContext());
        return new ViewHolder(infl, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Plan i = items.get(position);
        try {
            holder.bind(i);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Plan> items){
        this.items = items;
    }
}

class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
   private Plan currItem;
   private TextView planDetail;
   private TextView planTime;
   private ViewGroup temp;

   public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
       super(inflater.inflate(R.layout.plan_view, parent, false));
       itemView.setOnClickListener(this);

       planDetail = (TextView) itemView.findViewById(R.id.details);
       planTime = (TextView) itemView.findViewById(R.id.date_time);
       this.temp = parent;
   }

   public void bind(Plan i) throws ParseException {
       currItem = i;
       planDetail.setText(i.getDetails());
       String time = i.getTime();
       String date = i.getDate();

       planTime.setText(timeFormat(time)+", " + dateFormat(date));
   }


    @Override
    public void onClick(View v) {

    }

    private static String dateFormat(String str) throws ParseException {
        SimpleDateFormat month_date = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        Date date = sdf.parse(str);
        return month_date.format(date);
    }

    private static String timeFormat(String str){
        int h1 = (int)str.charAt(0) - '0';
        int h2 = (int)str.charAt(1)- '0';

        int hh = h1 * 10 + h2;

        // Finding out the Meridien of time
        // ie. AM or PM
        String Meridien;
        if (hh < 12) {
            Meridien = "AM";
        }
        else
            Meridien = "PM";

        hh %= 12;

        // Handle 00 and 12 case separately
        if (hh == 0) {
            return "12"+str.substring(2)+" " + Meridien;

        }
        else {
            return hh+str.substring(2)+" " + Meridien;
        }

        // After time is printed
        // cout Meridien
    }

}
