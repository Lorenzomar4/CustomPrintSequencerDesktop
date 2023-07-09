package com.lorenzomar4.customprintsequencer.model;

public class Sheet {
    Page front;
    Page opposite = null ;

    public Sheet(Page front, Page opposite){
        this.front= front;
        this.opposite = opposite;
    }


}
