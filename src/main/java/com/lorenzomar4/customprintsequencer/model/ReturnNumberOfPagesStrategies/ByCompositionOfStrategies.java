package com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPagesStrategies;

import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class  ByCompositionOfStrategies extends PageNumberReturner {

    public List<PageNumberReturner> listOfPageNumberReturner = new ArrayList<>();


    public void addNewPageNumberReturner(PageNumberReturner aPageNumberReturner) throws BusinessException {
        incomeValidation(aPageNumberReturner);
        listOfPageNumberReturner.add(aPageNumberReturner);
    }

    public void deleteAPageNumberReturner(PageNumberReturner aPageNumberReturner){
        listOfPageNumberReturner.remove(aPageNumberReturner);
    }

    @Override
    public List<Integer> generateListOfNumber() {
        return listOfPageNumberReturner.stream().flatMap(aRangeOfPages -> aRangeOfPages.generateListOfNumber().stream()).collect(Collectors.toList());
    }


    private void incomeValidation(PageNumberReturner aPageNumberReturner) {
        listOfPageNumberReturner.forEach(aPageNumberListOption -> {
            try {
                aPageNumberListOption.ifThereAreOverlapsThenExceptionIsThrown(aPageNumberReturner);
            } catch (BusinessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
