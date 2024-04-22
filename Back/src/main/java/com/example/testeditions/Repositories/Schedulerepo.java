package com.example.testeditions.Repositories;

import com.example.testeditions.Entites.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Schedulerepo extends JpaRepository<Schedule,Long> {
    Schedule findByUserId(long id);

    List<Schedule> findByArea(String area);

    List<Schedule> findByDate(String date);
}
