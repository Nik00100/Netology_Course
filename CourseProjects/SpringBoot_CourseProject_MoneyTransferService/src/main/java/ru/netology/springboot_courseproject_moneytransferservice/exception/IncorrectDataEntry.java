package ru.netology.springboot_courseproject_moneytransferservice.exception;

public class IncorrectDataEntry extends RuntimeException {

    public IncorrectDataEntry(String msg) {
        super(msg);
    }
}
