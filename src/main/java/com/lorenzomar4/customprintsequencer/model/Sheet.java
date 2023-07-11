package com.lorenzomar4.customprintsequencer.model;

public class Sheet {
    PageNumber front;
    PageNumber opposite;


    public static Sheet SheetWithFrontAnOpposite(PageNumber front, PageNumber opposite) {
        final Sheet anSheet = new Sheet();
        anSheet.setFront(front);
        anSheet.setOpposite(opposite);

        return anSheet;
    }

    public static Sheet SheetOnlyWithFront(PageNumber front) {
        final Sheet anSheet = new Sheet();
        anSheet.setFront(front);
        return anSheet;
    }


    public PageNumber getFront() {
        return front;
    }

    public void setFront(PageNumber front) {
        this.front = front;
    }

    public PageNumber getOpposite() {
        return opposite;
    }

    public void setOpposite(PageNumber opposite) {
        this.opposite = opposite;
    }
}
