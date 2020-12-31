import junit.framework.TestCase;

public class SolverTest extends TestCase {

    public void testIsSolvable() {
        int[][] tiles = { {1, 2} , {3, 0} };
        Board board = new Board(tiles);
        Solver solver = new Solver(board);
        assertTrue(solver.isSolvable());
    }

    public void testIsNotSolvable() {
        int[][] tiles = { {3, 0} , {1, 2} };
        Board board = new Board(tiles);
        Solver solver = new Solver(board);
        assertFalse(solver.isSolvable());
    }

    public void testIsNotSolvable3x3() {
        int[][] tiles = { {1, 2, 3} , {4, 5, 6}, {8, 7, 0} };
        Board board = new Board(tiles);
        Solver solver = new Solver(board);
        assertFalse(solver.isSolvable());
    }

    public void testMovesTrivial() {
        int[][] tiles = { {1, 0} , {3, 2} };
        Board board = new Board(tiles);
        Solver solver = new Solver(board);
        assertEquals(1, solver.moves());
    }

    public void testMoves3x3() {
        int[][] tiles = { {0, 1, 3} , {4, 2, 5}, {7, 8, 6} };
        Board board = new Board(tiles);
        Solver solver = new Solver(board);
        assertEquals(4, solver.moves());
    }

    public void testMoves() {
        int[][] tiles = { {5, 1, 8} , {2, 7, 3}, {4, 0, 6} };
        Board board = new Board(tiles);
        Solver solver = new Solver(board);
        assertEquals(17, solver.moves());
    }

    public void testSolution() {
    }
}