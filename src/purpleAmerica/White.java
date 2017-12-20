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


public class White extends GraphicsProgram{
	private int scaleSize = 60;
	private String fileName = "";
	private String[] args;
	private ArrayList<String> data;
	
	public White(String fileName){
		super();
		this.fileName = fileName;
			
		if (fileName.equals("USA") || fileName.equals("USA-county")) { scaleSize = 30; }
//		System.out.println("girdi1");
	}
	public void run() {
//		System.out.println("girdi");
		int boundaryNumber;
		GPolygon shape = null;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		GLabel title = new GLabel("White America - "+fileName);
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
			add(shape,this.getWidth()/2- scaleSize*(maxX-minX)/2,this.getHeight()/2-scaleSize*(maxY-minY)/2);
			
			index++;
			index++;
			if(index>=data.size()) break;
		}		
	}
	
}
