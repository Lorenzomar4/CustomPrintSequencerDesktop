package model.chainOfValidation;


import model.exception.BusinessException;

public class TheInitialValidator {

    public static TheInitialValidator validator;


    private TheInitialValidator() {
    }

    public static TheInitialValidator getInstanceValidator() {

        if (validator == null) {
            validator = new TheInitialValidator();
        }

        return validator;
    }

    public void validate(String initiaStringl, String limitString) throws BusinessException {
        int initial;
        int limit;

        theParametersIsNotEmpty(initiaStringl, limitString);
        allparametersAreNumbers(initiaStringl, limitString);

        initial = Integer.parseInt(initiaStringl);
        limit = Integer.parseInt(limitString);

        NegativeNumber.getInstance().validate(initial, limit);

    }

    private void allparametersAreNumbers(String initiaStringl, String limitString) throws BusinessException {
        if (!initiaStringl.matches("\\d+") && !limitString.matches("\\d+")) {
            throw new BusinessException("¡Debe ingresar numeros unicamentes!");
        }

    }

    private void theParametersIsNotEmpty(String initiaStringl, String limitString) throws BusinessException {
        if (initiaStringl == null  || limitString==  null || limitString.isBlank() || initiaStringl.isBlank()) {

            System.out.println("ingresado"+initiaStringl+" "+limitString);
            throw new BusinessException("¡Los campos no pueden ser vacios!. Asegurese de ingresar los numeros de pagina en cada campo");
        }

    }


}
