package com.lorenzomar4.customprintsequencer.model.PageNumberListStrategies;

import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;

import java.util.List;

public interface   PageNumberList {

    public List<Integer> generateListOfNumber();

    default public Boolean theNewRangeOverlapsWithMySelf(PageNumberList aPageNumberList){
        return aPageNumberList.generateListOfNumber().stream().anyMatch(aNumberPage->generateListOfNumber().contains(aNumberPage));
    }

    default public void ifThereAreOverlapsThenExceptionIsThrown(PageNumberList aPageNumberList) throws BusinessException {
        if(theNewRangeOverlapsWithMySelf(aPageNumberList)){
            throw new BusinessException("La nueva pagina ingresada ya existe");
        }
    }

    default public void addlistOfPageNumbersNotConsidered(PageNumberList aPageNumberList) throws BusinessException {

    };



}
