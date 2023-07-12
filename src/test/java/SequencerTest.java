import com.lorenzomar4.customprintsequencer.model.PageNumberListStrategies.ExplicitNumberListStrategy;
import com.lorenzomar4.customprintsequencer.model.PageNumberListStrategies.RangeNumberStrategy;
import com.lorenzomar4.customprintsequencer.model.Sequencer;
import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SequencerTest {

    ExplicitNumberListStrategy listOfPagesNumber;

    RangeNumberStrategy listOfRangePagesNumber;

    RangeNumberStrategy listOfRangePagesNumber2;
    RangeNumberStrategy listOfRangePagesNumber3;

    Sequencer aSequencer;

    @BeforeEach
    void initialize() throws BusinessException {
        listOfPagesNumber = new ExplicitNumberListStrategy();
        listOfPagesNumber.addNumberOfPage(2);
        listOfPagesNumber.addNumberOfPage(4);

        listOfRangePagesNumber = new RangeNumberStrategy(1, 10);
        listOfRangePagesNumber2 = new RangeNumberStrategy(8, 10);
        listOfRangePagesNumber3 = new RangeNumberStrategy(5, 7);

        aSequencer = new Sequencer();


    }

    @Test
    @DisplayName("Retornar la lista de numero de paginas y de paso tambien se testea la cantidad de elementos que tiene esa lista")
    void listOfNumberPagesAndTherQuantity () throws BusinessException {
        aSequencer.addListOfRangeOfPages(listOfRangePagesNumber2);
        aSequencer.addListOfRangeOfPages(listOfPagesNumber);
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(8, 9, 10, 2, 4)), aSequencer.returnAlLNumberPages());
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(2, 4, 8, 9, 10)), aSequencer.returnAllNumberPagesSortedAscending());
        Assertions.assertEquals(5,aSequencer.cantOfPages());
    }

    @Test
    @DisplayName("Si se agrega nuevo rango o una lista de numeros que incluye numeros ya existente en el Sequencer se lanza una excepcion")
    void launchExceptionIfTheNewRangeorListofnumberContainsANumberThatAlreadyExistsInTheSequencer()  throws BusinessException {
        aSequencer.addListOfRangeOfPages(listOfRangePagesNumber);

        assertThrows(RuntimeException.class, () -> {
            aSequencer.addListOfRangeOfPages(listOfPagesNumber);
        });

        assertThrows(RuntimeException.class, () -> {
            aSequencer.addListOfRangeOfPages(listOfRangePagesNumber2);
        });

    }

}
