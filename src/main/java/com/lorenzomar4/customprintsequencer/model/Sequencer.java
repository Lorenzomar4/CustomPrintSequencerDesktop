package com.lorenzomar4.customprintsequencer.model;

import com.lorenzomar4.customprintsequencer.model.PageNumberListStrategies.PageNumberList;
import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;

import java.util.List;
import java.util.stream.Collectors;

public class Sequencer {

    List<PageNumberList> listOfPagesNumbers;

    public void addListOfRangeOfPages(PageNumberList aPageNumberList) throws BusinessException {
       /*
        if(theNewRangeOverlapsWithExistingOnes(aRangeOfPage)){
            throw new BusinessException(" ");
        }*/
        listOfPagesNumbers.add(aPageNumberList);
    }
    /*
    public List<RangeOfPages> getListOfPagesNumbers() {
        return this.listOfPagesNumbers;
    }
    *
     */
    /*
    public boolean theNewRangeOverlapsWithExistingOnes(RangeOfPages aRangeOfPages){
       return getListOfPagesNumbers().stream().anyMatch(aRange -> aRange.overlapingWithMyself(aRangeOfPages) );
    };
    */


    /*
    public List<Integer> returnAllIndexOfPages(){
        return listOfPagesNumbers.stream().flatMap(aRangeOfPages -> aRangeOfPages.getListOfIntegers().stream()).collect(Collectors.toList());
    }
    */

    /*
    public Integer cantOfPages() {
        return returnAllIndexOfPages().stream().mapToInt( numberOfPage -> numberOfPage).sum();

    }

     */

    public void  getPagesToPrint() {
        //..

    }




}
