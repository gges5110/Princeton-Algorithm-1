import junit.framework.TestCase;

public class BoardTest extends TestCase {

    public void testTestToString() {
        int[][] tiles = { {1, 2} , {3, 0} };
        Board board = new Board(tiles);
        assertEquals(
            "2\n 1 2\n 3 0\n",
            board.toString()
        );
    }


    public void testHammingSimple() {
        int[][] tiles = { {1, 2} , {3, 0} };
        Board board = new Board(tiles);
        assertEquals(0, board.hamming());
    }

    public void testHamming() {
        int[][] tiles = { {1, 0} , {2, 3} };
        Board board = new Board(tiles);
        assertEquals(2, board.hamming());
    }

    public void testManhattanSimple() {
        int[][] tiles = { {1, 2} , {3, 0} };
        Board board = new Board(tiles);
        assertEquals(0, board.manhattan());
    }

    public void testManhattan() {
        int[][] tiles = { {0, 1, 3} , {4, 2, 5}, {7, 8, 6} };
        Board board = new Board(tiles);
        assertEquals(4, board.manhattan());
    }

    public void testDimension() {
        int[][] tiles = { {1, 2} , {3, 0} };
        Board board = new Board(tiles);
        assertEquals(2, board.dimension());
    }

    public void testIsGoal() {
        int[][] tiles = { {1, 2} , {3, 0} };
        Board board = new Board(tiles);
        assertTrue(board.isGoal());
    }

    public void testIsGoalFalse() {
        int[][] tiles = { {2, 1} , {3, 0} };
        Board board = new Board(tiles);
        assertFalse(board.isGoal());
    }

    public void testNeighbors() {
        int[][] tiles = { {1, 2, 3} , {4, 5, 0}, {7, 8, 6} };
        Board board = new Board(tiles);

        int len = 0;
        for (Board value : board.neighbors()) {
            len++;
        }

        assertEquals(3, len);
    }
}