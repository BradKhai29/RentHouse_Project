package controller.validation;

public class InputValidation {
	private static final int MAX_NUMBER_LENGTH = 12;
    private static final int MIN_NUMBER_LENGTH = 10;
    
    public static boolean checkPhoneNumber(String phoneNumber) {
        if(phoneNumber.length() > MAX_NUMBER_LENGTH || phoneNumber.length() < MIN_NUMBER_LENGTH) return false;
        boolean isValid = true;
        
        boolean isZeroFirst = Character.toString(phoneNumber.charAt(0)).equals("0");
        
        //Check if the First Character is zero or not
        isValid = isValid && isZeroFirst;
        for(int i = 1; i < phoneNumber.length(); i++)
        {
            isValid = isValid && Character.isDigit(phoneNumber.charAt(i));
        }
        
        return isValid;
    }
}
