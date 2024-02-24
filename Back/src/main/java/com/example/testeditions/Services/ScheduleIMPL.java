package com.example.testeditions.Services;

import com.example.testeditions.Entites.Schedule;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Repositories.ScheduleRepository;
import com.example.testeditions.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleIMPL implements ScheduleInterface{
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Schedule ajouterSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> GetUserSchedule(Long id) {
        User user=userRepository.findById(id).get();
        return user.getSchedules();
    }
}
