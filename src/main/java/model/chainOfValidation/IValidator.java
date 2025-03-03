package model.chainOfValidation;


import model.exception.BusinessException;

public interface IValidator {
     void validate(Integer initial ,Integer limit) throws BusinessException;


}
