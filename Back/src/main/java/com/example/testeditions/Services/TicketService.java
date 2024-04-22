package com.example.testeditions.Services;

import com.example.testeditions.Entites.Ticket;
import com.example.testeditions.Repositories.EventRepository;
import com.example.testeditions.Repositories.Ticketrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TicketService implements TicketInter{


    @Autowired
    Ticketrepository ticketrepository ;


    @Override
    public Ticket ajouterticket(Ticket ticket) {
        return (ticketrepository.save(ticket));
    }

    @Override
    public Ticket GetticketbyUserID(int id) {
        return null;
    }

    @Override
    public Ticket Updateticket(int id, Ticket ticket) {
        return null;
    }

    @Override
    public List<Ticket> showtickets() {
        return null;
    }
}
