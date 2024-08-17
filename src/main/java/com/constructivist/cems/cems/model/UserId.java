package com.constructivist.cems.cems.model;

import jakarta.persistence.Embeddable;
import lombok.Data;


import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class UserId implements Serializable {
    private Long userId;
    private Long foreignKeyId;

    public UserId(Long id, Long foreignKeyId) {
        this.userId = id;
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
