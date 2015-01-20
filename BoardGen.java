import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;

public class BoardGen {
	private static int count=0;

	private static int x = 0;
	private static int y = 0;
	private static int rc,bc,gc,cc,yc,mc;
	private static Random generator;
	public static String randomColor(int size,int players) {
		int roll = (int)(generator.nextDouble()*players);
		int threshold = size/players;
		
	switch(roll) {
		case 0:
			if(rc>threshold) return randomColor(size,players);
			rc++;
			return "#E44";
		case 1:
			if(bc>threshold) return randomColor(size,players);
			bc++;
			return "#44E";
		case 2:
			if(gc>threshold) return randomColor(size,players);
			gc++;
			return "#4E4";
		case 3:
			if(yc>threshold) return randomColor(size,players);
			yc++;
			return "#EE4";
		case 4:
			if(mc>threshold) return randomColor(size,players);
			mc++;
			return "#E4E";
		case 5:
			if(cc>threshold) return randomColor(size,players);
			cc++;
			return "#4EE";
		}
		return "";
	}
	public static void nextCoords(int roll) {
		boolean evenRow = (Math.abs(y)%2==0);
		switch(roll) {
		case 0:
			x = (evenRow)? x:x+1;
			y = y-1;
			break;
		case 1:
			x = x+1;
			break;
		case 2:
			x = (evenRow)? x:x+1;
			y=y+1;
			break;
		case 3:
			x = (evenRow)? x-1:x;
			y=y+1;
			break;
		case 4:
			x=x-1;
			break;
		case 5:
			x = (evenRow)? x-1:x;
			y=y-1;
			break;
		}
	}		
	public static String hexString(int dec) {
		if(dec==0) return "F00";
		return "rgb(0,"+dec+",0)";
		
	}
	public static void main(String args[]) {
		ArrayList<Hex> tiles = new ArrayList<Hex>();
		tiles.add(new Hex(6,count++,0,0));
		String scale = "l";
		if(args.length < 2) {
			System.out.println("usage: java BoardGen tiles players [seed] [size=s|l] > out.html");
			System.exit(1);
		}
		if(args.length >= 3) {
			generator = new Random(Integer.parseInt(args[2]));
		}
		if(args.length == 4) {
			scale=args[3];
		}
		else {
			generator = new Random();
		}
		int size = Integer.parseInt(args[0]);
		int players = Integer.parseInt(args[1]);
		while(count<size) {
			int roll = (int)(generator.nextDouble()*6);
			nextCoords(roll);
			boolean filled = false;
			for(Hex hex : tiles) {
				if(hex.x==x && hex.y==y) {
					filled=true;
				}
			}
			if(!(filled)) {
				tiles.add(new Hex(roll,(255*count++/size),x,y));
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
		System.out.println("<html><head>\n</head>\n<body><script type=\"text/javascript\" src=\"hex.js\"></script><h2 id=\"clicks\"></h2><h2 id=\"coords\"></h2>");
		
		System.out.println("<div class=\"board\">");
		int ypar=0;
		if(((Math.abs(ymin)) % 2)== 1)
			ypar=1;
			
		for(int i=ymin;i<=ymax;i++) {
			
			System.out.println("<div class=\"hex-row\">");
			for(int j=xmin;j<=xmax;j++) {
				boolean found=false;
				for(Hex hex : tiles) {
					if(hex.x==j && hex.y==i) {
						found=true;
						System.out.println("<div class=\"hex\" data-x=\""+(j-xmin)+"\" data-y=\""+(i-ymin-ypar)+"\">&#x2B22;</div>");
						System.out.println("<div class=\"label\" data-x=\""+(j-xmin)+"\" data-y=\""+(i-ymin-ypar)+"\">"+hex.roll+"</div>");
						
					}
				}
			}
			System.out.println("</div>");
		}
		System.out.println("</div>");
		double degreeSum=0;
		double degreeDev=0;
		ArrayList<Hex> redundancies = new ArrayList<Hex>();
		for(Hex hex : tiles) {
			int degree=0;
			for(Hex other : tiles) {
				if(hex != other) {
					degree += (hex.isAdjacent(other))? 1 : 0;
				}
			}
			degreeSum += degree;
		}
		double variance=0;
		for(Hex hex : tiles) {
			int degree=0;
			for(Hex other : tiles) {
				if(hex != other) {
					degree += (hex.isAdjacent(other))? 1 : 0;
				}
			}
			double squareDiff = degree - (degreeSum/tiles.size());
			squareDiff = Math.pow(squareDiff,2);
			variance+=squareDiff;
		}
		variance /= tiles.size();
	}
}
			