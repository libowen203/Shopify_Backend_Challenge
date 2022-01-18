package com.example.demo.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
@Data
@Document
public class Inventory {
    @Id
    private String id;
    private String name;
    private String sender;
    private String recipient;
    private String from;
    private String current;
    private String to;
    private int amount;
    private LocalDateTime lastUpdate;

    public Inventory(String name, String sender, String recipient, String from, String current, String to, int amount) {
        this.name = name;
        this.sender = sender;
        this.recipient = recipient;
        this.from = from;
        this.current = current;
        this.to = to;
        this.amount = amount;
        this.lastUpdate = LocalDateTime.now();
    }
}
