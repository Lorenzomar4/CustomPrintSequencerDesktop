package com.lorenzomar4.customprintsequencer.model.chainOfValidation;

import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;

public class ManyPagesException implements IValidator{

    public static ManyPagesException manyPagesException;

    private ManyPagesException(){}

    public static  ManyPagesException getInstance(){
        if(manyPagesException==null){
            manyPagesException= new ManyPagesException();
        }
        return manyPagesException;
    }

    @Override
    public void validate(Integer initial, Integer limit) throws BusinessException {
        if(thisNumberInitialsIsBigger(initial)){
            throw  new BusinessException("¡No se admiten numeros mayores a 3000 para la pagina inicial!");
        }

        if(thisNumberFinalsIsBigger(limit)){
            throw  new BusinessException("¡No se admiten numeros mayores a 4500 para la pagina final!");
        }
        BigRangeException.getInstance().validate(initial,limit);
    }

    private Boolean thisNumberInitialsIsBigger(Integer initial){
        return initial>3000;
    }

    private Boolean thisNumberFinalsIsBigger(Integer limit){
        return limit>4500;
    }
}
