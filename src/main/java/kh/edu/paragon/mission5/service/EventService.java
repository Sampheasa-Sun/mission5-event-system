package kh.edu.paragon.mission5.service;

import kh.edu.paragon.mission5.model.Event;
import kh.edu.paragon.mission5.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // 1. End User Flow: Check Redis FIRST. If empty, query Postgres and save to Redis.
    @Cacheable(value = "eventsList")
    public List<Event> getAllEvents() {
        System.out.println("⚠️ FETCHING FROM POSTGRESQL (Cache was empty!)");
        return eventRepository.findAll();
    }

    // 2. Admin Flow: Save to Postgres, then completely wipe the Redis cache so it stays synced.
    @CacheEvict(value = "eventsList", allEntries = true)
    public Event saveEvent(Event event) {
        System.out.println("💾 SAVING TO POSTGRESQL & WIPING REDIS CACHE");
        return eventRepository.save(event);
    }

    // 3. Manual Cache Control
    @CacheEvict(value = "eventsList", allEntries = true)
    public void refreshCache() {
        System.out.println("🧹 MANUAL CACHE REFRESH TRIGGERED: Redis wiped.");
    }
}