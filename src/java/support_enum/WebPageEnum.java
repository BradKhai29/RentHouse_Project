package support_enum;

public enum WebPageEnum {
	HOME("index"),
	ERROR_404("error404"),
	USER_LOGIN("login"),
	USER_REGISTER("register"),
	USER_UPDATE_INFO("update_info"),
	PROVIDER_DASHBOARD("provider_dashboard"),
	ADMIN_LOGIN("login"),
	ADMIN_DASHBOARD("admin_dashboard"),
	ADMIN_UPDATE_INFO("update_info"),
	CREATE_NEW_POST("create_new_post"),
	RENT_HOUSE_DETAIL("detail"),
	RENT_HOUSE_UPDATE_INFO("update_info");
	
	private String page;
	
	private WebPageEnum(String page) {
		this.page = page.concat(".jsp");
	}
	
	public String getPage() {
		return page;
	}
}
