package com.example.testeditions.Controllers;

import com.example.testeditions.Entites.Schedule;
import com.example.testeditions.Services.ScheduleInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class SechduleController {

    private final ScheduleInter scheduleService;

    @Autowired
    public SechduleController(ScheduleInter scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/ajouterschedule")
    public Schedule addSchedule(@RequestBody Schedule schedule) {
        return scheduleService.ajouterschedule(schedule);
    }

    @GetMapping("/user/{userId}")
    public Schedule getScheduleByUserId(@PathVariable long userId) {
        return scheduleService.getschedulebyuserid(userId);
    }

    @PutMapping("/changeavailability/{scheduleId}")
    public Schedule toggleScheduleAvailability(@PathVariable long scheduleId) {
        return scheduleService.changeavalibilty(scheduleId);
    }

    @GetMapping("/area/{area}")
    public List<Schedule> getScheduleByArea(@PathVariable String area) {
        return scheduleService.getschedulebyarea(area);
    }

    @GetMapping("/date/{date}")
    public List<Schedule> getScheduleByDate(@PathVariable String date) {
        return scheduleService.getschedulebydate(date);
    }
}
