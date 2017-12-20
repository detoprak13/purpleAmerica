package purpleAmerica;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import acm.graphics.*;
import acm.program.*;


public class RedBlue extends GraphicsProgram{
	private int scaleSize = 60;
	private String fileName = "";
	private int year;
	private ArrayList<String> data;
	private ArrayList<ElectionData> electionResult;
	private String preRegion = "";
	private HashMap<GPolygon, ElectionData> regionList;
	private GLabel nameClicked, democratClicked, republicanClicked, otherClicked;
	
	public RedBlue(String fileName, String year){
		super();
		this.fileName = fileName;
		this.year = Integer.parseInt(year);
		if (fileName.equals("USA") || fileName.equals("USA-county")) { scaleSize = 30; }
//		System.out.println("girdi1");
	}
	public void run() {
//		System.out.println("girdi");
		
		regionList = new HashMap<GPolygon, ElectionData>();
		int boundaryNumber;
		GPolygon shape = null;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		GLabel title = new GLabel("Purple America - "+fileName+" "+year+" Election");
		title.setFont(new Font("Arial",Font.ITALIC,44));
		add(title,this.getWidth()/2-title.getWidth()/2,this.getHeight()/8);
		double maxY,minY,maxX,minX;
		DataReader reader = new DataReader(fileName);
		data = reader.getData();
		String[] mins = data.get(0).split("   ");
		String[] maxs = data.get(1).split("   ");
		minX = Double.parseDouble(mins[0]);
		minY = Double.parseDouble(mins[1]);
		maxX = Double.parseDouble(maxs[0]);
		maxY = Double.parseDouble(maxs[1]);
		double boundary = Double.parseDouble(data.get(2));
		int index=4;
		while(data.get(index)!=null) {
			String subRegion = data.get(index);
			index++;
			String region = data.get(index);
			index++;						
			if (!region.equals(preRegion)){
				electionResult = electionData(region, year);
//				System.out.println(electionResult.get(0));
				if (electionResult == null){
					System.out.println("Year is incorrect!");
					return;
				}

				preRegion = region;
			}
//			System.out.println("index: "+index+" sub: "+subRegion+" region: "+region);
			boundaryNumber = Integer.parseInt(data.get(index));
			shape = new GPolygon();
			int limit = index + boundaryNumber;
			while(index<limit) {
				index++;
				String[] coordinates = data.get(index).split(" ");
				double xCoor, yCoor = 0;
				int i = 0;
				if (coordinates.length > 4) { i++; }
				xCoor = Double.parseDouble(coordinates[i]) - minX;
				yCoor = maxY- Double.parseDouble(coordinates[i + 3]);
				shape.addVertex(scaleSize*xCoor, scaleSize*yCoor);				
			}
			shape.setColor(Color.BLACK);
			shape.setFilled(true);
//			if(subRegion.contains("Parish")) {
//				String tem = subRegion.substring(0, subRegion.length()-7);
//				subRegion = tem;
//			}
			ElectionData subRegionData = searchInArrayList(subRegion, electionResult);
			if (subRegionData != null){
				if(subRegionData.getRepublicans()>subRegionData.getDemocrats() && subRegionData.getRepublicans()>subRegionData.getOthers()) {
					shape.setFillColor(Color.red);
				}else if(subRegionData.getDemocrats()>subRegionData.getRepublicans() && subRegionData.getDemocrats()>subRegionData.getOthers()) {
					shape.setFillColor(Color.blue);
				}else{
					shape.setFillColor(Color.green);
				}
			}
			else{
				shape.setFilled(false);
			}
			add(shape,this.getWidth()/2- scaleSize*(maxX-minX)/2,this.getHeight()/2-scaleSize*(maxY-minY)/2);
			regionList.put(shape, subRegionData);
			index++;
			index++;
			if(index>=data.size()) break;
		}
		initLargant();
		addMouseListeners();
		
	}
	public ArrayList<ElectionData> electionData(String file, int year){
//		System.out.println("girdi");
		DataReader reader = new DataReader(file+year);
		ArrayList<String> data = reader.getData();
		ArrayList<ElectionData> electionData = new ArrayList<ElectionData>();
		int i=0;
		for(String line : data) {
			if(i!=0) {
				String[] splittedLine = line.split(",");
				ElectionData elecData = new ElectionData(splittedLine[0], splittedLine[1], splittedLine[2], splittedLine[3], file);
//				System.out.println(elecData.toString());
				electionData.add(elecData);
				
			}
			i++;
		}
		return electionData;
	}
	public ElectionData searchInArrayList(String name, ArrayList<ElectionData> arr) {
		ElectionData result = null;
		String[] splittedName = name.split(" ");
		
		for(ElectionData temp : arr) {
			String tempName = "";
			if(name.equalsIgnoreCase(temp.getName())) {
				return temp;
			}
			for(int i=0; i<splittedName.length; i++) {
				if(i>0) { tempName+=" "; }
				tempName +=splittedName[i];		
				if(tempName.equalsIgnoreCase(temp.getName())) {
					return temp;
				}
			}			
		}
		
		return result;
	}
	public void initLargant() {
		GRect democratSq = new GRect(50,50);
		GRect republicanSq = new GRect(50,50);
		GRect otherSq = new GRect(50,50);
		GLabel democratLb = new GLabel("Democrat");
		GLabel republicanLb = new GLabel("Republican");
		GLabel otherLb = new GLabel("Other");
		Font font = new Font("Arial",20,20);
		democratSq.setFilled(true);
		republicanSq.setFilled(true);
		otherSq.setFilled(true);
		democratSq.setFillColor(Color.BLUE);
		republicanSq.setFillColor(Color.RED);
		otherSq.setFillColor(Color.GREEN);
		democratLb.setFont(font);
		otherLb.setFont(font);
		republicanLb.setFont(font);
		int labelSize = 20;
		double rectX = this.getWidth()- democratSq.getWidth()-150;
		double rectY = this.getHeight()- otherSq.getHeight()-80;
		add(democratSq, rectX, rectY-210);
		add(republicanSq, rectX, rectY-140);
		add(otherSq, rectX, rectY-70);
		add(democratLb, this.getWidth()- democratLb.getWidth()-220, rectY-210 + labelSize);
		add(republicanLb, this.getWidth()- republicanLb.getWidth()-220, rectY-140 + labelSize);
		add(otherLb, this.getWidth()- otherLb.getWidth()-220, rectY-70 + labelSize);
		nameClicked = new GLabel("");
		democratClicked = new GLabel("");
		republicanClicked = new GLabel("");
		otherClicked = new GLabel("");
		nameClicked.setFont(font);
		democratClicked.setFont(font);
		republicanClicked.setFont(font);
		otherClicked.setFont(font);
		add(nameClicked, 10, getHeight()-250);
		add(democratClicked, 10, getHeight()-230);
		add(republicanClicked, 10, getHeight()-210);
		add(otherClicked, 10, getHeight()-190);
	}
	public void mouseClicked(MouseEvent e) {
		GPolygon shape;
		try{
			shape = (GPolygon)this.getElementAt(e.getX(), e.getY());
		}
		catch(ClassCastException exc){
			return;
		}
		ElectionData clickedRegion = regionList.get(shape);
		nameClicked.setLabel("Name: "+clickedRegion.getName()+"-"+clickedRegion.getState());
		democratClicked.setLabel("Democrat Votes: "+clickedRegion.getDemocrats());
		republicanClicked.setLabel("Republican Votes: "+clickedRegion.getRepublicans());
		otherClicked.setLabel("Other Votes: "+clickedRegion.getOthers());
		
	}
}
