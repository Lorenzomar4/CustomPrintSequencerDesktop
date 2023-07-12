package com.lorenzomar4.customprintsequencer.model;

public class Sheet {
    PageOfSheet front;
    PageOfSheet opposite;


    public static Sheet SheetWithFrontAnOpposite(PageOfSheet front, PageOfSheet opposite) {
        final Sheet anSheet = new Sheet();
        anSheet.setFront(front);
        anSheet.setOpposite(opposite);

        return anSheet;
    }

    public static Sheet SheetOnlyWithFront(PageOfSheet front) {
        final Sheet anSheet = new Sheet();
        anSheet.setFront(front);
        return anSheet;
    }


    public void setFront(PageOfSheet front) {
        this.front = front;
    }


    public void setOpposite(PageOfSheet opposite) {
        this.opposite = opposite;
    }


    public PageOfSheet getFront() {
        return front;
    }

    public PageOfSheet getOpposite() {
        return opposite;
    }
}
