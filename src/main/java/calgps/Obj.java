package calgps;

public class Obj {
	private int number;
	private float vol;
	private String state;
	
	public void setNumber(int song){
		this.number=song;
	}
	public void setVol(Float t){
		this.vol=t;
	}
	public void setState(String t){
		this.state= t;
	}
	public float getNumber(){
		return number;
	}
	public float getVol(){
		return vol;
	}
	public String getState(){
		return state;
	}
	public Obj(){
		
	}
	public Obj(String t, int t1, float t2){
		this.state=t;
		this.number=t1;
		this.vol=t2;
	}
	public String toString(){
		return this.state+","+this.number+","+this.vol;
	}
}
