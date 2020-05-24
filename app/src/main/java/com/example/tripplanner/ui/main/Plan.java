package com.example.tripplanner.ui.main;
import java.util.UUID;

public class Plan {
    private UUID id;
    private String details;
    private String date;
    private String time;

    public Plan(){
        this.id = UUID.randomUUID();
    }

    public Plan(UUID id){
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
