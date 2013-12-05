import java.util.Iterator;


public class BoardTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create initial board from file
		In in = new In("puzzle04.txt");
		int N = in.readInt();
		int [][] blocks = new int[N][N];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				blocks[i][j] = in.readInt();
			}
		}
		Board initial = new Board(blocks);
		StdOut.println("hamming distance = " + initial.hamming());
		StdOut.println("manhattan distance = " + initial.manhattan());
		
		StdOut.println("board toString = \r\n" + initial);
		
		Iterator<Board> it = initial.neighbors().iterator();
		StdOut.println("neighbors : \r\n");
		while(it.hasNext()){
			StdOut.println(it.next());
		}
	}
}
