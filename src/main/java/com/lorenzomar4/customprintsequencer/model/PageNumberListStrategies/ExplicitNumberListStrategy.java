package com.lorenzomar4.customprintsequencer.model.PageNumberListStrategies;

import java.util.ArrayList;
import java.util.List;

public class ExplicitNumberListStrategy implements  PageNumberList{

    List<Integer> listOfPagesNumber = new ArrayList<>();

    @Override
    public List<Integer> generateListOfNumber() {
        return listOfPagesNumber;
    }

    public void addNumberOfPage(Integer pageNumber){
        listOfPagesNumber.add(pageNumber);
    }
}
