package centurylink.cpms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "cpms_seats")
public class CpmsSeats {
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long sid;
	

	@Column(name = "cid")
	private long cid;
	 
	 @Column(name = "routeid")
	private long routeid;
	 
	 @Column(name = "seats")
	private int seats;
	 
	 
	 @Column(name = "type")
	private int type;
	 
	@Override
	public String toString() {
		return "CpmsSeats [cid=" + cid + ", routeid=" + routeid + ", seats=" + seats
				+ ", type=" + type + "]";
	}


	public Long getSid() {
		return sid;
	}


	public CpmsSeats() {
		
	}


	public void setSid(Long sid) {
		this.sid = sid;
	}


	public CpmsSeats(long cid, long routeid, int seats, int type) {
		super();
		this.cid = cid;
		this.routeid = routeid;
		this.seats = seats;
		this.type = type;
	}


	public long getCid() {
		return cid;
	}


	public void setCid(long cid) {
		this.cid = cid;
	}


	public long getRouteid() {
		return routeid;
	}


	public void setRouteid(long routeid) {
		this.routeid = routeid;
	}


	public int getSeats() {
		return seats;
	}


	public void setSeats(int seats) {
		this.seats = seats;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	 
	

}
