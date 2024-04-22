package com.example.testeditions.Repositories;

import com.example.testeditions.Entites.Event;
import com.example.testeditions.Entites.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long>  {

    List<Event> findByPlace(String place);
    List<Event> findByTime(String time);


}
