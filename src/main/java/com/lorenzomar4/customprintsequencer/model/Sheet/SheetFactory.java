package com.lorenzomar4.customprintsequencer.model.Sheet;

import com.lorenzomar4.customprintsequencer.model.PageOfSheet;

import java.util.List;

public class SheetFactory {

    Integer cantOfPages;
    List<Integer> listOfNumber;

    public SheetFactory(Integer lastPage, List<Integer> listOfNumber) {
        this.cantOfPages = lastPage;
        this.listOfNumber = listOfNumber;
    }

    public ISheet returnSheet(Integer index) {

        return index + 1 < cantOfPages ?
                new DoubleSidedSheet(
                        new PageOfSheet(listOfNumber.get(index)),
                        new PageOfSheet(listOfNumber.get(index + 1))) :
                new SingleSidedSheet(new PageOfSheet(listOfNumber.get(index)));

    }

}


