package model.chainOfValidation;


import model.exception.BusinessException;

public class FinalIsSmallThanInitial implements IValidator {

    public static FinalIsSmallThanInitial finalIsSmallThanInitial;

    private FinalIsSmallThanInitial() {
    };

    public static FinalIsSmallThanInitial getInstance() {

        if (finalIsSmallThanInitial == null) {
            finalIsSmallThanInitial = new FinalIsSmallThanInitial();
        }

        return finalIsSmallThanInitial;
    }

    @Override
    public void validate(Integer initial, Integer limit) throws BusinessException {
        if (theFinalIsSmallThanInitial(initial, limit)) {
            throw new BusinessException("El numero de pagina inicial debe ser menor al de la final");
        }
        ManyPagesException.getInstance().validate(initial,limit);
    }

    private Boolean theFinalIsSmallThanInitial(Integer initial, Integer limit) {
        return limit < initial;

    }
}
