package com.anthonyo.kfc.kfc.mappers;

import java.util.*;

import com.anthonyo.kfc.kfc.repository.SaleMovRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;



class Menu {
    public String menuName;
    public int nbSold;
    public int totalPrice;

    public Menu(String menuName, int nbSold, int totalPrice) {
        this.menuName = menuName;
        this.nbSold = nbSold;
        this.totalPrice = totalPrice;
    }
}


class Restaurant {
    public int restaurant_id;
    public String place;
    public List<Menu> detailMenu;

    public Restaurant(int restaurant_id, String place) {
        this.restaurant_id = restaurant_id;
        this.place = place;
        this.detailMenu = new ArrayList<>();
    }

    public void addMenu(Menu menu) {
        this.detailMenu.add(menu);
    }
}


public class Main {

    public static void main(String[] args) {
        List<Map<String, Object>> data = Arrays.asList(
                createData(7, "Anosibe", "Hot dog", 8, 20000),
                createData(7, "Anosibe", "Burger", 4, 15000),
                createData(9, "abositra", "Hot dog", 2, 5000),
                createData(7, "abositra", "Hot dog", 2, 5000)
        );

        Map<Integer, Restaurant> restaurantMap = new HashMap<>();

        for (Map<String, Object> row : data) {
            int restaurantId = (int) row.get("restaurant_id");
            String place = (String) row.get("place");
            String menuName = (String) row.get("name");
            int nbSold = (int) row.get("nbSold");
            int totalPrice = (int) row.get("totalPrice");

            restaurantMap.putIfAbsent(restaurantId, new Restaurant(restaurantId, place));
            restaurantMap.get(restaurantId).addMenu(new Menu(menuName, nbSold, totalPrice));
        }

        Collection<Restaurant> restaurants = restaurantMap.values();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(restaurants);
            System.out.println(jsonResult);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Object> createData(int restaurantId, String place, String name, int nbSold, int totalPrice) {
        Map<String, Object> data = new HashMap<>();
        data.put("restaurant_id", restaurantId);
        data.put("place", place);
        data.put("name", name);
        data.put("nbSold", nbSold);
        data.put("totalPrice", totalPrice);
        return data;
    }
}
