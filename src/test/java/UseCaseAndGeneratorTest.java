import com.lorenzomar4.customprintsequencer.model.Generator;
import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPages.ByCompositionOfStrategies;
import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPages.ByExplicitNumberList;
import com.lorenzomar4.customprintsequencer.model.ReturnNumberOfPages.ByRangeNumber;
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


}
