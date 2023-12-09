import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPages.ByCompositionOfStrategies;
import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPages.ByExplicitNumberList;
import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPages.ByRangeNumber;
import com.lorenzomar4.customprintsequencer.model.Sequencer;
import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompositionOfStrategiesTest {

    ByExplicitNumberList listOfPagesNumber;

    ByRangeNumber listOfRangePagesNumber;

    ByRangeNumber listOfRangePagesNumber2;
    ByRangeNumber listOfRangePagesNumber3;

    ByCompositionOfStrategies listOfPageNumberReturner;

    Sequencer aSequencer;

    @BeforeEach
    void initialize() throws BusinessException {
        listOfPagesNumber = new ByExplicitNumberList();
        listOfPagesNumber.addNumberOfPage(2);
        listOfPagesNumber.addNumberOfPage(4);

        listOfRangePagesNumber = new ByRangeNumber(1, 10);
        listOfRangePagesNumber2 = new ByRangeNumber(8, 10);
        listOfRangePagesNumber3 = new ByRangeNumber(5, 7);
        listOfPageNumberReturner = new ByCompositionOfStrategies();


        aSequencer = new Sequencer();
        aSequencer.setPageNumberReturner(listOfPageNumberReturner);


    }

    @Test
    @DisplayName("Retornar la lista de numero de paginas y de paso tambien se testea la cantidad de elementos que tiene esa lista")
    void listOfNumberPagesAndTherQuantity () throws BusinessException {
        listOfPageNumberReturner.addNewPageNumberReturner(listOfRangePagesNumber2);
        listOfPageNumberReturner.addNewPageNumberReturner(listOfPagesNumber);
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(8, 9, 10, 2, 4)), aSequencer.getPageNumberReturner().generateListOfNumber());
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(2, 4, 8, 9, 10)), aSequencer.returnAllNumberPagesSortedAscending());
        Assertions.assertEquals(5,aSequencer.cantOfPages());
    }



}
