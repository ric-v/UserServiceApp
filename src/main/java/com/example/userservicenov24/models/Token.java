package com.example.userservicenov24.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Token extends BaseModel {
    private String value;
    @ManyToOne
    private User user;
    private long expiryAt;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getExpiryAt() {
        return expiryAt;
    }

    public void setExpiryAt(long expiryAt) {
        this.expiryAt = expiryAt;
    }

    //private long userId;
}
