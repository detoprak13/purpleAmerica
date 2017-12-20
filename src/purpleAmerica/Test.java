package purpleAmerica;

import java.util.Scanner;

import acm.program.GraphicsProgram;

public class Test {
	public static String path = "D:\\workspace\\purpleAmerica\\purple\\";
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GraphicsProgram RedBlue, white, Purple;
		Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        while(!line.equals("exit")) {
        	long start = System.currentTimeMillis();
        	args = line.split(" ");
			if(args[0]!=null) {
				if(args[0].equalsIgnoreCase("white")) {
	//				DataReader reader = new DataReader(args);
	//				reader.toString();
					white = new White(args[1]); 
					white.start();
					long end = System.currentTimeMillis();
					System.out.println("Finished in: " + (end-start) + "ms");
				}else if(args[0].equalsIgnoreCase("RedBlue")){
					RedBlue = new RedBlue(args[1], args[2]); 
					RedBlue.start();
					long end = System.currentTimeMillis();
					System.out.println("Finished in: " + (end-start) + "ms");
				}else if(args[0].equalsIgnoreCase("Purple")){
					Purple = new Purple(args[1], args[2]); 
					Purple.start();
					long end = System.currentTimeMillis();
					System.out.println("Finished in: " + (end-start) + "ms");
				}
				
			}else {
				System.out.println("Arguement Error!");
			}
			line = sc.nextLine();
        }
		
		

	}

}
