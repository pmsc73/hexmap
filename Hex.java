import java.util.ArrayList;
import java.lang.Math;
public class Hex {
	public int x;
	public int y;
	public int id;
	public int roll;
	public Hex(int id, int x, int y) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	public int getName() {
		return id;
	}
	public boolean isAdjacent(Hex other) {
		int diffX = this.x-other.x;
		int diffY = this.y-other.y;
		if(diffY == 0) {
			return (Math.abs(diffX) == 1); // other.x = this.x +- 1
		}
		if(Math.abs(diffY) == 1) {
			return (diffX == 0 || (this.x%2==0 && diffX == this.x-1) || (this.x%2==1 && diffX==this.x+1));
			
		}
		return false;
	}
	
}