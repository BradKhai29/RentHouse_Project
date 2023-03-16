package support_enum;

public enum ServletEnum {
	HOME("home"),;
	
	private String URL;
	private static final String URLSeperator = "/";
	
	private ServletEnum(String URL) {
		this.URL = URLSeperator.concat(URL);
	}
	
	public String getURL() {
		return URL;
	}
}
