package com.example.testeditions.Services;

import com.example.testeditions.Entites.Event;
import com.example.testeditions.Entites.Ticket;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Repositories.EventRepository;
import com.example.testeditions.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class Eventserv implements Eventinter {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Event ajouterEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> GetUserEvent(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            List<Event> userEvents = new ArrayList<>();
            for (Ticket ticket : user.getTickets()) {
                userEvents.add(ticket.getEvent());
            }
            return userEvents;
        } else {
            return Collections.emptyList();
        }
    }
    @Override
    public List<Event> showEvents() {
        return eventRepository.findAll();
    }
    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public List<Event> getEventsByPlace(String place) {
        return eventRepository.findByPlace(place);
    }

    @Override
    public List<Event> getEventsByTime(String time) {
        return eventRepository.findByTime(time);
    }

    @Override
    public void deletePastEvents() {
        LocalDateTime now = LocalDateTime.now();
        List<Event> events = eventRepository.findAll();
        for (Event event : events) {
            LocalDateTime eventTime = LocalDateTime.parse(event.getTime());
            if (eventTime.isBefore(now)) {
                eventRepository.delete(event);
            }
        }
    }



}
