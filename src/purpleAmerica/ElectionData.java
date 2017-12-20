package purpleAmerica;

public class ElectionData {
	private float democrats;
	private float republicans;
	private float others;
	private float total;
	private String name;
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String toString() {		
		return "Name: "+name+" Democrats: "+this.democrats+" republicans: "+this.republicans+" others: "+ this.others+" total: "+this.total;
	}

	public ElectionData(String name, String republicans, String democrats, String others, String state){
		this.state = state;
		this.democrats = Float.parseFloat(democrats);
		this.republicans = Float.parseFloat(republicans);
		this.others = Float.parseFloat(others);
		this.total = this.democrats + this.republicans + this.others;
		this.name = name;
		
	}

	public float getDemocrats() {
		return democrats;
	}

	public void setDemocrats(float democrats) {
		this.democrats = democrats;
	}

	public float getRepublicans() {
		return republicans;
	}

	public void setRepublicans(float republicans) {
		this.republicans = republicans;
	}

	public float getOthers() {
		return others;
	}

	public void setOthers(float others) {
		this.others = others;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
}
