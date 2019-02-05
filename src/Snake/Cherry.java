package Snake;

import java.awt.Point;
import java.util.Random;

public class Cherry {
	Point Location;

	Random rand;
	
	int Number;

	public Cherry(Point x) {
		rand = new Random();
		Number = rand.nextInt(10000)+1;
		Location = new Point(10,10);
	}
	
	public void new_Number() {
		Number = rand.nextInt(10000)+1;
	}

	public int Get_Number() {
		return this.Number;
	}

	public void set_Cherry(Snake a, Point x) {
		if (a.Head.x == x.x && a.Head.y == x.y  || !a.CheckBodyCollision(x)) {
			while (!a.CheckBodyCollision(x))
				x.x = rand.nextInt(79);
			while (!a.CheckBodyCollision(x))
				x.y = rand.nextInt(67);
		}
		set_Cherry(x);
	}
	
	public void set_Cherry(Point x) {
		if (x != null) {
			Location.x = x.x;
			Location.y = x.y;
		} else {
			Location.x = rand.nextInt(76);
			Location.y = rand.nextInt(67);
		}
	}

	int get_CherryX() {
		return Location.x;
	}

	int get_CherryY() {
		return Location.y;
	}
}
