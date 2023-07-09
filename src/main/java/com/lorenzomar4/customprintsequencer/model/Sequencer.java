package com.lorenzomar4.customprintsequencer.model;

import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;

import java.util.List;
import java.util.stream.Collectors;

public class Sequencer {

    List<RangeOfPages> listOfRangeOfPages;

    public void addListOfRangeOfPages(RangeOfPages aRangeOfPage) throws BusinessException {
        if(theNewRangeOverlapsWithExistingOnes(aRangeOfPage)){
            throw new BusinessException(" ");
        }
        listOfRangeOfPages.add(aRangeOfPage);
    }

    public List<RangeOfPages> getListOfRangeOfPages() {
        return this.listOfRangeOfPages;
    }

    public boolean theNewRangeOverlapsWithExistingOnes(RangeOfPages aRangeOfPages){
       return getListOfRangeOfPages().stream().anyMatch(aRange -> aRange.overlapingWithMyself(aRangeOfPages) );
    };

    public List<Integer> returnAllIndexOfPages(){
        return listOfRangeOfPages.stream().flatMap( aRangeOfPages -> aRangeOfPages.getListOfIntegers().stream()).collect(Collectors.toList());
    }

    public Integer cantOfPages() {
        return returnAllIndexOfPages().stream().mapToInt( numberOfPage -> numberOfPage).sum();

    }

    public void  getPagesToPrint() {
    //..

    }




}
