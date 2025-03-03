import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import model.Generator
import model.ReturnNumberOfPages.ByRangeNumber
import model.ReturnNumberOfPages.PageNumberReturner
import model.Sequencer
import model.chainOfValidation.TheInitialValidator
import model.exception.BusinessException

class CustomPrintSequencerViewModel : ViewModel() {

    private val sequencer: Sequencer = Sequencer()
    private val generator: Generator = Generator(sequencer)
    private var pageNumberReturnerType: ByRangeNumber = ByRangeNumber()

    private val _initialRange = MutableStateFlow("")
    val initialRange: StateFlow<String> = _initialRange

    private val _finalRange = MutableStateFlow("")
    val finalRange: StateFlow<String> = _finalRange

    private val _frontPagesRangeString = MutableStateFlow("")
    val frontPagesRange: StateFlow<String> = _frontPagesRangeString

    private val _oppositePageRangeString = MutableStateFlow("")
    val oppositePageRange: StateFlow<String> = _oppositePageRangeString

    private val _necesarySheet = MutableStateFlow("")
    val necesarySheet: StateFlow<String> = _necesarySheet

    private val _totalSheet = MutableStateFlow("")
    val totalSheet: StateFlow<String> = _totalSheet

    private val _pageNumberFront = MutableStateFlow("")
    val pageNumberFront: StateFlow<String> = _pageNumberFront

    private val _pageNumberBack = MutableStateFlow("")
    val pageNumberBack: StateFlow<String> = _pageNumberBack


    init {
        sequencer.setPageNumberReturner(pageNumberReturnerType)
    }

    fun updateInitialRange(newRange: String) {
        _initialRange.value = newRange
    }

    fun updateFinalRange(newRange: String) {
        _finalRange.value = newRange
    }

    fun setFrontalPagesString() {
        val valor = generator.onlyFrontPagesOfSheetString()
        _frontPagesRangeString.value = valor
    }

    fun setOppositePagesString() {
        val valor = generator.onlyOppositePagesOfSheetString()
        _oppositePageRangeString.value = valor

    }

    fun confirm() {


        try {
            TheInitialValidator.getInstanceValidator().validate(_initialRange.value, _finalRange.value)
            pageNumberReturnerType.setInitialRange(_initialRange.value.toInt())
            pageNumberReturnerType.setFinalRange(_finalRange.value.toInt())
            setFrontalPagesString()
            setOppositePagesString()
            pageInfoSets()
        } catch (e: BusinessException) {
            cancel()
        }

    }

    fun pageInfoSets() {
        _necesarySheet.value = generator.numberOfFrontPages().toString()
        _totalSheet.value = generator.numberOfPagesTotal().toString()
        _pageNumberBack.value = generator.numberOfOppositePages().toString()
        _pageNumberFront.value = generator.numberOfFrontPages().toString()
    }

    fun cancel() {
        _initialRange.value = ""
        _finalRange.value = ""
        _frontPagesRangeString.value = ""
        _oppositePageRangeString.value = ""
    }
}
