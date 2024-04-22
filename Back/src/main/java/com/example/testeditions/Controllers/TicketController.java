package com.example.testeditions.Controllers;

import com.example.testeditions.Entites.Ticket;
import com.example.testeditions.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Endpoint to add a ticket
    @PostMapping("/ajuterticket")
    public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket) {
        Ticket addedTicket = ticketService.ajouterticket(ticket);
        return new ResponseEntity<>(addedTicket, HttpStatus.CREATED);
    }

    // Add more endpoints as needed for other operations
}
