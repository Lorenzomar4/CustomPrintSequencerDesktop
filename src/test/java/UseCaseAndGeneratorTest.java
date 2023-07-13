import com.lorenzomar4.customprintsequencer.model.Generator;
import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPagesStrategies.ByCompositionOfStrategies;
import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPagesStrategies.ByExplicitNumberList;
import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPagesStrategies.ByRangeNumber;
import com.lorenzomar4.customprintsequencer.model.Sequencer;
import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UseCaseAndGeneratorTest {
    Generator generator;

    Sequencer sequencer;

    ByExplicitNumberList listOfPagesNumber;

    ByRangeNumber listOfRangePagesNumber;

    ByRangeNumber listOfRangePagesNumber2;

    ByCompositionOfStrategies compositionOfPageNumberReturner;

    @BeforeEach
    void inititalize() throws BusinessException {

        listOfPagesNumber = new ByExplicitNumberList();

        listOfRangePagesNumber = new ByRangeNumber(1, 10);

        listOfRangePagesNumber2 = new ByRangeNumber(30, 33);

        compositionOfPageNumberReturner = new ByCompositionOfStrategies();

        compositionOfPageNumberReturner.addNewPageNumberReturner(listOfRangePagesNumber);

        sequencer = new Sequencer();
        sequencer.setPageNumberReturner(compositionOfPageNumberReturner);

        generator = new Generator(sequencer);

    }
    @Test
    @DisplayName("Se genera la secuencia para imprimir unicamente las carillas o paginas frontales de todas las hojas. Recordar" +
            "que toda hoja tiene una carilla frontal y opuesta")
    void generateFrontPageSequence(){
        Assertions.assertEquals("1, 3, 5, 7, 9",generator.onlyFrontPagesOfSheetString());

    }
    @Test
    @DisplayName("Se genera la secuencia para imprimir unicamente las carillas o paginas opuestas de todas las hojas. Recordar" +
            "que toda hoja tiene una carilla frontal y opuesta")
    void generateOppositePageSequence(){
        Assertions.assertEquals("2, 4, 6, 8, 10",generator.onlyOppositePagesOfSheetString());
    }

    @Test
    @DisplayName("Caso de uso 1 : Se desea imprimir desde la pagina 1 a la 10 salvo  la pagina 5 ")
    void useCase1() throws BusinessException {

        listOfPagesNumber.addNumberOfPage(5);
        listOfRangePagesNumber.addlistOfPageNumbersNotConsidered(listOfPagesNumber);

        Assertions.assertEquals("1, 3, 6, 8, 10",generator.onlyFrontPagesOfSheetString());
        Assertions.assertEquals("2, 4, 7, 9",generator.onlyOppositePagesOfSheetString());

    }

    @Test
    @DisplayName("Caso de uso 2 : Se desea imprimir desde la pagina 1 a la 10 salvo  la pagina 5 y 7 ")
    void useCase2() throws BusinessException {

        listOfPagesNumber.addNumberOfPage(5);
        listOfPagesNumber.addNumberOfPage(7);
        listOfRangePagesNumber.addlistOfPageNumbersNotConsidered(listOfPagesNumber);

        Assertions.assertEquals("1, 3, 6, 9",generator.onlyFrontPagesOfSheetString());
        Assertions.assertEquals("2, 4, 8, 10",generator.onlyOppositePagesOfSheetString());

    }

    @Test
    @DisplayName("Caso de uso 3 : Se desea imprimir desde la pagina 1 a la 10 salvo  la pagina 3,4 y 5 ")
    void useCase3() throws BusinessException {

        listOfPagesNumber.addNumberOfPage(3);
        listOfPagesNumber.addNumberOfPage(4);
        listOfPagesNumber.addNumberOfPage(5);
        listOfRangePagesNumber.addlistOfPageNumbersNotConsidered(listOfPagesNumber);

        Assertions.assertEquals("1, 6, 8, 10",generator.onlyFrontPagesOfSheetString());
        Assertions.assertEquals("2, 7, 9",generator.onlyOppositePagesOfSheetString());

    }

    @Test
    @DisplayName("Caso de uso 4 : Identico al caso anterior pero con rangos ->" +
            " Se desea imprimir desde la pagina 1 a la 10 salvo  el rango entre 3 y 5 ")
    void useCase4() throws BusinessException {

        ByRangeNumber subRange = new ByRangeNumber(3,5);
        listOfRangePagesNumber.addlistOfPageNumbersNotConsidered(subRange);

        Assertions.assertEquals("1, 6, 8, 10",generator.onlyFrontPagesOfSheetString());
        Assertions.assertEquals("2, 7, 9",generator.onlyOppositePagesOfSheetString());

    }

    @Test
    @DisplayName("Caso de uso 5 :  Se desea imprimir desde la pagina 1 a la 10 salvo  los rangos [1,4] y [9-10] , tampoco" +
            "se desea imprimir la pagina 6 ")
    void useCase5() throws BusinessException {

        ByRangeNumber subRange = new ByRangeNumber(1,4);
        listOfPagesNumber.addNumberOfPage(6);
        ByRangeNumber subRange2 = new ByRangeNumber(9,10);
        listOfRangePagesNumber.addlistOfPageNumbersNotConsidered(subRange);
        listOfRangePagesNumber.addlistOfPageNumbersNotConsidered(listOfPagesNumber);
        listOfRangePagesNumber.addlistOfPageNumbersNotConsidered(subRange2);


        Assertions.assertEquals("5, 8",generator.onlyFrontPagesOfSheetString());
        Assertions.assertEquals("7",generator.onlyOppositePagesOfSheetString());

    }

    @Test
    @DisplayName("Caso de uso 6 :  El rango anterior del caso 5 con sus exclusiones + 1 nuevo rango adicional")
    void useCase6() throws BusinessException {

        ByRangeNumber subRange = new ByRangeNumber(1,4);
        listOfPagesNumber.addNumberOfPage(6);
        ByRangeNumber subRange2 = new ByRangeNumber(9,10);
        listOfRangePagesNumber.addlistOfPageNumbersNotConsidered(subRange);
        listOfRangePagesNumber.addlistOfPageNumbersNotConsidered(listOfPagesNumber);
        listOfRangePagesNumber.addlistOfPageNumbersNotConsidered(subRange2);

        compositionOfPageNumberReturner.addNewPageNumberReturner(listOfRangePagesNumber2);


        Assertions.assertEquals("5, 8, 31, 33",generator.onlyFrontPagesOfSheetString());
        Assertions.assertEquals("7, 30, 32",generator.onlyOppositePagesOfSheetString());

    }
}
