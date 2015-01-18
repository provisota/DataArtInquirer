package com.dataart.inquirer.shared.utils;

public class RegExpPatterns {
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+" +
            "(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    //может содержать только цифры и буквы лат. алфавита (6 - 15 символов)
    public static final String LOGIN_PATTERN = "([a-zA-Z0-9]{3,12})$";
    //должен содержать и цифры и буквы лат. алфавита (6 - 15 символов)
    public static final String PASSWORD_PATTERN = "^(?=.*?[A-Za-z])(?=.*?[0-9]).{6,15}$";

}
