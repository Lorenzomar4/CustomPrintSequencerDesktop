package com.lorenzomar4.customprintsequencer.model;

import java.util.List;
import java.util.stream.Collectors;

public class Generator {

    public Sequencer sequencer;

    public Generator(Sequencer aSequencer) {
        sequencer = aSequencer;
    }


    public List<PageOfSheet> onlyFrontPagesOfSheet() {
        return sequencer.getPagesToPrint().stream().map(Sheet::getFront).collect(Collectors.toList());
    }

    public List<PageOfSheet> onlyOppositePagesOfSheet() {
        int cantOfPages = sequencer.cantOfPages();
        boolean isPair = cantOfPages%2==0;
        List<PageOfSheet> listOfPages = sequencer.getPagesToPrint().stream().map(Sheet::getOpposite).toList();

        return (isPair) ? listOfPages :listOfPages.subList(0,listOfPages.size()-1);
    }

    public String onlyFrontPagesOfSheetString() {
        final String stringToReturn = onlyFrontPagesOfSheet().stream().map(PageOfSheet::getNumberOfPage).toList().toString();
        return correctString(stringToReturn);
    }


    public String onlyOppositePagesOfSheetString() {
        final String stringToReturn = onlyOppositePagesOfSheet().stream().map(PageOfSheet::getNumberOfPage).toList().toString();
        return correctString(stringToReturn);
    }

    public String correctString(String aString) {
        return aString.substring(1, aString.length() - 1);

    }

}
