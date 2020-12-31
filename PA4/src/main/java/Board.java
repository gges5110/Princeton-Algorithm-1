import java.util.LinkedList;

public class Board {
    private final int[][] tiles;
    private final int dimension;
    private int manhattan, hamming;

    /**
     * create a board from an n-by-n array of tiles
     * @param tiles tiles[row][col] = tile at (row, col)
     */
    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException();
        }
        dimension = tiles.length;
        this.tiles = new int[dimension][dimension];
        tilesDeepCopy(tiles, this.tiles);

        preCalculateHamming();
        preCalculateManhattan();
    }

    private void preCalculateHamming() {
        int distance = 0;
        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                if (tiles[i][j] == 0) {
                    continue;
                }

                int expectedNumber = j + (i * dimension) + 1;
                if (i == dimension - 1 && j == dimension - 1) {
                    expectedNumber = 0;
                }
                if (tiles[i][j] != expectedNumber) {
                    distance++;
                }
            }
        }
        hamming = distance;
    }

    private void preCalculateManhattan() {
        int distance = 0;
        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                if (tiles[i][j] == 0) {
                    continue;
                }

                int expectedI = tiles[i][j] / dimension;
                if (tiles[i][j] % dimension == 0) {
                    expectedI--;
                }
                int expectedJ = (tiles[i][j] % dimension) - 1;
                if (tiles[i][j] % dimension == 0) {
                    expectedJ = dimension - 1;
                }
                distance += Math.abs(i - expectedI) + Math.abs(j - expectedJ);
            }
        }
        manhattan = distance;
    }

    /**
     * @return string representation of this board
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dimension);
        stringBuilder.append("\n");
        for (int[] row: tiles) {
            for (int num: row) {
                stringBuilder.append(" ");
                stringBuilder.append(num);
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    /**
     * @return board dimension n
     */
    public int dimension() {
        return dimension;
    }

    /**
     * @return number of tiles out of place
     */
    public int hamming() {
        return hamming;
    }

    /**
     * @return sum of Manhattan distances between tiles and goal
     */
    public int manhattan() {
        return manhattan;
    }

    /**
     * @return is this board the goal board?
     */
    public boolean isGoal() {
        return hamming() == 0;
    }

    /**
     * @param y the board to be compared with
     * @return does this board equal y?
     */
    public boolean equals(Object y) {
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }

        Board that = (Board) y;
        if (that.dimension != dimension || that.manhattan != manhattan || that.hamming != hamming) {
            return false;
        }

        for (int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                if (that.tiles[i][j] != this.tiles[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * @return all neighboring boards
     */
    public Iterable<Board> neighbors() {
        // locate the element 0
        int i, j = 0;
        for (i = 0; i < dimension; ++i) {
            for (j = 0; j < dimension; ++j) {
                if (tiles[i][j] == 0) {
                    break;
                }
            }
            if (j < dimension && tiles[i][j] == 0) {
                break;
            }
        }

        LinkedList<Board> list = new LinkedList<>();

        if (i == dimension && j == dimension) {
            return list;
        }

        if (i > 0) {
            int[][] t = new int[dimension][dimension];
            tilesDeepCopy(this.tiles, t);
            int tmp = t[i][j];
            t[i][j] = t[i - 1][j];
            t[i - 1][j] = tmp;
            list.add(new Board(t));
        }

        if (i < dimension - 1) {
            int[][] t = new int[dimension][dimension];
            tilesDeepCopy(this.tiles, t);
            int tmp = t[i][j];
            t[i][j] = t[i + 1][j];
            t[i + 1][j] = tmp;
            list.add(new Board(t));
        }

        if (j > 0) {
            int[][] t = new int[dimension][dimension];
            tilesDeepCopy(this.tiles, t);
            int tmp = t[i][j];
            t[i][j] = t[i][j - 1];
            t[i][j - 1] = tmp;
            list.add(new Board(t));
        }

        if (j < dimension - 1) {
            int[][] t = new int[dimension][dimension];
            tilesDeepCopy(this.tiles, t);
            int tmp = t[i][j];
            t[i][j] = t[i][j + 1];
            t[i][j + 1] = tmp;
            list.add(new Board(t));
        }

        return list;
    }

    /**
     * @return a board that is obtained by exchanging any pair of tiles
     */
    public Board twin() {
        int[][] twin = new int[dimension][dimension];

        tilesDeepCopy(this.tiles, twin);

        // make sure to not swap tile of 0
        // locate the element 0
        int i, j;
        for (i = 0; i < dimension; ++i) {
            for (j = 0; j < dimension; ++j) {
                if (tiles[i][j] == 0) {
                    break;
                }
            }
            if (j < dimension && tiles[i][j] == 0) {
                break;
            }
        }

        if (i == 0) {
            int t = twin[1][0];
            twin[1][0] = twin[1][1];
            twin[1][1] = t;
        } else {
            int t = twin[0][0];
            twin[0][0] = twin[0][1];
            twin[0][1] = t;
        }

        return new Board(twin);
    }

    /**
     * @param source source of tiles
     * @param destination destination of tiles
     */
    private void tilesDeepCopy(int[][] source, int[][] destination) {
        int d = source.length;
        for (int i = 0; i < source.length; ++i) {
            assert source[i].length == d;
            System.arraycopy(source[i], 0, destination[i], 0, source[i].length);
        }
    }
}
