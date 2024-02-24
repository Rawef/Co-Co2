package com.example.testeditions.Controllers;

import com.example.testeditions.Entites.Schedule;
import com.example.testeditions.Services.ScheduleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    ScheduleInterface scheduleInterface;

    @PostMapping("/ajouterSchedule")
    public Schedule ajouterSchedule(@RequestBody Schedule schedule){

        return scheduleInterface.ajouterSchedule(schedule);
    }

    @GetMapping("/Afficher/{id}")
    public List<Schedule> AfficherUserSchedule(@PathVariable Long id){
        return scheduleInterface.GetUserSchedule(id);
    }

}
