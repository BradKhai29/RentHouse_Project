package support_enum;

public enum ActionEnum {
    LOAD_DATA,
    LOGOUT,
    LOGIN,
    REGISTER,
    CANCEL,
    CREATE,
    COMMENT,
    CONFIRM_CREATE,
    UPDATE,
    EDIT,
    CONFIRM_UPDATE,
    DELETE,;

    public static ActionEnum get(String inputAction) {
        if (inputAction == null) {
            return LOAD_DATA;
        }

        ActionEnum resultCommand = LOAD_DATA;
        ActionEnum[] commandEnums = ActionEnum.values();

        boolean isFound = false;
        for (int i = 0; i < commandEnums.length && !isFound; i++) {
            if (commandEnums[i].name().equalsIgnoreCase(inputAction)) {
                resultCommand = commandEnums[i];
            }
        }

        return resultCommand;
    }
}
