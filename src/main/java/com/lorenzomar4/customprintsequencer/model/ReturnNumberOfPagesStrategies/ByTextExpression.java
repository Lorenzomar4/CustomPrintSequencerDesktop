package com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPagesStrategies;

import java.util.List;

public class ByTextExpression implements PageNumberReturner {

    public String regularExpression;

    @Override
    public List<Integer> generateListOfNumber() {
        return null;
    }

    @Override
    public Boolean theNewRangeOverlapsWithMySelf(PageNumberReturner aPageNumberReturner){
        return false;
    }




}
