package com.example.testeditions.Repositories;

import com.example.testeditions.Entites.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Ticketrepository  extends JpaRepository<Ticket,Long> {
}
