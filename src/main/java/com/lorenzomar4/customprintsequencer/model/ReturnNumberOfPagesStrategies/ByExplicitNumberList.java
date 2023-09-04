package com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPagesStrategies;

import java.util.ArrayList;
import java.util.List;

public class ByExplicitNumberList extends PageNumberReturner {

    List<Integer> listOfPagesNumber = new ArrayList<>();

    @Override
    public List<Integer> generateListOfNumber() {
        return listOfPagesNumber;
    }

    public void addNumberOfPage(Integer pageNumber){
        listOfPagesNumber.add(pageNumber);
    }
}
