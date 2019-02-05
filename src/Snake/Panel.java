package Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel {

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(new Color(174, 198, 207));
		g.fillRect(0, 0, 800, 700);

		Snake a = Snake_Game._Snake;
		g.setColor(Color.BLACK);

		for (Point i : a.SnakeBody) {
			g.fillRect(i.x * Snake.SCALE, i.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		}

		g.setColor(Color.GREEN);
		g.fillRect(a.Head.x * Snake.SCALE, a.Head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		g.setColor(Color.yellow);
	

		if (Snake_Game._Cherry != null) {
			Cherry b = Snake_Game._Cherry;
			g.fillRect(b.get_CherryX() * Snake.SCALE, b.get_CherryY() * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		}

		String show_info = "Scor: " + Snake_Game.score + " Lungime: " + a.tail;
		g.setColor(Color.WHITE);
		g.drawString(show_info, -(int) (getWidth() / 2 - show_info.length() * 2.5f), -15);

		if (Snake_Game.game_ongoing == false) {
			show_info = "Game Over! ";
			g.drawString(show_info, (int) (getWidth() / 2 - show_info.length() * 2.5f), 15);
			show_info = "Score: " + Snake_Game.score + ". Lungime snake: " + a.tail;
			g.drawString(show_info, (int) (getWidth() / 2 - show_info.length() * 2.5f), 30);
		}
		if (Snake_Game.pause == true) {
			show_info = "...Game Paused...";
			g.drawString(show_info, (int)(getWidth()/2 - show_info.length() * 2.5f), 15);
			show_info = "Score: " + Snake_Game.score + ". Lungime snake: " + a.tail;
			g.drawString(show_info, (int)(getWidth()/2 - show_info.length() * 2.5f), 30);
		}

	}

}
