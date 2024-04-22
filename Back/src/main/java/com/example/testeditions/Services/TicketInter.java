package com.example.testeditions.Services;

import com.example.testeditions.Entites.Ticket;

import java.util.List;

public interface TicketInter {


    public Ticket ajouterticket(Ticket ticket) ;
    public Ticket GetticketbyUserID(int id);
    public Ticket Updateticket (int id , Ticket ticket);

    public List<Ticket> showtickets();
}
