package com.lorenzomar4.customprintsequencer.model;

import com.lorenzomar4.customprintsequencer.model.Sheet.ISheet;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Generator {

    public Sequencer sequencer;

    public Generator(Sequencer aSequencer) {
        sequencer = aSequencer;
    }


    public int[] onlyFrontPagesOfSheet() {
        return sequencer.getPagesToPrint().getFront();
    }

    public  int[] onlyOppositePagesOfSheet() {

        return sequencer.getPagesToPrint().getOpposite();
    }

    public String onlyFrontPagesOfSheetString() {
        final String stringToReturn = Arrays.toString(onlyFrontPagesOfSheet());
        return correctString(stringToReturn);
    }


    public String onlyOppositePagesOfSheetString() {
        final String stringToReturn = Arrays.toString(onlyOppositePagesOfSheet());
        return correctString(stringToReturn);
    }

    public String correctString(String aString) {
        return aString.substring(1, aString.length() - 1);
    }

    public Integer numberOfPagesTotal() {
        return sequencer.cantOfPages();
    }

    public Integer numberOfFrontPages(){
        int resultado = numberOfPagesTotal() / 2;

        if ( numberOfPagesTotal() % 2 != 0) {
            resultado++;
        }

        return resultado;
    }

    public Integer numberOfOppositePages() {
        return  numberOfPagesTotal()/2;

    }



}
