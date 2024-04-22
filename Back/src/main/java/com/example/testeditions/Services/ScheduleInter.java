package com.example.testeditions.Services;

import com.example.testeditions.Entites.Schedule;

import java.util.List;

public interface ScheduleInter {

    public Schedule ajouterschedule(Schedule schedule) ;

    public Schedule getschedulebyuserid (long id ) ;

    public Schedule changeavalibilty (long id) ;

    public List<Schedule> getschedulebyarea(String area);

    public List<Schedule> getschedulebydate (String date) ;
}
