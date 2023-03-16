package support_enum;

public enum AttributeEnum {
	ROOT,
	REGISTER_USER,
	STEP1,
	STEP2,
	USER,
	ADMIN,
	COMMENT,
	RENT_HOUSE,
	RENT_HOUSE_PROVIDER,
	NOT_FOUND;
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public static AttributeEnum getEnum(String enumString) {
		if (enumString == null) {
			return NOT_FOUND;
		}
	
		boolean isFound = false;
		AttributeEnum resultEnum = NOT_FOUND;
		AttributeEnum[] enums = AttributeEnum.values();
		
		for(int i = 0; i < enums.length && !isFound; i++) {
			if(enums[i].toString().equalsIgnoreCase(enumString)) resultEnum = enums[i];
		}
		
		return resultEnum;
	}
}
