package model.chainOfValidation;


import model.exception.BusinessException;

public class NegativeNumber implements IValidator {

    public static NegativeNumber negativeNumber;

    private NegativeNumber() {
    }


    public static NegativeNumber getInstance() {
        if (negativeNumber == null) {
            negativeNumber = new NegativeNumber();
        }

        return negativeNumber;
    }


    @Override
    public void validate(Integer initial, Integer limit) throws BusinessException {

        if (!thisNumbersArePositive(initial, limit)) {
            throw new BusinessException("¡Asegurese de haber ingresado numeros positivos en todos los campos correspondientes!");
        }

        FinalIsSmallThanInitial.getInstance().validate(initial, limit);
    }

    private Boolean thisNumbersArePositive(Integer initial, Integer limit) {
        return initial > 0 && limit > 0;
    }
}
