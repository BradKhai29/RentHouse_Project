package webpage_tools;

import support_enum.FolderEnum;
import support_enum.ServletEnum;
import support_enum.WebPageEnum;

public final class URLBuilder {
	private final String rootURL = "/";;
	private final StringBuilder builder;
	
	public URLBuilder() {
		builder = new StringBuilder(rootURL);
	}
	
	public URLBuilder addFolder(FolderEnum... folderEnums) {
		for(FolderEnum folderEnum : folderEnums)
		{
			builder.append(folderEnum.getFolderName());
		}
		return this;
	}
	
	public URLBuilder addPage(WebPageEnum webPageEnum) {
		builder.append(webPageEnum.getPage());
		return this;
	}
	
	private void resetBuilder() {
		builder.delete(0, builder.length());
		builder.append(rootURL);
	}
	
	public String getURL() {
		String resultURL = builder.toString();
		resetBuilder();
		return resultURL;
	}
	
	public String getURL(ServletEnum servletEnum) {
		builder.append(servletEnum.getURL());
		String resultURL = builder.toString();
		resetBuilder();
		return resultURL;
	}
}
