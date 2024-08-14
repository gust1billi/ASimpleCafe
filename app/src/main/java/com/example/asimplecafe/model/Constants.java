package com.example.asimplecafe.model;

import com.example.asimplecafe.model.Product;
import com.example.asimplecafe.model.Receipt;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public List<Product> getDummyProducts(){
        List<Product> objectList = new ArrayList<>();
        objectList.add( new Product() );
        objectList.add( new Product() );
        objectList.add( new Product() );
        objectList.add( new Product() );
        objectList.add( new Product() );
        objectList.add( new Product() );
        objectList.add( new Product() );
        objectList.add( new Product() );
        objectList.add( new Product() );
        objectList.add( new Product() );
        objectList.add( new Product() );
        objectList.add( new Product() );
        objectList.add( new Product() );
        return objectList;
    }

    public List<Receipt> getDummyReceipts(){
        List<Receipt> objectList = new ArrayList<>();
        objectList.add( new Receipt() );
        objectList.add( new Receipt() );
        objectList.add( new Receipt() );
        objectList.add( new Receipt() );
        objectList.add( new Receipt() );
        objectList.add( new Receipt() );
        objectList.add( new Receipt() );
        objectList.add( new Receipt() );
        objectList.add( new Receipt() );
        objectList.add( new Receipt() );
        objectList.add( new Receipt() );
        objectList.add( new Receipt() );
        objectList.add( new Receipt() );
        return objectList;
    }
}
