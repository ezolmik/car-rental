package com.msci.carrental.external.validator;

public interface Validator<T> {

    void validate(T t) throws Exception;

}
