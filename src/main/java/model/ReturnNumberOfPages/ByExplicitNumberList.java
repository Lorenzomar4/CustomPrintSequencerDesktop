package model.ReturnNumberOfPages;

import java.util.ArrayList;
import java.util.List;

public class ByExplicitNumberList implements PageNumberReturner {
    List<Integer> listOfPagesNumber = new ArrayList<>();

    @Override
    public List<Integer> generateListOfNumber() {
        return listOfPagesNumber;
    }

    public void addNumberOfPage(Integer pageNumber){
        listOfPagesNumber.add(pageNumber);
    }
}
