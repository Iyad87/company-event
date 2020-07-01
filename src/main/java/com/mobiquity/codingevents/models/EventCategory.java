package com.mobiquity.codingevents.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EventCategory extends AbstractEntity {

    @OneToMany(mappedBy = "eventCategory")
    private final List<Event> events = new ArrayList<>();
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;
}