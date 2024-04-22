package com.example.testeditions.Services;

import com.example.testeditions.Entites.Schedule;
import com.example.testeditions.Repositories.Schedulerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Scheduleserv implements ScheduleInter {

    private final Schedulerepo scheduleRepository;

    @Autowired
    public Scheduleserv(Schedulerepo schedulerepo) {
        this.scheduleRepository = schedulerepo;
    }

    @Override
    public Schedule ajouterschedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule getschedulebyuserid(long id) {
        return scheduleRepository.findByUserId(id);
    }

    @Override
    public Schedule changeavalibilty(long id) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);
        if (schedule != null) {
            // Toggle the availability
            if ("available".equals(schedule.getAvailability())) {
                schedule.setAvailability("unavailable");
            } else {
                schedule.setAvailability("available");
            }
            return scheduleRepository.save(schedule);
        }
        return null; // Or handle the case where schedule is not found
    }

    @Override
    public List<Schedule> getschedulebyarea(String area) {
        return scheduleRepository.findByArea(area);
    }

    @Override
    public List<Schedule> getschedulebydate(String date) {
        return scheduleRepository.findByDate(date);
    }
}
