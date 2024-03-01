package com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPages;

import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

public class ByRangeNumber implements PageNumberReturner {

    Integer initialRange = 0;
    Integer finalRange = 0;

    public ByRangeNumber(Integer initialRange, Integer finalRange) throws BusinessException {
        this.initialRange = initialRange;
        this.finalRange = finalRange;
    }

    public ByRangeNumber() {}

    @Override
    public int[] generateListOfNumber() {
        int lenght = Math.abs(finalRange-initialRange)+1;
        int[] listOfInteger = new int[lenght];
        int index = 0;

        for (int i = this.initialRange; i <= this.finalRange; i++) {
            listOfInteger[index]=i;
            index++;
        }

        return listOfInteger;
    }


    public void setInitialRange(Integer initialRange) throws BusinessException {

        this.initialRange = initialRange;

    }

    public void setFinalRange(Integer finalRange) throws BusinessException {
        this.finalRange = finalRange;
    }


}
