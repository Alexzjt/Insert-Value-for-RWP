
public class Rwp_class {
	public int time;
	public int  nodeID;
	public double lon;
	public double lat;
	public String speed;
	
	public Rwp_class(int time, int nodeID, double lon, double lat, String speed) {
		
		this.time = time;
		this.nodeID = nodeID;
		this.lon = lon;
		this.lat = lat;
		this.speed = speed;
	}

	public Rwp_class() {
		
	}

	public Rwp_class(int time, int nodeID, double lon, double lat) {
		
		this.time = time;
		this.nodeID = nodeID;
		this.lon = lon;
		this.lat = lat;
	}

	public Rwp_class(int nodeID) {
		
		this.nodeID = nodeID;
	}

	public int getNodeID() {
		return nodeID;
	}
	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}
	public double getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}

	@Override
	public String toString() {
		return time+"\t"+nodeID+"\t"+lon+"\t"+lat+"\t"+speed+"\r\n";
	}
	public boolean isEmpty(){
		if(this.lat==0 &&lon==0&&time==0&&speed.equals("0")&&nodeID==0)
			return true;
		else
			return false;
		
	}
}
