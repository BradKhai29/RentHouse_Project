package webpage_tools;

import java.util.Objects;

import support_enum.ServletEnum;

public final class URLBuilderFactory {
	private static URLBuilder urlBuilder;
	
	/**
	 * Get the URLBuilder created in this Factory
	 * @return
	 */
	public static URLBuilder get() {
		if(Objects.isNull(urlBuilder)) urlBuilder = new URLBuilder();
		return urlBuilder;
	}
	
	
	/**
	 * Get URL at given servlet resource
	 * @param servletEnum
	 * @return
	 */
	public static String getURL(ServletEnum servletEnum) {
		if(Objects.isNull(urlBuilder)) urlBuilder = new URLBuilder();
		
		String url = urlBuilder.getURL(servletEnum);
		return url;
	}
}
