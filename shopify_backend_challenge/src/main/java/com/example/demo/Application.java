package com.example.demo;

import com.example.demo.Model.Inventory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runner(InventoryService inventoryService) {
		return args -> {
			List<String> names = List.of("Offer", "Medicine", "Toy","Paper","Game","Computer","iphone","Bed","Table",
					"Jackets","Porsche");
			List<String> senders = List.of("Shopify", "aa", "bbb","pp","cc","dd","ee","ff","gg", "hh","ii");
			List<String> recipients = List.of("Bowen Li", "jj","kk","ll","mm","nn","oo","pp", "yy","rr","nn");
			List<String> from = List.of("California", "Seattle","Boston","New York","Los Angel","San Francisco",
					"Austin","Beijing", "Shanghai","Shijiazhuang","Suzhou");
			List<String> current = List.of("California", "London","New York","Pairs","Shanghai","Beijing","Tianjin",
					"Moscow", "Washington DC","Portland","Sydney");
			List<String> to = List.of("Seattle", "Chongqing","Chengdu","Tokyo","Huston","Vancouver","Toronto",
					"San Diego", "San Mateo","Bellevue","Amherst");
			List<Integer> amount = List.of(1, 1000,2200,4050,1390,2532,5324,345,245,654,9432);
			for (int i = 0; i < names.size(); i++) {
				List<Inventory> find = inventoryService.findByName(names.get(i));
				if (find.size() == 0) {
					inventoryService.insertInventory(new Inventory(names.get(i), senders.get(i),
							recipients.get(i), from.get(i), current.get(i), to.get(i), amount.get(i)));
				}
				else {
					continue;
				}
			}
		};
	}
}
