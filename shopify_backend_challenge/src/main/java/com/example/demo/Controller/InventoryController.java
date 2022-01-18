package com.example.demo.Controller;

import com.example.demo.InventoryService;
import com.example.demo.Model.Inventory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("addInventory")
    public String addInventory(@ModelAttribute Inventory inventory) {
        System.out.println(inventory);
        inventoryService.insertInventory(inventory);
        return "redirect:/";
    }

    @GetMapping("search")
    public String search(Model model, String id, String name, String sender, String recipient, String from,
                         String current, String to, String amount) {
        List<Inventory> invs = inventoryService.findByInfo(id, name, sender, recipient, from, current, to, amount);
        model.addAttribute("inventories", invs);
        return "listInventories";
    }

    @GetMapping("showAddForm")
    public String showAddForm() {
        return "AddInventory";
    }

    @GetMapping("list")
    public String fetchAllInventories(Model model) {
        model.addAttribute("inventories", inventoryService.getAllInventories());
        return "listInventories";
    }

    //delete inventory
    @RequestMapping(value="/doDelete/{id}", method = RequestMethod.GET)
    public String deleteInventory (@PathVariable String id) {
        inventoryService.deleteInventory(id);
        return "redirect:/list";
    }

    @GetMapping("showEditForm/{id}")
    public String showEditForm(Model model, @PathVariable String id) {
        model.addAttribute("inventory", inventoryService.getById(id));
        return "editInventory";
    }

    //edit inventory
    @RequestMapping(value="edit/{id}", method = RequestMethod.GET)
    public String editInventory (@PathVariable String id, String name, String sender, String recipient, String from,
                                 String current, String to, String amount) {
        System.out.println("id: " + id);
        inventoryService.update(id, name, sender, recipient, from, current, to, amount);
        return "redirect:/list";
    }
}
