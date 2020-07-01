package com.mobiquity.codingevents.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder


public class Event extends AbstractEntity {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private EventDetails eventDetails;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    public void addTag(Tag tag) {
        tags.add(tag);
    }

}