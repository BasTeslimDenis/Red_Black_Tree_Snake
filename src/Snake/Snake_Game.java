package Snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.JFrame;

import java.util.Random;

public class Snake_Game implements ActionListener, KeyListener {

	public static boolean game_ongoing = true, pause = false;

	public Panel _Panel;
	public JFrame _JFrame;
	public static Snake_Game Snake;
	public static Snake _Snake;
	public static Cherry _Cherry;
	public static Dimension Dimensiune;
	public Random rand;
	public static Timer _Timer;
	public static Arbore_RB _Arbore;
	public static int Tick = 0;
	public static long score = 0;

	@SuppressWarnings("static-access")
	public void Window() {
		Dimensiune = Toolkit.getDefaultToolkit().getScreenSize();
		_JFrame = new JFrame("Snake");
		_JFrame.setVisible(true);
		_JFrame.setSize(800, 720);
		_JFrame.setResizable(false);
		_JFrame.setLocation(Dimensiune.width / 2 - _JFrame.getWidth() / 2,
				Dimensiune.height / 2 - _JFrame.getHeight() / 2);
		_JFrame.add(_Panel = new Panel());
		_JFrame.setDefaultCloseOperation(_JFrame.EXIT_ON_CLOSE);
		_JFrame.addKeyListener(this);
		_Snake = new Snake();
		_Timer = new Timer(30, this);
		_Timer.start();
		rand = new Random();

	}

	public static void main(String args[]) {
		Start_Game();
	}

	public static void Start_Game() {
		game_ongoing = true;
		score = 0;
		Tick = 0;
		_Arbore = new Arbore_RB();
		_Arbore.debugging();
		Snake = new Snake_Game();
		_Snake = new Snake();
		Snake.Window();
		_Cherry = new Cherry(new Point(10, 10));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		_Panel.repaint();
		Tick++;
		if (_Snake.get_head() != null && pause != true && Tick % 2 == 0 && game_ongoing == true) {
			_Snake.add_SnakePart(new Point(_Snake.Head.x, _Snake.Head.y));
			if (_Snake.get_dir() == 0) {
				_Snake.set_Head(new Point(_Snake.get_head().x, _Snake.get_head().y + 1));
				if (!_Snake.CheckBodyCollision(_Snake.get_head())) {
					game_ongoing = false;
				}

			}
			if (_Snake.get_dir() == 1) {
				_Snake.set_Head(new Point(_Snake.get_head().x, _Snake.get_head().y - 1));
				if (!_Snake.CheckBodyCollision(_Snake.get_head())) {
					game_ongoing = false;
				}

			}
			if (_Snake.get_dir() == 2) {
				_Snake.set_Head(new Point(_Snake.get_head().x - 1, _Snake.get_head().y));
				if (!_Snake.CheckBodyCollision(_Snake.get_head())) {
					game_ongoing = false;
				}

			}
			if (_Snake.get_dir() == 3) {
				_Snake.set_Head(new Point(_Snake.get_head().x + 1, _Snake.get_head().y));
				if (!_Snake.CheckBodyCollision(_Snake.get_head())) {
					game_ongoing = false;
				}

			}
			if (get_Cherry() != null) {
				if (_Snake.Head.x == _Cherry.get_CherryX() && _Snake.Head.y == _Cherry.get_CherryY()) {
					score+= _Cherry.Number/25;
					_Snake.tail++;
					_Arbore.insert_key(_Cherry.Get_Number());
					_Arbore.debugging();
					_Snake.add_SnakePart(new Point(get_Cherry().get_CherryX(), get_Cherry().get_CherryY()));
					_Cherry.new_Number();
					_Cherry.set_Cherry(_Snake, new Point(rand.nextInt(76) + 1, rand.nextInt(67) + 1));
				}
			}
			if (_Snake.get_head().x < 0 || _Snake.get_head().x > 80 || _Snake.get_head().y < 0
					|| _Snake.get_head().y > 67)
				game_ongoing = false;
			if (_Snake.SnakeBody.size() > _Snake.tail)
				_Snake.remove();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override

	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && _Snake.direction != 3) {
			_Snake.direction = 2;
		}
		if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && _Snake.direction != 2) {
			_Snake.direction = 3;
		}
		if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && _Snake.direction != 0) {
			_Snake.direction = 1;
		}
		if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && _Snake.direction != 1) {
			_Snake.direction = 0;
		}
		if (i == KeyEvent.VK_SPACE) {
			if (game_ongoing) {
				pause = !pause;
			} else if (!game_ongoing) {
				_Timer.stop();
				Start_Game();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static Cherry get_Cherry() {
		return _Cherry;
	}

	public static void set_Cherry(Cherry _Cherry) {
		Snake_Game._Cherry = _Cherry;
	}
}