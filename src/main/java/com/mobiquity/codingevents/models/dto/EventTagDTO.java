package com.mobiquity.codingevents.models.dto;

import javax.validation.constraints.NotNull;

import com.mobiquity.codingevents.models.Event;
import com.mobiquity.codingevents.models.Tag;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class EventTagDTO {
    @NotNull
    private Event event;

    @NotNull
    private Tag tag;

}
