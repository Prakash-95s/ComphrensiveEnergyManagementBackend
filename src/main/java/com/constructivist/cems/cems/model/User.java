package com.constructivist.cems.cems.model;

import com.constructivist.cems.cems.model.HomeEnergy;
import com.constructivist.cems.cems.model.UserId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "app_user")
@Data
public class User {

    @EmbeddedId
    @Column(name = "user_id",nullable = false,insertable = false,updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UserId userId;

    private String name;
    private String tariffPlan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "home_id")
    private HomeEnergy homeEnergy; // Each user is associated with one home
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transport> transports;

    private boolean twoFactorEnabled;
    private String twoFactorMethod; // e.g., "SMS", "EMAIL", "AUTH_APP"
    private String twoFactorPhoneNumber; // For SMS 2FA
    private String twoFactorEmail; // For Email 2FA
    private double score; // For leaderboard rankings

    // Getters and Setters
    // ...
    @JsonCreator
    public User(@JsonProperty("userId") UserId userId,
                @JsonProperty("name") String name,
                @JsonProperty("tariffPlan") String tariffPlan,
                @JsonProperty("homeEnergy") HomeEnergy homeEnergy,
                @JsonProperty("city") City city,
                @JsonProperty("transports") List<Transport> transports,
                @JsonProperty("twoFactorEnabled") boolean twoFactorEnabled,
                @JsonProperty("twoFactorMethod") String twoFactorMethod,
                @JsonProperty("twoFactorPhoneNumber") String twoFactorPhoneNumber,
                @JsonProperty("twoFactorEmail") String twoFactorEmail,
                @JsonProperty("score") int score) {
        this.userId = userId;
        this.name = name;
        this.tariffPlan = tariffPlan;
        this.homeEnergy = homeEnergy;
        this.city = city;
        this.transports = transports;
        this.twoFactorEnabled = twoFactorEnabled;
        this.twoFactorMethod = twoFactorMethod;
        this.twoFactorPhoneNumber = twoFactorPhoneNumber;
        this.twoFactorEmail = twoFactorEmail;
        this.score = score;
    }

}
