package com.example.demo;

import com.example.demo.Model.Inventory;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final MongoTemplate mongoTemplate;

    public Inventory getById(String id) {
        Optional<Inventory> inv = inventoryRepository.findById(id);
        if (inv.isPresent()) {
            Inventory res = inv.get();
            return res;
        }
        return null;
    }

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public List<Inventory> findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        List<Inventory> invs = mongoTemplate.find(query, Inventory.class);
        return invs;
    }

    public List<Inventory> findByInfo(String id, String name, String sender, String recipient, String from,
                                      String current, String to, String amount) {
        Query query = new Query();
        if (id != null && id != "") {
            query.addCriteria(Criteria.where("id").is(id));
        }
        if (name != null && name != "") {
            query.addCriteria(Criteria.where("name").is(name));
        }
        if (sender != null && sender != "") {
            query.addCriteria(Criteria.where("sender").is(sender));
        }
        if (recipient != null && recipient != "") {
            query.addCriteria(Criteria.where("recipient").is(recipient));
        }
        if (from != null && from != "") {
            query.addCriteria(Criteria.where("from").is(from));
        }
        if (current != null && current != "") {
            query.addCriteria(Criteria.where("current").is(current));
        }
        if (to != null && to != "") {
            query.addCriteria(Criteria.where("to").is(to));
        }
        if (amount != null && !amount.equals("")) {
            query.addCriteria(Criteria.where("amount").is(Integer.parseInt(amount)));
        }
        List<Inventory> invs = mongoTemplate.find(query, Inventory.class);
        return invs;
    }
    public void insertInventory(Inventory inventory) {
        inventoryRepository.insert(inventory);
    }

    public void update(String id, String name, String sender, String recipient, String from,
                       String current, String to, String amount) {
        Inventory inv = getById(id);
        if (name != null && !name.equals("")) {
            inv.setName(name);
        }
        if (sender != null && !sender.equals("")) {
            inv.setSender(sender);
        }
        if (recipient != null && !recipient.equals("")) {
            inv.setRecipient(recipient);
        }
        if (from != null && !from.equals("")) {
            inv.setFrom(from);
        }
        if (current != null && !current.equals("")) {
            inv.setCurrent(current);
        }
        if (to != null && !to.equals("")) {
            inv.setTo(to);
        }
        if (amount != null && !amount.equals("")) {
            inv.setAmount(Integer.parseInt(amount));
        }
        inv.setLastUpdate(LocalDateTime.now());
        inventoryRepository.save(inv);
    }
    public void deleteInventory(String id) {
        inventoryRepository.deleteById(id);
    }
}
