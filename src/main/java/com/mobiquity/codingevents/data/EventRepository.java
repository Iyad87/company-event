package com.mobiquity.codingevents.data;

import com.mobiquity.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Store Objects in DB
@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
