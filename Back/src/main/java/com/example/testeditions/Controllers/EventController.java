package com.example.testeditions.Controllers;

import com.example.testeditions.Entites.Event;
import com.example.testeditions.Repositories.EventRepository;
import com.example.testeditions.Services.Eventserv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private Eventserv eventserv; // Inject the Eventserv service

    // Endpoint to add an event
    @PostMapping("/ajouterEvent")
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        Event addedEvent = eventserv.ajouterEvent(event);
        return new ResponseEntity<>(addedEvent, HttpStatus.CREATED);
    }


    // Add more endpoints as needed for other operations

}

