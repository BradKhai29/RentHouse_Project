package support_enum;

public enum FolderEnum {
    ADMIN("admin"),
    USER("user"),
    RENT_HOUSE("rent_house"),
    RENT_HOUSE_IMAGES("rent_house_images");

    private String folderName;
    private static final String seperator = "/";

    private FolderEnum(String folderName) {
        this.folderName = folderName;
    }

    /**
     * Getting the Folder name belong to this FolderEnum
     * @return The return URL will be like this: folder_name/
     */
    public String getFolderName() {
        return folderName.concat(seperator);
    }
}
