package com.mobiquity.codingevents.models;

import javax.persistence.Entity;
import javax.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EventDetails extends AbstractEntity {

    @Size(max = 500, message = "Description too long!")
    private String description;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

}
