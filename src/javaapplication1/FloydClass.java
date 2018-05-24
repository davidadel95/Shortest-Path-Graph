package javaapplication1;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author john
 */
public class FloydClass {

    final static int INF = 2147483647, V = 22;
    int dist[][];
    public ArrayList FinalPath;

    void floydWarshall(int graph[][],int seq[][])
    {
        int result;
        dist = new int[V][V];
       
        
        
        
        int i, j, k;

        /* Initialize the solution matrix same as input graph matrix.
           Or we can say the initial values of shortest distances
           are based on shortest paths considering no intermediate
           vertex. */
        System.out.println("Distance matrixxxx");
        for (i = 0; i < V; i++){
            for (j = 0; j < V; j++){
                dist[i][j] = graph[i][j];
                System.out.print(dist[i][j]+ " ");
            }
            System.out.println(" ");
        }
        /* Add all vertices one by one to the set of intermediate
           vertices.
          ---> Before start of a iteration, we have shortest
               distances between all pairs of vertices such that
               the shortest distances consider only the vertices in
               set {0, 1, 2, .. k-1} as intermediate vertices.
          ----> After the end of a iteration, vertex no. k is added
                to the set of intermediate vertices and the set
                becomes {0, 1, 2, .. k} */
        for (k = 0; k < V; k++)
        {
            // Pick all vertices as source one by one
            for (i = 0; i < V; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < V; j++)
                {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if(dist[i][k]==2147483647||dist[k][j]==2147483647){
                    result=2147483647;
                    }
                    else {
                    result = dist[i][k] + dist[k][j];
                    }
                    
                    if (result < dist[i][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                        System.out.println(dist[i][j]);
                        seq[i][j]=k;
                    }
                }
            }
        }

        // Print the shortest distance matrix
        printSolution(dist,seq);
    }

    void printSolution(int dist[][],int seq[][])
    {
        System.out.println("Following matrix shows the shortest "+
                         "distances between every pair of vertices");
        for (int i=0; i<V; ++i)
        {
            for (int j=0; j<V; ++j)
            {
                if (dist[i][j]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j]+"   ");
            }
            System.out.println();
        }
        
        
        
               
        for (int i=0; i<V; ++i)
        {
            for (int j=0; j<V; ++j)
            {
                if (dist[i][j]==INF)
                    seq[i][j]=INF;
               
            }
           
        }
    }
    
    boolean GetFinalPath(int Start,int End,int[][] seq){
    FinalPath = new ArrayList<Integer>();
    boolean Flag = true;
    FinalPath.clear();
    int NewEnd = End;
    int Counter=0;
    while(Flag== true){
    if(seq[Start][End]==INF){
    Flag=false;
    }
    else {
        if (Counter==0){
        FinalPath.add(NewEnd);
        Counter++;
        }
        
    if(seq[Start][NewEnd]!=NewEnd){
    FinalPath.add(seq[Start][NewEnd]);
    NewEnd=seq[Start][NewEnd];
    }
    else {
    Flag=false;
    FinalPath.add(Start);
    return true;
    }
    
    }

    
   }
    return false;
}
}
