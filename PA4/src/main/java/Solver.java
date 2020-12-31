import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {
    private final SearchNode answer;

    /**
     * find a solution to the initial board (using the A* algorithm)
     * @param initial initial board
     */
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        Board twin = initial.twin();

        SearchNode initialNode1 = new SearchNode(initial, 0, null, initial.manhattan());
        SearchNode initialNode2 = new SearchNode(twin, 0, null, twin.manhattan());
        Comparator<SearchNode> priorityFunction = Comparator.comparingInt(o -> o.manhattan + o.moves);
        MinPQ<SearchNode> queue1 = new MinPQ<>(priorityFunction);
        MinPQ<SearchNode> queue2 = new MinPQ<>(priorityFunction);
        queue1.insert(initialNode1);
        queue2.insert(initialNode2);
        int turn = 0;

        while (true) {
            if (turn % 2 == 0 && !queue1.isEmpty()) {
                SearchNode node = queue1.delMin();
                if (node.board.isGoal()) {
                    answer = node;
                    break;
                } else {
                    for (Board neighbor: node.board.neighbors()) {
                        // critical optimization: don’t enqueue a neighbor if its board is
                        // the same as the board of the previous search node in the game tree.
                        if (node.parent != null && neighbor.equals(node.parent.board)) {
                            continue;
                        }
                        queue1.insert(new SearchNode(neighbor, node.moves + 1, node, neighbor.manhattan()));
                    }
                }
            } else if (!queue2.isEmpty()) {
                SearchNode node = queue2.delMin();
                if (node.board.isGoal()) {
                    // this is twin board
                    answer = null;
                    break;
                } else {
                    for (Board neighbor: node.board.neighbors()) {
                        // critical optimization: don’t enqueue a neighbor if its board is
                        // the same as the board of the previous search node in the game tree.
                        if (node.parent != null && neighbor.equals(node.parent.board)) {
                            continue;
                        }
                        queue2.insert(new SearchNode(neighbor, node.moves + 1, node, neighbor.manhattan()));
                    }
                }
            }
            turn++;
        }

    }

    /**
     * @return is the initial board solvable?
     */
    public boolean isSolvable() {
        return answer != null;
    }

    /**
     * @return min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        return answer != null ? answer.moves : -1;
    }

    /**
     * @return sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        if (answer == null) {
            return null;
        }

        List<Board> boardList = new ArrayList<>();
        SearchNode cursor = answer;
        while (cursor != null) {
            boardList.add(0, cursor.board);
            cursor = cursor.parent;
        }

        return boardList;
    }

    private static class SearchNode {
        public Board board;
        public int moves;
        public int manhattan;
        public SearchNode parent;

        public SearchNode(Board board, int moves, SearchNode parent, int manhattan) {
            this.board = board;
            this.moves = moves;
            this.manhattan = manhattan;
            this.parent = parent;
        }
    }
}