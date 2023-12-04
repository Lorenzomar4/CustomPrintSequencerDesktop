package com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPagesStrategies;

import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ByRangeNumber extends PageNumberReturner {

    Integer initialRange = 0;
    Integer finalRange = 0;

    List<PageNumberReturner> listOfPageNumbersNotConsidered = new ArrayList<>();




    public ByRangeNumber(Integer initialRange, Integer finalRange) throws BusinessException {

        this.initialRange = initialRange;
        this.finalRange = finalRange;
    }

    public ByRangeNumber() {

    }

    public List<Integer> getListOfIntegers() {
        List<Integer> listOfInteger = new ArrayList<>();

        for (int i = this.initialRange; i <= this.finalRange; i++) {
            listOfInteger.add(i);
        }
        return listOfInteger;
    }

    public List<Integer> rangeOfPagesNotConsideredFlatten() {
        return listOfPageNumbersNotConsidered.stream()
                .flatMap(aListOfIntegers -> aListOfIntegers.generateListOfNumber().stream())
                .collect(Collectors.toList());
    }
    private boolean isAPageNotConsidered(Integer page) {
        return rangeOfPagesNotConsideredFlatten().contains(page);
    }

    ;

    @Override
    public List<Integer> generateListOfNumber() {
        return getListOfIntegers().stream().filter(aPage -> !isAPageNotConsidered(aPage)).distinct().collect(Collectors.toList());
    }

    @Override
    public void addlistOfPageNumbersNotConsidered(PageNumberReturner aPageNumberReturner) throws BusinessException {
        if (!theNewRangeOverlapsWithMySelf(aPageNumberReturner)) {
            throw new BusinessException("La pagina o lista de paginas no esta incluido en ningun rango");
        }

        listOfPageNumbersNotConsidered.add(aPageNumberReturner);
    }

    public void deleteAllNotConsideredNumbers() {
        listOfPageNumbersNotConsidered.clear();
    }


    public void setInitialRange(Integer initialRange) throws BusinessException {


        deleteAllNotConsideredNumbers();
        this.initialRange = initialRange;
    }

    private Integer rangeDiference(){

        return finalRange -  initialRange;

    }


    public void setFinalRange(Integer finalRange) throws BusinessException {

        deleteAllNotConsideredNumbers();
        this.finalRange = finalRange;
    }


}
