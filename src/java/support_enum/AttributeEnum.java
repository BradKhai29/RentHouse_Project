package support_enum;

public enum AttributeEnum {
    root,
    checkpoint,
    currentPageNum,
    upperPageNum,
    lowerPageNum,
    REGISTER_USER,
    STEP1,
    STEP2,
    USER,
    ADMIN,
    COMMENT,
    houseID,
    rentHouse,
    rentHouseProvider,
    NOT_FOUND,
    districtMap,
    selectedDistrictMap,
    streetMap,
    selectedStreetMap,
    rentHouseMap,
    selectedRentHouseList,
    UPDATE_PROFILE_CHECKPOINT,
    UPDATE_PASSWORD_CHECKPOINT;

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

        for (int i = 0; i < enums.length && !isFound; i++) {
            if (enums[i].toString().equalsIgnoreCase(enumString)) {
                resultEnum = enums[i];
            }
        }

        return resultEnum;
    }
}
