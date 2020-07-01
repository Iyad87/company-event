package com.mobiquity.codingevents.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private int id;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity entity = (AbstractEntity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
