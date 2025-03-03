package model.chainOfValidation;


import model.exception.BusinessException;

public class BigRangeException implements  IValidator{

    public static BigRangeException bigRangeException;

    private BigRangeException(){}

    public static BigRangeException getInstance(){
        if(bigRangeException==null){
            bigRangeException = new BigRangeException();
        }
        return bigRangeException;
    }


    @Override
    public void validate(Integer initial, Integer limit) throws BusinessException {

        if(rangeDifference(initial,limit)){
            throw new BusinessException("Solo se pueden generar hasta 1500 paginas. Aun asi se recomienda realizar intervalos desde hasta 50 paginas");
        }

    }


    private Boolean rangeDifference(Integer initial, Integer limit){
        return limit-initial>1500;

    }
}
