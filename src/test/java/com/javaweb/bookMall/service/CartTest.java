package com.javaweb.bookMall.service;

import com.javaweb.bookMall.bean.Cart;
import com.javaweb.bookMall.bean.CartItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;



class CartTest {
    Cart cart = new Cart();

    @Test
    void addItem() {

        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100), new BigDecimal(100)));

        System.out.println(cart);
    }

    @Test
    void deleteItem() {
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100), new BigDecimal(100)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    void updateCount() {
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100), new BigDecimal(100)));
        cart.deleteItem(1);
        cart.clear();

        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100), new BigDecimal(100)));


        cart.updateCount(2,10);
        System.out.println(cart);


    }

    @Test
    void clear() {
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100), new BigDecimal(100)));
        cart.clear();
        System.out.println(cart);
    }
}