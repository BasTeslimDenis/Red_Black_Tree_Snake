package Snake;

import java.awt.Point;
import java.util.ArrayList;

public class Snake {

	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3,SCALE=10;
	public int direction = UP;

	public ArrayList<Point> SnakeBody;
	public Point Head;
	public int tail;

	public Snake() {
		Head = new Point();
		SnakeBody = new ArrayList<Point>();
		set_Head();
	}

	boolean CheckBodyCollision(Point Something) {
		for (Point i : SnakeBody) {
			if (Something.equals(i))
				return false;
		}
		return true;
	}
	
	void remove() {
		SnakeBody.remove(0);
	}

	void set_Head() {
		this.Head = new Point(0, 0);
	}

	void set_Head(Point NewHead) {
		Head.x = NewHead.x;
		Head.y = NewHead.y;
	}

	int get_dir() {
		return direction;
	}

	void add_SnakePart(Point newSnakeBody) {
		SnakeBody.add(new Point(newSnakeBody.x, newSnakeBody.y));
	}

	Point get_head() {
		return Head;
	}
}
