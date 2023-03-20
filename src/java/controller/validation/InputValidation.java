package controller.validation;

public enum InputValidation {
    default_value(-1);
        
    private int value;    
    private static final int MAX_NUMBER_LENGTH = 12;
    private static final int MIN_NUMBER_LENGTH = 10;

    private InputValidation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() > MAX_NUMBER_LENGTH || phoneNumber.length() < MIN_NUMBER_LENGTH) {
            return false;
        }
        boolean isValid = true;
        boolean isZeroFirst = Character.toString(phoneNumber.charAt(0)).equals("0");

        //Check if the First Character is zero or not
        isValid = isValid && isZeroFirst;
        for (int i = 1; i < phoneNumber.length(); i++) {
            isValid = isValid && Character.isDigit(phoneNumber.charAt(i));
        }

        return isValid;
    }
    
    public static int getNumber(String number) {
        boolean isNull = String.valueOf(number).trim().isEmpty();
        if(isNull) return default_value.value;
        
        boolean isValid = true;
        number = String.valueOf(number).trim();
        for (int i = 0; i < number.length(); i++) {
            isValid = isValid && Character.isDigit(number.charAt(i));
        }
        
        //If is valid, then return the 
        return isValid ? Integer.parseInt(number) : default_value.value;
    }
}
