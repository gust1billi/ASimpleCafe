package com.example.asimplecafe.model;

import com.example.asimplecafe.Utils;
import com.example.asimplecafe.model.Product;
import com.example.asimplecafe.model.Receipt;

import java.util.ArrayList;
import java.util.List;

public class ConstantValues {
    public static List<Cart> getDummyCartItems(){
        List<Cart> objectList = new ArrayList<>();
        objectList.add( new Cart("a", 1000, 1) );
        objectList.add( new Cart("b", 1000, 5) );
        objectList.add( new Cart("c", 1000, 3) );
        objectList.add( new Cart("d", 1000, 20) );
        objectList.add( new Cart("e", 1000, 99) );
        objectList.add( new Cart("f", 1000, 25) );
        objectList.add( new Cart("g", 1000, 13) );
        objectList.add( new Cart("af", 1000, 11) );
        objectList.add( new Cart("ah", 1000, 12) );
        objectList.add( new Cart("ai", 1000, 15) );
        objectList.add( new Cart("aj", 1000, 231) );
        objectList.add( new Cart("ak", 1000, 11) );
        objectList.add( new Cart("al", 1000, 14) );
        objectList.add( new Cart("am", 1000, 51) );
        return objectList;
    }

    public static List<Product> getDummyProducts(){
        List<Product> objectList = new ArrayList<>();
        objectList.add( new Product( "abc a", 50000 ) );
        objectList.add( new Product( "abc b", 3000 ) );
        objectList.add( new Product( "abc c", 5000 ) );
        objectList.add( new Product( "abc d", 6000) );
        objectList.add( new Product( "abc e", 15000) );
        objectList.add( new Product( "abc f", 25000) );
        objectList.add( new Product( "abc g", 3500 ) );
        objectList.add( new Product( "abc h", 15000) );
        objectList.add( new Product( "abc i", 1500) );
        objectList.add( new Product( "abc j", 20000) );
        objectList.add( new Product( "abc k", 500000) );
        objectList.add( new Product( "abc l", 5500) );
        objectList.add( new Product( "abc m", 45000) );
        return objectList;
    }

    public static List<Receipt> getDummyReceipts(){
        List<Receipt> objectList = new ArrayList<>();

        objectList.add( new Receipt(1 , 51000, Utils.getDateTime( )) );
        objectList.add( new Receipt(2 , 55000, Utils.getDateTime( )) );
        objectList.add( new Receipt(3 , 23000, Utils.getDateTime( )) );
        objectList.add( new Receipt(4 , 630000, Utils.getDateTime( )) );
        objectList.add( new Receipt(5 , 123000, Utils.getDateTime( )) );
        objectList.add( new Receipt(6 , 80000, Utils.getDateTime( )) );
        objectList.add( new Receipt(7 , 10000, Utils.getDateTime( )) );
        objectList.add( new Receipt(8 , 15000, Utils.getDateTime( )) );
        objectList.add( new Receipt(9 , 30000, Utils.getDateTime( )) );
        objectList.add( new Receipt(10, 62000, Utils.getDateTime( )) );
        objectList.add( new Receipt(11, 27000, Utils.getDateTime( )) );
        objectList.add( new Receipt(12, 35000, Utils.getDateTime( )) );
        objectList.add( new Receipt(13, 12000, Utils.getDateTime( )) );
        objectList.add( new Receipt(14, 90000, Utils.getDateTime( )) );
        return objectList;
    }
}
