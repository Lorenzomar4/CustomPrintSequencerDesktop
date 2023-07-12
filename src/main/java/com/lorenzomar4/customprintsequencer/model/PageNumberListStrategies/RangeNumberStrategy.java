package com.lorenzomar4.customprintsequencer.model.PageNumberListStrategies;

import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RangeNumberStrategy implements PageNumberList {

    Integer initialRange;
    Integer finalRange;

    List<PageNumberList> listOfPageNumbersNotConsidered = new ArrayList<>();


    public RangeNumberStrategy(Integer initialRange, Integer finalRange) throws BusinessException {

        if(initialRange>finalRange) {
            throw  new BusinessException("El rango inicial debe ser menor al final");

        }

        this.initialRange = initialRange;
        this.finalRange = finalRange;
    }

    public List<Integer> getListOfIntegers() {
        return IntStream.rangeClosed(initialRange, finalRange).boxed().collect(Collectors.toList());
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
        return getListOfIntegers().stream().filter(aPage -> !isAPageNotConsidered(aPage)).collect(Collectors.toSet()).stream().collect(Collectors.toList());
    }

    @Override
    public void addlistOfPageNumbersNotConsidered(PageNumberList aPageNumberList) throws BusinessException {
        if (!theNewRangeOverlapsWithMySelf(aPageNumberList)) {
            throw new BusinessException("La pagina o lista de paginas no esta incluido en ningun rango");
        }

        listOfPageNumbersNotConsidered.add(aPageNumberList);
    }

    public void deleteAllNotConsideredNumbers(){
        listOfPageNumbersNotConsidered.clear();
    }


    public void setInitialRange(Integer initialRange) throws BusinessException {
        if(initialRange>finalRange){
            throw new BusinessException("el rango incial debe ser menor al final");
        }

        deleteAllNotConsideredNumbers();
        this.initialRange = initialRange;
    }



    public void setFinalRange(Integer finalRange) throws BusinessException {

        if(initialRange>finalRange){
            throw new BusinessException("el rango final debe ser mayor al inicial");
        }

        deleteAllNotConsideredNumbers();
        this.finalRange = finalRange;
    }

    /*
       public boolean overlapingWithMyself(RangeOfPages aRangeOfPages) {
        return (aRangeOfPages.getInitialRange() >= getInitialRange() && aRangeOfPages.getInitialRange() <= getFinalRange()) ||
                (aRangeOfPages.getFinalRange() >= getInitialRange() && aRangeOfPages.getFinalRange() <= getFinalRange());
    }
    */

}
