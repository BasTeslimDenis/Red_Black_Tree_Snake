package Snake;

public class Arbore_RB {

	private static final boolean RED = true, Blk = false;

	private Node root;

	public void debugging() {
		System.out.println(root.size());
	}

	public void insert_key(int x) {

		System.out.println(x);
		root.insert_key(x);

	}

	public Arbore_RB() {
		root = new Node(0, Blk, 1);
		root.parent = null;
		root.left = new Node();
		root.right = new Node();
	}

	private class Node {

		private Node parent, left, right;
		private boolean culoare;
		private int key;
		private int size;

		public Node() {
			this.key = 0;
			this.culoare = true;
			this.size = 0;
		}

		public Node(int key, boolean color, int size) {
			this.key = key;
			this.culoare = color;
			this.size = size;
		}

		private void insert_key(int x) {
			root = insert(root, x);
			root.culoare = Blk;
		}

		private Node insert(Node x, int key) {
			if (x.key == 0) {
				return new Node(key, RED, 1);
			}

			int compare = x.key > key ? -1 : 1;
			
			if(compare==key)
				return x;

			if (compare < 0) {
				System.out.println("Mutare stanga");
				x.left = new Node();
				x.left.parent=x;
				x.insert(x.left, key);
			} else if (compare > 0) {
				System.out.println("Mutare dreapta");
				x.right = new Node();
				x.right.parent=x;
				x.insert(x.right, key);
			} else {
				x.key = key;
			}
			if (red_check(x.right) && !red_check(x.left))
				x = rotateleft(x);
			if (red_check(x.left) && red_check(x.left.left))
				x = rotateright(x);
			if (red_check(x.left) && red_check(x.right))
				flip(x);

			x.size = return_size(x.left) + return_size(x.right) +1;  
			
			return x;

		}

		private int return_size(Node x) {
			if (x == null)
				return 0;
			return x.size+1;
		}
		
		

		private Node rotateleft(Node x) {
			Node newNode = x.right;
			x.right = newNode.left;
			newNode.left = x;
			newNode.culoare = newNode.left.culoare;
			newNode.left.culoare = RED;
			newNode.size = x.size;
			x.size = return_size(x.left) + return_size(x.right) + 1;
			return x;
		}

		private Node rotateright(Node x) {
			Node newNode = x.left;
			x.left = newNode.right;
			newNode.right = x;
			newNode.culoare = newNode.right.culoare;
			newNode.right.culoare = RED;
			newNode.size = x.size;
			x.size = return_size(x.left) + return_size(x.right) + 1;
			return x;
		}

		private void flip(Node x) {
			x.culoare = !x.culoare;
			x.left.culoare = !x.left.culoare;
			x.right.culoare = !x.right.culoare;
		}

		public int size() {
			return return_size(root);
		}

		public boolean red_check(Node x) {
			if (x == null) {
				return false;
			}
			return x.culoare = true;
		}
	}

}
