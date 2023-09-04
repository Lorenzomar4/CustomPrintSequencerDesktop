package com.lorenzomar4.customprintsequencer.model;

import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPagesStrategies.ByExplicitNumberList;
import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPagesStrategies.PageNumberReturner;
import com.lorenzomar4.customprintsequencer.model.Sheet.ISheet;
import com.lorenzomar4.customprintsequencer.model.Sheet.SheetFactory;

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

    public List<ISheet> getPagesToPrint() {
        final List<Integer> listSortedAscending = returnAllNumberPagesSortedAscending();
        final SheetFactory sheetFactory = new SheetFactory(cantOfPages(), listSortedAscending);
        final List<ISheet> sheetList = new ArrayList<>();

        for (int i = 0; i <= cantOfPages() - 1; i = i + 2) {
            sheetList.add(sheetFactory.returnSheet(i));
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
