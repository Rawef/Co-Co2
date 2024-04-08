package com.example.testeditions.Repositories;

import com.example.testeditions.Entites.Event;
import com.example.testeditions.Entites.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long>  {
}
