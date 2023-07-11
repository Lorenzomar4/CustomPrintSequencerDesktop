package com.lorenzomar4.customprintsequencer.model.PageNumberListStrategies;

import java.util.List;
//ListOfNumberByRegularExpression
public class ListOfNumberByRegularExpression implements PageNumberList{

    public String regularExpression;

    @Override
    public List<Integer> generateListOfNumber() {
        return null;
    }

    @Override
    public Boolean theNewRangeOverlapsWithMySelf(PageNumberList aPageNumberList){
        return false;
    }




}
