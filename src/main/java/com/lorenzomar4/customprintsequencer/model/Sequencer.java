package com.lorenzomar4.customprintsequencer.model;

import com.lorenzomar4.customprintsequencer.model.PageNumberListStrategies.PageNumberList;
import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Sequencer {

    List<PageNumberList> listOfPagesNumbers;

    public void addListOfRangeOfPages(PageNumberList aPageNumberList) throws BusinessException {
        incomeValidation(aPageNumberList);
        listOfPagesNumbers.add(aPageNumberList);
    }

    private void incomeValidation(PageNumberList aPageNumberList) {
        listOfPagesNumbers.forEach(aPageNumberListOption -> {
            try {
                aPageNumberListOption.ifThereAreOverlapsThenExceptionIsThrown(aPageNumberList);
            } catch (BusinessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<PageNumberList> getListOfPagesNumbers() {
        return this.listOfPagesNumbers;
    }


    public List<Integer> returnAlLNumberPages() {
        return listOfPagesNumbers.stream().flatMap(aRangeOfPages -> aRangeOfPages.generateListOfNumber().stream()).collect(Collectors.toList());
    }

    public List<Integer> returnAllNumberPagesSortedascending() {
        List<Integer> pagesList = returnAlLNumberPages();
        Collections.sort(pagesList);
        return pagesList;
    }

    public Integer cantOfPages() {
        return returnAlLNumberPages().stream().mapToInt(numberOfPage -> numberOfPage).sum();

    }

    public List<Sheet> getPagesToPrint() {
        final List<Integer> listSortedAscending = returnAllNumberPagesSortedascending();
        final List<Sheet> sheetList = new ArrayList<>();


        for (int i = 0; i < cantOfPages() - 1; i += 2) {
            Integer numberPageFront = listSortedAscending.get(i);
            Integer numberPageOpposite = listSortedAscending.get(i + 1);

            if (i + 1 < cantOfPages()) {
                sheetList.add(Sheet.SheetWithFrontAnOpposite(new PageNumber(numberPageFront), new PageNumber(numberPageOpposite)));

            } else {
                sheetList.add(Sheet.SheetOnlyWithFront(new PageNumber(numberPageFront)));
            }

        }
        return sheetList;
    }


}
