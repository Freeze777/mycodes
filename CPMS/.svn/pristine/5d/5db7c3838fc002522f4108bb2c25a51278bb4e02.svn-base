package centurylink.cpms;

import javax.persistence.*;

@Entity
@Table(name = "cpms_routes")
public class CpmsRoutes {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long routeid;
	
	@Column(name = "cid")
	private Long cid;
	 
	public CpmsRoutes() {
	
		// TODO Auto-generated constructor stub
	}

	@Column(name = "source")
	private String source;
	 
	 @Column(name = "destination")
	private String destination;
	 
	 
	 
	 @Column(name = "status")
	private int status;
	 
	 @Column(name = "sourcetime")
		private int sourcetime;
	 
	 @Column(name = "destinationtime")
		private int destinationtime;
	 @Column(name = "type")
	 private int type;

	 
	 public CpmsRoutes(Long cid, String source, String destination, int status,
			int sourcetime, int destinationtime, int type) {
		//super();
		this.cid = cid;
		this.source = source;
		this.destination = destination;
		this.status = status;
		this.sourcetime = sourcetime;
		this.destinationtime = destinationtime;
		this.type = type;
	}

	
	public Long getRouteid() {
		return routeid;
	}

	public void setRouteid(Long routeid) {
		this.routeid = routeid;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSourcetime() {
		return sourcetime;
	}

	public void setSourcetime(int sourcetime) {
		this.sourcetime = sourcetime;
	}

	public int getDestinationtime() {
		return destinationtime;
	}

	public void setDestinationtime(int destinationtime) {
		this.destinationtime = destinationtime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	
	
	
}
