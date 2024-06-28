package org.example.JavaRush.tasks.others.CustomDateJsonSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class Event {
    public String name;
    @JsonSerialize(using = JavaRush.tasks.others.CustomDateJsonSerializer.CustomDateSerializer.class)
    public Date eventDate;

    public Event(String name, Date eventDate) {
        this.name = name;
        this.eventDate = eventDate;
    }
}
