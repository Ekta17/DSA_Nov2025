package dsa.topten;

import static org.junit.Assert.*;

public class NumberOfIslands {

    public static int numIslands(char[][] grid) {

        //Calculated all the neighbors including diagnols, but those are not required.
        /*
         * Adjacent of node located at [i][j]
         * [i-1][j]
         * [i][j-1]
         * [i+1][j]
         * [i][j+1]
         */

        int totalIslands = 0;

        /*//I didnt know how to see col and rows as m n are not given
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[i].length; j++){
                if(grid[i][j] == '1'){
                    //then keep up with dfs to check for all the adjacents till 0 is reached
                }else{

                }
            }
        }*/
        //Wasted some time into checking the traversal then thought of looking at it as a graph and solving it with BFS
        /*
         BFS: uses queue
         List<Character> q = new LinkedList<>();
         q.add(grid[0][0]) //root

          while(!q.isEmpty()){
          char c = q.poll();
            if(c == '1'){
                q.add(all neighbors);
            }
            //dont know how to implement BFS, need to revise this code

            // Saw the youtube video after 30 min of thinking about the problem. I am not totally solving the problem, but trying to check the solution
            //https://www.youtube.com/watch?v=ZgCZfXPo3hI
            for(traver the grid one cell at a time){
                perform dfs on each node
                increment number of islands
            }

            dfs(grid, i, j){
                if(water or visited node)
                    return
                else
                    visit adjacent nodes through dfs
                    mark grid[i][j] = visited
            }
          }

         */

        //After watching the video

        if(grid == null || grid.length == 0)
            return 0;

        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[i].length; j++){
                if(grid[i][j] == '1'){
                    dfs(grid, i, j);
                    totalIslands++;
                }
            }
        }

        return totalIslands;
    }

    private static void dfs(char[][] grid, int i, int j){
        if(    i < 0 || i >= grid.length
            || j <0 || j>=grid[i].length
            || grid[i][j] == '0'){
            return ; //Reached end or water encountered in middle
        }

        //mark current as visit by marking it as water
        grid[i][j] = '0';

        //explore adjacent nodes
        dfs(grid, i-1, j);
        dfs(grid, i, j-1);
        dfs(grid,i+1, j);
        dfs(grid, i,j+1);
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        assertEquals(1, numIslands(grid));

        grid = new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        assertEquals(3, numIslands(grid));

    }
}
