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
			if(!(filled)) {
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
		System.out.println("<html><head><style>.hex {"
+"	float: left;"
+"font-family: \"Courier New\", Courier, monospace;"
+"	font-size: 13px;"
+"	color: #C6C;"
+"	margin-bottom:-13px;"
+"}"
+".hex-row {"
+"clear:left;"
+"}"
+"div.hex-row:nth-of-type(even) {"
+"	margin-left:5px;"
+"}"
+".hex-null {"
+"	float: left;"
+"	font-family: \"Courier New\", Courier, monospace;"
+"	font-size: 13px;"
+"	color: transparent;"
+"	margin-bottom:-13px;"
+"}\n</style><script type=\"text/javascript\">"+"var clickCount = 0;"+
"function readMouseMove(e) {"
	+"currentCoords = document.getElementById('coords');"
	+"currentCoords.innerHTML = getMouseCoords(e);"
	+"div = document.getElementsByClassName('hex');" 
	+"for(var i=0; i < div.length; ++i) {"
	+"	div[i].innerHTML=\"&#x2B22;\""
	+"}"
	+"div = document.getElementsByClassName('hex-null');" 
	+"for(var i=0; i < div.length; ++i) {"
	+"	div[i].innerHTML=\"&#x2B22;\""
	+"}"
+"}"
+"function readMouseClick(e) {"
	+"clickCount+=1;"
	+"click_result = document.getElementById('clicks');"
	+"click_result.innerHTML = getMouseCoords(e);"
+"}"
+"function getMouseCoords(e) {"
	+"return \"<pre>(\"+e.clientX+\", \"+e.clientY+\")</pre>\";"
+"}"
+"document.onmousemove = readMouseMove;"
+"document.onclick = readMouseClick;</script></head><body><h2 id=\"clicks\"></h2><h2 id=\"coords\"></h2>");

		for(int i=ymin;i<=ymax;i++) {
			System.out.println("<div class=\"hex-row\">");
			for(int j=xmin;j<=xmax;j++) {
				boolean found=false;
				for(Hex hex : tiles) {
					if(hex.x==j && hex.y==i) {
						found=true;
						System.out.print("<div class=\"hex\" id=\""+j+","+i+"\"></div>");
					}
				}
				if(!(found)) {
					System.out.print("<div class=\"hex-null\"></div>");
				}
			}
			System.out.println("</div>");
		}
		System.out.println("</body></html>");
	}
}
			