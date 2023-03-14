package ru.netology.springboot_courseproject_moneytransferservice.exception;

public class DataMismatch extends RuntimeException {

    public DataMismatch(String msg) {
        super(msg);
    }
}
