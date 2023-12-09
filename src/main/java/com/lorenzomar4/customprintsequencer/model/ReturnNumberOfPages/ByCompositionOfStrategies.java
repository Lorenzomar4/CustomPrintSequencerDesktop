package com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPages;

import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class  ByCompositionOfStrategies implements PageNumberReturner {

    public List<PageNumberReturner> listOfPageNumberReturner = new ArrayList<>();

    public void addNewPageNumberReturner(PageNumberReturner aPageNumberReturner) throws BusinessException {

        listOfPageNumberReturner.add(aPageNumberReturner);
    }

    public void deleteAPageNumberReturner(PageNumberReturner aPageNumberReturner){
        listOfPageNumberReturner.remove(aPageNumberReturner);
    }

    @Override
    public List<Integer> generateListOfNumber() {
        return listOfPageNumberReturner.stream().flatMap(aRangeOfPages -> aRangeOfPages.generateListOfNumber().stream()).collect(Collectors.toList());
    }



}
