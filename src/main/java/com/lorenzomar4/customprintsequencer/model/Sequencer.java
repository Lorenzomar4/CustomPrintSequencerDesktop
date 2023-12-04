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
        List<Integer> listSortedAscending = returnAllNumberPagesSortedAscending();
        SheetFactory sheetFactory = new SheetFactory(cantOfPages(), listSortedAscending);
        List<ISheet> sheetList = new ArrayList<>();

        int totalPages = cantOfPages();
        for (int i = 0; i < totalPages; i += 2) {
            sheetList.add(sheetFactory.returnSheet(i));
        }

        listSortedAscending = null;

        return sheetList;
    }

    public PageNumberReturner getPageNumberReturner() {
        return pageNumberReturner;
    }

    public void setPageNumberReturner(PageNumberReturner pageNumberReturner) {
        this.pageNumberReturner = pageNumberReturner;
    }
}
