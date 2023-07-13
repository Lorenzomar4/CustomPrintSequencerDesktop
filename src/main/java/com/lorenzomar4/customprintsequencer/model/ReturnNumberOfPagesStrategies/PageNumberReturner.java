package com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPagesStrategies;

import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;

import java.util.List;

public interface PageNumberReturner {

    public List<Integer> generateListOfNumber();

    default public Boolean theNewRangeOverlapsWithMySelf(PageNumberReturner aPageNumberReturner){


        return  (generateListOfNumber().size()==0) ? true :  aPageNumberReturner.generateListOfNumber().stream().anyMatch(aNumberPage->generateListOfNumber().contains(aNumberPage));
    }

    default public void ifThereAreOverlapsThenExceptionIsThrown(PageNumberReturner aPageNumberReturner) throws BusinessException {
        if(theNewRangeOverlapsWithMySelf(aPageNumberReturner)){
            throw new BusinessException("La nueva pagina ingresada ya existe");
        }
    }

    default public void addlistOfPageNumbersNotConsidered(PageNumberReturner aPageNumberReturner) throws BusinessException {

    };



}
