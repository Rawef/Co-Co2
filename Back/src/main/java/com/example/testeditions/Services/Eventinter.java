package com.example.testeditions.Services;

import com.example.testeditions.Entites.Event;

import java.util.List;

public interface Eventinter {

    public Event ajouterEvent(Event event);
    public List<Event> GetUserEvent(Long id);
}
