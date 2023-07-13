package com.lorenzomar4.customprintsequencer.model;

import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPagesStrategies.ByExplicitNumberList;
import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPagesStrategies.PageNumberReturner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sequencer {

    PageNumberReturner pageNumberReturner = new ByExplicitNumberList();

    public List<Integer> returnAllNumberPagesSortedAscending() {
        List<Integer> pagesList = pageNumberReturner.generateListOfNumber();
        Collections.sort(pagesList);
        return pagesList;
    }

    public Integer cantOfPages() {
        return pageNumberReturner.generateListOfNumber().size();

    }

    public List<Sheet> getPagesToPrint() {
        final List<Integer> listSortedAscending = returnAllNumberPagesSortedAscending();
        final List<Sheet> sheetList = new ArrayList<>();

        for (int i = 0; i <= cantOfPages() - 1; i = i + 2) {
            Integer numberPageFront = listSortedAscending.get(i);

            if (i + 1 < cantOfPages()) {
                Integer numberPageOpposite = listSortedAscending.get(i + 1);
                sheetList.add(Sheet.SheetWithFrontAnOpposite(new PageOfSheet(numberPageFront), new PageOfSheet(numberPageOpposite)));

            } else {
                sheetList.add(Sheet.SheetOnlyWithFront(new PageOfSheet(numberPageFront)));
            }
        }
        return sheetList;
    }

    public PageNumberReturner getPageNumberReturner() {
        return pageNumberReturner;
    }

    public void setPageNumberReturner(PageNumberReturner pageNumberReturner) {
        this.pageNumberReturner = pageNumberReturner;
    }
}
