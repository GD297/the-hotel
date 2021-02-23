/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinhgt.objects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Cart {

    private List<Item> items;
    private int price;

    public int getPrice() {
        return price;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItemToCart(Item hotel) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        items.add(hotel);

    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }
}
