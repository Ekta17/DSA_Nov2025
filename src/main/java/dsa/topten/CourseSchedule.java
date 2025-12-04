package dsa.topten;

import java.util.*;

import static org.junit.Assert.assertFalse;

public class CourseSchedule {

    //Reading the question, I was quickly able to relate it to graphs, and there shouldnt be cycles in the graph in order for us to finish all the courses
    // was thinking if we need to use topological sort - such that to get the order of courses to take and if a cycle is found then return false
    //But I am not sure how to implement topological sort like write in code
    // I am thinking like start with a node (i,j) - find the outdegree and indgree of nodes, nodes are all the i and j and [i,j] is an edge where [i,j] means i's outdegree is 1 and j's indegree is 1
    // we start with a node with outgree as 0, but then what to do if two nodes have same outgrees as we will need to check individual dependencies in this case then
    //after 15 min, i looked into how topological alogrithm works, like with kahn and DFS. but I still dont know how to get all the nodes in the graph first, for that I will have to  traverse through the entire 2d array which is O(n2)
    public static boolean canFinish(int numCourses, int[][] prerequisites) {

        /*
        Attempt 1:
        List<List<Integer>> graph = new ArrayList<>(); // Added this after looking into different implementation of graphs in java
        for(int i =0; i< numCourses; i++){
            graph.add(new ArrayList<>());
        }

        Set<Integer> courses = new HashSet<>(numCourses);
        //Kahn's algorithm:
        //first find indegree for each course
        Map<Integer, Integer> indegree = new HashMap<>();

        for(int i = 0; i< prerequisites.length; i++){
            courses.add(prerequisites[i][0]);
            courses.add(prerequisites[i][1]);
            indegree.put(prerequisites[i][1], indegree.getOrDefault(prerequisites[i][1], 0)+1);
            graph.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        System.out.println(graph);
        System.out.println(courses);
        System.out.println(indegree);

        Queue<Integer> q = new LinkedList<>();
        for(Integer course: courses){
            if(!indegree.containsKey(course)){
                q.add(course); // All the courses that should be taken last
            }
        }

        System.out.println(q);

        while(!q.isEmpty()){
            //right now this will have 1, but how to find the neighbour of 1?
            //At this point I started at 9:11 and now its 9:47 -> I am checking for the solution now
            //Looking at the solution, I see that my approach was correct to use topological sort but I am stuck at converting 2d array to graph and checking all the neighbors
            //Looked into different implementations of a graph in java - adjacency list and adjacency matrix
            // now that I know how to represent a graph and I have converted it into adjacency list, now lets try to iterate and work
            int currentCourse = q.poll();
            // I give up, its 10:22 and I have looked into the solution and I am tried and still unable to see how to implement this, like I know but I am tired and dont want to spend more time on this , need to take a break.
        }




        return true;*/

        //Attempt 2:
        /*
         1. find unique nodes
         2. initialize empty adj list for n node
         3. fill out indegree and adj list
         4. add to queue all nodes that have indegree as 0
         5. loop till queue is empty
                get a node from queue
                add the node to order
                for each neighbor of the node from the adj list:
                    decrease indegree for neighbor
                    if (indegree == 0) -> add this neighbor to queue
          6.  if size of order == number of nodes -> then no cycle else cycle
         */

        Set<Integer> nodes = new HashSet<>();
        List<List<Integer>> adjList = new ArrayList<>();


        // find unique nodes
        for(int i = 0 ; i < prerequisites.length; i++){
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            //Add unique nodes
            nodes.add(a);
            nodes.add(b);
        }

        //initialize adj list
        for(int i = 0; i<nodes.size(); i++){
            adjList.add(new ArrayList<>());
        }

        // fill out indegree and adj list for each node
        int[] indegree = new int[nodes.size()];
        for(int i = 0; i< prerequisites.length; i++){
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            //since this directional so there is only one edge from b to a
            adjList.get(b).add(a);
            //add to indegree
            indegree[i]++;
        }

        //Add nodes to the queue that have indegree as 0
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i< indegree.length; i++){
            if(indegree[i] == 0)
                queue.add(i);
        }

        //Loop through queue
        List<Integer> order = new ArrayList<>();
        while(!queue.isEmpty()){
            int n = queue.poll();
            order.add(n);
            for(int neighbor: adjList.get(n)){
                indegree[neighbor]--;
                if(indegree[neighbor] == 0)
                    queue.add(neighbor);
            }
        }

        //Check if all nodes are covered
        if(order.size() == nodes.size())
            return true;

        return false;
    }

    public static void main(String[] args) {
        int numCourses = 3;
        int[][] prerequisites = new int[][] {
                {1,0},
                {0, 2},
                {1, 2}
        };

        assertFalse(canFinish(numCourses, prerequisites));
    }
}
