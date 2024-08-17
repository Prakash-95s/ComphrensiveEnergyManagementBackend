package com.constructivist.cems.cems.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.Data;


import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class UserId implements Serializable {
    private Long userId;
    private Long foreignKeyId;
    @JsonCreator
    public UserId(@JsonProperty("userId") Long userId,
                  @JsonProperty("foreignKeyId") Long foreignKeyId) {
        this.userId = userId;
        this.foreignKeyId = foreignKeyId;
    }


    // Constructors, getters, setters, equals, and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(userId, userId.userId) && Objects.equals(foreignKeyId, userId.foreignKeyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, foreignKeyId);
    }
}
