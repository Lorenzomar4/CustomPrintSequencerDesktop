package com.lorenzomar4.customprintsequencer.model;


import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/*
public class RangeOfPages {
    Integer initialRange;
    Integer finalRange;

    InputRangePagesNotConsidered inputRangePagesNotConsidered = new InputRangePagesNotConsidered();

    List<RangeOfPages> rangeOfPagesNotConsidered = new ArrayList<>();


    public boolean overlapingWithMyself(RangeOfPages aRangeOfPages) {
        return (aRangeOfPages.getInitialRange() >= getInitialRange() && aRangeOfPages.getInitialRange() <= getFinalRange()) ||
                (aRangeOfPages.getFinalRange() >= getInitialRange() && aRangeOfPages.getFinalRange() <= getFinalRange());
    }


    public void addNewRangeOfPagesNotConsidered(RangeOfPages rangeOfPages) throws BusinessException {
        if(overlapingWithMyself(rangeOfPages)){
            throw new BusinessException("Paginas fuera de rango");
        }

        rangeOfPagesNotConsidered.add(rangeOfPages);
    }

    public List<RangeOfPages> getRangeOfPagesNotConsidered() {
        return rangeOfPagesNotConsidered;
    }



    public List<Integer> getListOfIntegers() {
        return IntStream.rangeClosed(initialRange,finalRange).boxed().collect(Collectors.toList());
    }

    private List<Integer>  rangeOfPagesNotConsideredFlatten() {
        return rangeOfPagesNotConsidered.stream()
                .flatMap(aRangeOfPages -> aRangeOfPages.getListOfIntegers().stream())
                .collect(Collectors.toList());

    }

    private boolean isAPageNotConsidered( Integer page) {
        return rangeOfPagesNotConsideredFlatten().contains(page);
    };


    public  List<Integer> getListOfIntegersFiltered() {
        return getListOfIntegers().stream().filter(aPage -> isAPageNotConsidered(aPage) ).collect(Collectors.toList());
    }


    public Integer getInitialRange() {
        return initialRange;
    }

    public void setInitialRange(Integer initialRange) {
        this.initialRange = initialRange;
    }

    public Integer getFinalRange() {
        return finalRange;
    }

    public void setFinalRange(Integer finalRange) {
        this.finalRange = finalRange;
    }
}
*/