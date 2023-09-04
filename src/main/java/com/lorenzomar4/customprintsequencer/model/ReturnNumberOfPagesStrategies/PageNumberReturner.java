package com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPagesStrategies;

import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;

import java.util.List;


public abstract class  PageNumberReturner {

    public abstract List<Integer> generateListOfNumber();

     public Boolean theNewRangeOverlapsWithMySelf(PageNumberReturner aPageNumberReturner){

        return generateListOfNumber().isEmpty() || aPageNumberReturner.generateListOfNumber().stream().anyMatch(aNumberPage -> generateListOfNumber().contains(aNumberPage));
    }

    public void ifThereAreOverlapsThenExceptionIsThrown(PageNumberReturner aPageNumberReturner) throws BusinessException {
        if(theNewRangeOverlapsWithMySelf(aPageNumberReturner)){
            throw new BusinessException("La nueva pagina ingresada ya existe");
        }
    }

    public void addlistOfPageNumbersNotConsidered(PageNumberReturner aPageNumberReturner) throws BusinessException {

    };



}
