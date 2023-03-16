package support_enum;

public enum TimeEnum {
	_3DAYS(3 * 24 * 60 * 60),
	MAX_TIME(_3DAYS.time)
	;
	
	private int time;
	
	private TimeEnum(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}
}
