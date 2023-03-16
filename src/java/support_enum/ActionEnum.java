package support_enum;

public enum ActionEnum {
	LOAD_DATA,
	LOGOUT,
	LOGIN,
	REGISTER,
	DELETE,
	UPDATE,
	CREATE,
	;
	
	public static ActionEnum get(String inputCommand) {
		if(inputCommand == null) return LOAD_DATA;
		
		ActionEnum resultCommand = LOAD_DATA;
		ActionEnum[] commandEnums = ActionEnum.values();
		
		boolean isFound = false;
		for(int i = 0; i < commandEnums.length && !isFound; i++) 
		{
			if(commandEnums[i].name().equalsIgnoreCase(inputCommand))
			{
				resultCommand = commandEnums[i];
			}
		}
		
		return resultCommand;
	}
}
