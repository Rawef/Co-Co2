package com.example.testeditions.Services;

import com.example.testeditions.Entites.Schedule;

import java.util.List;

public interface ScheduleInterface {

    public Schedule ajouterSchedule (Schedule schedule);
    public List<Schedule> GetUserSchedule(Long id);
}
