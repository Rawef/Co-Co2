package com.example.testeditions.Services;

import com.example.testeditions.Entites.Event;
import com.example.testeditions.Entites.Ticket;
import com.example.testeditions.Entites.User;
import com.example.testeditions.Repositories.EventRepository;
import com.example.testeditions.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
