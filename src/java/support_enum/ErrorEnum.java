package support_enum;

public enum ErrorEnum {
	INVALID_PHONE_NUMBER("Số điện thoại không hợp lệ yêu cầu ít nhất 10 số và nhiều nhất 12 số"),
	EXIST_USERNAME("Tên đăng nhập đã tồn tại, vui lòng chọn tên khác"),
	LOGIN_ERROR("Tên đăng nhập hoặc mật khẩu không đúng"),
	CONFIRM_PASSWORD_NOT_MATCH("Mật khẩu nhập lại không trùng khớp"),
        PARAMETER_ERROR("Đã có lỗi xảy ra")
	;
	
	private String message;
	
	private ErrorEnum(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
