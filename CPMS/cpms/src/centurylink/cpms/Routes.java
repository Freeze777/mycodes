package centurylink.cpms;

public class Routes {
	
	
	private String toSource,toDestination,toOfficeStartTime;
	private String toOfficeEndTime,towardsTransits,fromSource,fromDestination,fromOfficeStartTime;
	private String fromOfficeEndTime,fromTransits;
	private String toOfficeSeats,fromOfficeSeats;
	
	public String getToOfficeSeats() {
		return toOfficeSeats;
	}

	public void setToOfficeSeats(String toOfficeSeats) {
		this.toOfficeSeats = toOfficeSeats;
	}

	public String getFromOfficeSeats() {
		return fromOfficeSeats;
	}

	public void setFromOfficeSeats(String fromOfficeSeats) {
		this.fromOfficeSeats = fromOfficeSeats;
	}

	private long toRouteId;
	public long getToRouteId() {
		return toRouteId;
	}

	public void setToRouteId(long toRouteId) {
		this.toRouteId = toRouteId;
	}

	public long getFromRouteId() {
		return FromRouteId;
	}

	public void setFromRouteId(long fromRouteId) {
		FromRouteId = fromRouteId;
	}

	private long FromRouteId;
	
	
	
	public String getToSource() {
		return toSource;
	}
	
	public void setToSource(String toSource) {
		this.toSource = toSource;
	}
	public String getToDestination() {
		return toDestination;
	}
	public void setToDestination(String toDestination) {
		this.toDestination = toDestination;
	}
	public String getToOfficeStartTime() {
		return toOfficeStartTime;
	}
	public void setToOfficeStartTime(String toOfficeStartTime) {
		this.toOfficeStartTime = toOfficeStartTime;
	}
	public String getToOfficeEndTime() {
		return toOfficeEndTime;
	}
	public void setToOfficeEndTime(String toOfficeEndTime) {
		this.toOfficeEndTime = toOfficeEndTime;
	}
	public String getTowardsTransits() {
		return towardsTransits;
	}
	public void setTowardsTransits(String towardsTransits) {
		this.towardsTransits = towardsTransits;
	}
	public String getFromSource() {
		return fromSource;
	}
	public void setFromSource(String fromSource) {
		this.fromSource = fromSource;
	}
	public String getFromDestination() {
		return fromDestination;
	}
	public void setFromDestination(String fromDestination) {
		this.fromDestination = fromDestination;
	}
	public String getFromOfficeStartTime() {
		return fromOfficeStartTime;
	}
	public void setFromOfficeStartTime(String fromOfficeStartTime) {
		this.fromOfficeStartTime = fromOfficeStartTime;
	}
	public String getFromOfficeEndTime() {
		return fromOfficeEndTime;
	}
	public void setFromOfficeEndTime(String fromOfficeEndTime) {
		this.fromOfficeEndTime = fromOfficeEndTime;
	}
	public String getFromTransits() {
		return fromTransits;
	}
	public void setFromTransits(String fromTransits) {
		this.fromTransits = fromTransits;
	}

	@Override
	public String toString() {
		return "Routes [toSource=" + toSource + ", toDestination="
				+ toDestination + ", toOfficeStartTime=" + toOfficeStartTime
				+ ", toOfficeEndTime=" + toOfficeEndTime + ", toOfficeSeats=" + toOfficeSeats
				+ ", towardsTransits="	+ towardsTransits 
				+ ", fromSource=" + fromSource
				+ ", fromDestination=" + fromDestination
				+ ", fromOfficeStartTime=" + fromOfficeStartTime
				+ ", fromOfficeEndTime=" + fromOfficeEndTime + ", fromOfficeSeats=" + fromOfficeSeats
				+ ", fromTransits=" + fromTransits + "]";
	}
	
	

}
