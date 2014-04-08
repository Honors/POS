package pos.core;

public interface UpdateableContent {
	public final static String INVENTORY_UPDATED = "inventory_updated";
	public final static String RETURN_UPDATED = "return_updated";
	public final static String BRAND_UPDATED = "brand_updated";
	public final static String COLOR_UPDATED = "color_updated";
	public final static String SIZE_UPDATED = "size_updated";
	public final static String TYPE_UPDATED = "type_updated";
	public final static String GENDER_UPDATED = "gender_updated";
	public final static String CLIENT_UPDATED = "client_updated";
	public final static String LOGIN_UPDATED = "login_updated";
	public final static String READABLE_LOG_UPDATED = "readable_log_updated";
	public final static String CHANGE_LOG_UPDATED = "change_log_updated";
	
	public void update(String updateIdentifier, String info);
}
