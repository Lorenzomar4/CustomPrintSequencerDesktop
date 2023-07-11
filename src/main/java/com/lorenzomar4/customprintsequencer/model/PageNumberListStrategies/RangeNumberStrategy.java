package com.lorenzomar4.customprintsequencer.model.PageNumberListStrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RangeNumberStrategy implements  PageNumberList{

    Integer initialRange;
    Integer finalRange;

    List<PageNumberList> listOfPageNumbersNotConsidered =  new ArrayList<>();

    public List<Integer> getListOfIntegers() {
        return IntStream.rangeClosed(initialRange,finalRange).boxed().collect(Collectors.toList());
    }

    private List<Integer>  rangeOfPagesNotConsideredFlatten() {
        return listOfPageNumbersNotConsidered.stream()
                .flatMap(aListOfIntegers -> aListOfIntegers.generateListOfNumber().stream())
                .collect(Collectors.toList());
    }

    private boolean isAPageNotConsidered( Integer page) {
        return rangeOfPagesNotConsideredFlatten().contains(page);
    };

    @Override
    public List<Integer> generateListOfNumber() {
          return getListOfIntegers().stream().filter(aPage -> isAPageNotConsidered(aPage) ).collect(Collectors.toList());
    }
}
