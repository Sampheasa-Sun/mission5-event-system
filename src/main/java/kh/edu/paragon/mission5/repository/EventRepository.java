package kh.edu.paragon.mission5.repository;

import kh.edu.paragon.mission5.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}