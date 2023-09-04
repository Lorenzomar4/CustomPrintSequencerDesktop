package com.lorenzomar4.customprintsequencer.model.Sheet;

import com.lorenzomar4.customprintsequencer.model.PageOfSheet;

public class DoubleSidedSheet implements  ISheet{

    PageOfSheet frontSide;
    PageOfSheet backSide;

    public DoubleSidedSheet(PageOfSheet front, PageOfSheet opposite) {
        this.frontSide = front;
        this.backSide = opposite;
    }

    @Override
    public PageOfSheet getFrontSide() {
        return frontSide;
    }

    @Override
    public PageOfSheet getBackSide() {
        return backSide;
    }
}
