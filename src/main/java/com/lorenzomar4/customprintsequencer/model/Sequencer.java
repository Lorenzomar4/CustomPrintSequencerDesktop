package com.lorenzomar4.customprintsequencer.model;

import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPages.ByRangeNumber;
import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPages.PageNumberReturner;
import com.lorenzomar4.customprintsequencer.model.Sheet.Book;
import com.lorenzomar4.customprintsequencer.model.Sheet.ISheet;
import com.lorenzomar4.customprintsequencer.model.Sheet.SheetFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sequencer {

    PageNumberReturner pageNumberReturner = new ByRangeNumber();


    public Integer cantOfPages() {
        return pageNumberReturner.generateListOfNumber().length;

    }

    public Book getPagesToPrint() {
        int[] numberList = pageNumberReturner.generateListOfNumber();
        Book book = Book.getInstance();

        int numberOfOppositePages = cantOfPages() / 2;
        int numberOfFrontalPages = numberOfOppositePages + 1;

        int[] front = new int[numberOfFrontalPages];
        int[] opposite = new int[numberOfOppositePages];
        int index = 0;

        int totalPages = cantOfPages();
        for (int i = 0; i < totalPages; i += 2) {

            if (index + 1 < cantOfPages()) {
                opposite[index] = numberList[i + 1];
            }
            front[index] = numberList[i];

            index++;

        }

        numberList = null;

        book.setFront(front);
        book.setOpposite(opposite);


        return book;
    }

    public PageNumberReturner getPageNumberReturner() {
        return pageNumberReturner;
    }

    public void setPageNumberReturner(PageNumberReturner pageNumberReturner) {
        this.pageNumberReturner = pageNumberReturner;
    }
}
