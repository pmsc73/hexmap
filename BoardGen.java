import java.util.ArrayList;
import java.lang.Math;

public class BoardGen {
	private static int count=0;

	private static int x = 0;
	private static int y = 0;
	public static void nextCoords(int roll) {
		boolean evenRow = (y%2==0);
		switch(roll) {
		case 0:
			x = (evenRow)? x:x+1;
			y = y+1;
			break;
		case 1:
			x = x+1;
			break;
		case 2:
			x = (evenRow)? x:x+1;
			y=y-1;
			break;
		case 3:
			x = (evenRow)? x-1:x;
			y=y-1;
			break;
		case 4:
			x=x-1;
			break;
		case 5:
			x = (evenRow)? x-1:x;
			y=y+1;
			break;
		}
	}		
	public static void main(String args[]) {
		ArrayList<Hex> tiles = new ArrayList<Hex>();
		tiles.add(new Hex(count++,0,0));
		int size = Integer.parseInt(args[0]);
		while(count<size) {
			int roll = (int)(Math.random()*6);
			nextCoords(roll);
			boolean filled = false;
			for(Hex hex : tiles) {
				if(hex.x==x && hex.y==y) {
					filled=true;
				}
			}
			if(!(filled) && Math.random() > 0.1) {
				tiles.add(new Hex(count++,x,y));
			}
		}
		int xmin=Integer.MAX_VALUE;
		int ymin=Integer.MAX_VALUE;
		int xmax=Integer.MIN_VALUE;
		int ymax=Integer.MIN_VALUE;
		for(Hex hex : tiles) {
			if(hex.x < xmin) {
				xmin = hex.x;
			}
			if(hex.y < ymin) {
				ymin = hex.y;
			}
			if(hex.x > xmax) {
				xmax = hex.x;
			}
			if(hex.y > ymax) {
				ymax = hex.y;
			}
		}
		for(Hex hex : tiles) {
			System.out.println(hex.getName()+": ("+hex.x+", "+hex.y+")");
		}
		System.out.println("XMAX: " + xmax+" YMAX: " +ymax);
		System.out.println("XMIN: " + xmin+" YMIN: " +ymin);

		for(int i=ymin;i<=ymax;i++) {
			System.out.println("<div class=\"hex-row\">");
			for(int j=xmin;j<=xmax;j++) {
				boolean found=false;
				for(Hex hex : tiles) {
					if(hex.x==j && hex.y==i) {
						found=true;
						System.out.println("<div class=\"hex\"></div> <!-- x="+j+" y="+i+" -->");
					}
				}
				if(!(found)) {
					System.out.println("<div class=\"hex-null\"></div>");
				}
			}
			System.out.println("</div>");
		}
	}
}
			