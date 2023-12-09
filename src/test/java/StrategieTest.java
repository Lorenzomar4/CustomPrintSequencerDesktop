import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPages.ByExplicitNumberList;
import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPages.ByRangeNumber;
import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StrategieTest {

    ByExplicitNumberList listOfPagesNumber;

    ByRangeNumber listOfRangePagesNumber;

    ByRangeNumber listOfRangePagesNumber2;
    ByRangeNumber listOfRangePagesNumber3;

    @BeforeEach
    void initialize() throws BusinessException {
        listOfPagesNumber = new ByExplicitNumberList();
        listOfRangePagesNumber = new ByRangeNumber(1, 10);
        listOfRangePagesNumber2 = new ByRangeNumber(8, 10);
        listOfRangePagesNumber3 = new ByRangeNumber(30, 70);
    }
    @Test
    @DisplayName("Se agrega secuencialmente numeros a la lista del objeto listOfPagesNumber y luego se retorna la lista completa de numeros ingresados")
    void listOfPages() {
        listOfPagesNumber.addNumberOfPage(1);
        listOfPagesNumber.addNumberOfPage(3);
        listOfPagesNumber.addNumberOfPage(7);
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(1, 3, 7)), listOfPagesNumber.generateListOfNumber());

    }

    @Test
    @DisplayName("Testing para el test del strategy RangeNumberStrategy")
    void listOfPages2() {
        Assertions.assertEquals(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)), listOfRangePagesNumber.generateListOfNumber());

    }



    @Test
    @DisplayName("Si el rango inicial es igual al final entonces se retorna una lista con un solo numero")
    void ifTheInitialRangeIsEqualThanFinaleThenReturnListWhitAnUniqueNumber() throws BusinessException {
        ByRangeNumber listOfRangeWhitUniqueNumberInTheList = new ByRangeNumber(4,4);

        Assertions.assertEquals(new ArrayList<>(List.of(4)) , listOfRangeWhitUniqueNumberInTheList.generateListOfNumber() );



    }


}
