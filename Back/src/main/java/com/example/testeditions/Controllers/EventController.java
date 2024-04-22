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
    private EventRepository eventRepository;
    @Autowired
    private Eventserv eventserv; // Inject the Eventserv service

    // Endpoint to add an event
    @PostMapping("/ajouterEvent")
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        Event addedEvent = eventserv.ajouterEvent(event);
        return new ResponseEntity<>(addedEvent, HttpStatus.CREATED);
    }

    @GetMapping("/showEvents") // Remove '/events' since it's already in the @RequestMapping
    public List<Event> showEvents() {
        return eventRepository.findAll();
    }    // Add more endpoints as needed for other operations

    // Endpoint to get events by place
    @GetMapping("/geteventsbyplace/{place}")
    public List<Event> getEventsByPlace(@PathVariable String place) {
        return eventserv.getEventsByPlace(place);
    }

    // Endpoint to get events by time
    @GetMapping("/getEventsByTime/{time}")
    public List<Event> getEventsByTime(@PathVariable String time) {
        return eventserv.getEventsByTime(time);
    }

    // Endpoint to delete past events
    @DeleteMapping("/deletePastEvents")
    public ResponseEntity<Void> deletePastEvents() {
        eventserv.deletePastEvents();
        return ResponseEntity.noContent().build();
    }


}

