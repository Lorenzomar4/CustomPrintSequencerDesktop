package model.ReturnNumberOfPages;


import model.exception.BusinessException;

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

    public List<Integer> getListOfIntegers() {
        List<Integer> listOfInteger = new ArrayList<>();

        for (int i = this.initialRange; i <= this.finalRange; i++) {
            listOfInteger.add(i);
        }
        return listOfInteger;
    }


    @Override
    public List<Integer> generateListOfNumber() {
        return getListOfIntegers();
    }


    public void setInitialRange(Integer initialRange) throws BusinessException {

        this.initialRange = initialRange;

    }

    public void setFinalRange(Integer finalRange) throws BusinessException {

        this.finalRange = finalRange;

    }


}
