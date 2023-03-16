package support_enum;

public enum FolderEnum {
	ADMIN("admin"),
	USER("user"),
	RENT_HOUSE("rent_house"),;
	
	private String folderName;
	private static final String seperator = "/";
	
	private FolderEnum(String folderName) {
		this.folderName = folderName;
	}
	
	public String getFolderName() {
		return folderName.concat(seperator);
	}
}
