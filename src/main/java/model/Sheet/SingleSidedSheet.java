package model.Sheet;


import model.PageOfSheet;

public class SingleSidedSheet implements  ISheet{

    PageOfSheet frontSide;

    public SingleSidedSheet(PageOfSheet frontSide) {
        this.frontSide = frontSide;
    }

    @Override
    public PageOfSheet getFrontSide() {
        return frontSide;
    }

    @Override
    public PageOfSheet getBackSide() {
        return null;
    }
}
