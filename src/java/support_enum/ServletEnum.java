package support_enum;

public enum ServletEnum {
    HOME("home"),
    RENT_HOUSE("user/rent_house"),
    RENT_HOUSE_DETAIL("rent_house/detail"),
    RENT_HOUSE_SEARCH("rent_house/search"),
    USER("user"),
    USER_UPDATE("user/update"),
    USER_COMMENT("user/comment");

    private String URL;
    private static final String URLSeperator = "/";

    private ServletEnum(String URL) {
        this.URL = URLSeperator.concat(URL);
    }

    public String getURL() {
        return URL;
    }
}
