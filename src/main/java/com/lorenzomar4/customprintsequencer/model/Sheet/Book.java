package com.lorenzomar4.customprintsequencer.model.Sheet;

public class Book {

    int[] front;
    int[] opposite;

    private  static Book book;
    private Book() {
    }

    public static  Book getInstance(){
        if(book==null){
            book= new Book();
        }
        return book;
    }

    public int[] getFront() {
        return front;
    }

    public void setFront(int[] front) {
        this.front = front;
    }

    public int[] getOpposite() {
        return opposite;
    }

    public void setOpposite(int[] opposite) {
        this.opposite = opposite;
    }
}
