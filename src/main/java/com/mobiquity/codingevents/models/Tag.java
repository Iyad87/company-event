package com.mobiquity.codingevents.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Tag extends AbstractEntity {

    @ManyToMany(mappedBy = "tags")
    private final List<Event> events = new ArrayList<>();
    @Size(min = 1, max = 25)
    @NotBlank
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return "#" + name + " ";
    }

}