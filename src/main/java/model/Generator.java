package model;

import model.Sheet.ISheet;
import java.util.List;
import java.util.stream.Collectors;

public class Generator {

    public Sequencer sequencer;

    public Generator(Sequencer aSequencer) {
        sequencer = aSequencer;
    }


    public List<PageOfSheet> onlyFrontPagesOfSheet() {
        return sequencer.getPagesToPrint().stream().map(ISheet::getFrontSide).collect(Collectors.toList());
    }

    public List<PageOfSheet> onlyOppositePagesOfSheet() {
        int cantOfPages = sequencer.cantOfPages();
        boolean isPair = cantOfPages%2==0;
        List<PageOfSheet> listOfPages = sequencer.getPagesToPrint().stream().map(ISheet::getBackSide).toList();
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
