import com.lorenzomar4.customprintsequencer.model.PageNumberListStrategies.ExplicitNumberListStrategy;
import com.lorenzomar4.customprintsequencer.model.PageNumberListStrategies.RangeNumberStrategy;
import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StrategieTest {

    ExplicitNumberListStrategy listOfPagesNumber;

    RangeNumberStrategy listOfRangePagesNumber;

    RangeNumberStrategy listOfRangePagesNumber2;
    RangeNumberStrategy listOfRangePagesNumber3;

    @BeforeEach
    void initialize() throws BusinessException {
        listOfPagesNumber = new ExplicitNumberListStrategy();
        listOfRangePagesNumber = new RangeNumberStrategy(1, 10);
        listOfRangePagesNumber2 = new RangeNumberStrategy(8, 10);
        listOfRangePagesNumber3 = new RangeNumberStrategy(30, 70);
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
    @DisplayName("Se tiene un rango de numeros (rango padre) pero de ese rango de numeros quiero omitir algunos .Esos numeros a  excluir pueden ser " +
            "rangos o una lista explicita de numeros (numeros sueltos en una lista)")
    void listOfPages3() throws BusinessException {


        listOfPagesNumber.addNumberOfPage(4);
        listOfPagesNumber.addNumberOfPage(2);

        listOfRangePagesNumber.addlistOfPageNumbersNotConsidered(listOfPagesNumber);
        listOfRangePagesNumber.addlistOfPageNumbersNotConsidered(listOfRangePagesNumber2);


        Assertions.assertEquals(new ArrayList<>(Arrays.asList(1, 3, 5, 6, 7)), listOfRangePagesNumber.generateListOfNumber());
    }

    @Test
    @DisplayName("Se tiene un rango de numeros (rango padre) ,pero se desea excluir  algunos numeros (lista explicita o rango de numeros)" +
            "esos numeros a omitir deben estar dentro del rango padre de lo contrario se lanza excepcion " +
            "(No se pueden omitir numeros que no esten dentro del rango padre")
    void listOfPagesWhitException() throws BusinessException {
        listOfPagesNumber.addNumberOfPage(20);

        assertThrows(BusinessException.class, () -> {
            listOfRangePagesNumber.addlistOfPageNumbersNotConsidered(listOfPagesNumber);
        });

        assertThrows(BusinessException.class, () -> {
            listOfRangePagesNumber.addlistOfPageNumbersNotConsidered(listOfRangePagesNumber3);
        });

    }

    @Test
    @DisplayName("Si modificamos los atributos de rango inicial o final se resetea la lista de paginas no consideradas/excluidas por lo tanto")
    void resetAfterEditRangeInitialOrFinale() throws BusinessException {

        listOfPagesNumber.addNumberOfPage(2);
        listOfPagesNumber.addNumberOfPage(4);

        listOfRangePagesNumber.addlistOfPageNumbersNotConsidered(listOfPagesNumber);
        listOfRangePagesNumber.addlistOfPageNumbersNotConsidered(listOfRangePagesNumber2);

        Assertions.assertEquals(new ArrayList<>(Arrays.asList(2, 4, 8, 9, 10)), listOfRangePagesNumber.rangeOfPagesNotConsideredFlatten());

        listOfRangePagesNumber.setInitialRange(5);
        listOfRangePagesNumber.setFinalRange(11);

        Assertions.assertEquals(new ArrayList<>(Arrays.asList( 5, 6, 7, 8, 9, 10,11)), listOfRangePagesNumber.generateListOfNumber());

    }

    @Test
    @DisplayName("Al crear un nuevo rango de paginas se lanza una excepcion por asignar un rango inicial mayor al rango final")
    void launchExceptionIfTheInitialRangeIsGreaterThanFinaleRange(){
        assertThrows(BusinessException.class, () -> {
            RangeNumberStrategy rangeOfNumberInvalid =  new RangeNumberStrategy(10,4);
        });
    }


    @Test
    @DisplayName("Al modificar los rangos se tiene que seguir cumpliendo la regla de que el rango inicial debe ser menor o igual al final")
    void launchExceptionIfAfterEditTheInitialRangeIsGreaterThanFinaleRange (){
        assertThrows(BusinessException.class, () -> {
            listOfRangePagesNumber.setInitialRange(30);
        });

        assertThrows(BusinessException.class, () -> {
            listOfRangePagesNumber.setFinalRange(0);
        });
    }

    @Test
    @DisplayName("Si el rango inicial es igual al final entonces se retorna una lista con un solo numero")
    void ifTheInitialRangeIsEqualThanFinaleThenReturnListWhitAnUniqueNumber() throws BusinessException {
        RangeNumberStrategy listOfRangeWhitUniqueNumberInTheList = new RangeNumberStrategy(4,4);

        Assertions.assertEquals(new ArrayList<>(List.of(4)) , listOfRangeWhitUniqueNumberInTheList.generateListOfNumber() );



    }


}
