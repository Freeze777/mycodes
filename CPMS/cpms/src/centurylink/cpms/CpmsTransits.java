package centurylink.cpms;

import javax.persistence.*;

@Entity
@Table(name = "cpms_transits")

public class CpmsTransits {
	
	public CpmsTransits() {
		
	}



	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long tid;
	
	@Column(name = "cid")
	private Long cid;
	 
	@Column(name = "routeid")
	private Long routeid;
	 
	 @Override
	public String toString() {
		return "CpmsTransits [tid=" + tid + ", cid=" + cid + ", routeid="
				+ routeid + ", transit=" + transit + ", type=" + type + "]";
	}



	@Column(name = "transit_points")
	private String transit;
	 
	 
	 
	 @Column(name = "type")
	private int type;



	public CpmsTransits(Long cid, Long routeid, String transit,
			int type) {
		//super();
		
		this.cid = cid;
		this.routeid = routeid;
		this.transit = transit.toLowerCase();
		this.type = type;
	}



	public Long getTid() {
		return tid;
	}



	public void setTid(Long tid) {
		this.tid = tid;
	}



	public Long getCid() {
		return cid;
	}



	public void setCid(Long cid) {
		this.cid = cid;
	}



	public Long getRouteid() {
		return routeid;
	}



	public void setRouteid(Long routeid) {
		this.routeid = routeid;
	}



	public String getTransit() {
		return transit;
	}



	public void setTransit(String transit) {
		this.transit = transit;
	}



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}
	 
}
