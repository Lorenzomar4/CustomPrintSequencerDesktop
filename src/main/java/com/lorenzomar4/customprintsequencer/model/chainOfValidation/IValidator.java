package com.lorenzomar4.customprintsequencer.model.chainOfValidation;

import com.lorenzomar4.customprintsequencer.model.exception.BusinessException;

public interface IValidator {
     void validate(Integer initial ,Integer limit) throws BusinessException;


}
