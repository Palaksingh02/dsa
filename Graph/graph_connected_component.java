import java.io.*;
import java.util.*;

public class graph_connected_component {
   static class Edge {
      int src;
      int nbr;
      int wt;

      Edge(int src, int nbr, int wt) {
         this.src = src;
         this.nbr = nbr;
         this.wt = wt;
      }
   }
   
   public static void gcc(ArrayList<Edge>[] graph , ArrayList<ArrayList<Integer>> comps){
       boolean[] vis = new boolean[graph.length];
       for(int i = 0 ; i<graph.length ; i++){
           if(!vis[i]){
               ArrayList<Integer> single = new ArrayList<>();
               get_single_components(graph , i , vis , single);
               comps.add(single);
           }
       }
   }
   
   public static void get_single_components(ArrayList<Edge>[] graph , int src , boolean[] vis , ArrayList<Integer> single){
       single.add(src);
       vis[src] = true;
       for(Edge e : graph[src]){
           int nbr = e.nbr;
           if(!vis[nbr]){
               get_single_components(graph , nbr , vis , single);
           }
       }
   }

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      int vtces = Integer.parseInt(br.readLine());
      ArrayList<Edge>[] graph = new ArrayList[vtces];
      for (int i = 0; i < vtces; i++) {
         graph[i] = new ArrayList<>();
      }

      int edges = Integer.parseInt(br.readLine());
      for (int i = 0; i < edges; i++) {
         String[] parts = br.readLine().split(" ");
         int v1 = Integer.parseInt(parts[0]);
         int v2 = Integer.parseInt(parts[1]);
         int wt = Integer.parseInt(parts[2]);
         graph[v1].add(new Edge(v1, v2, wt));
         graph[v2].add(new Edge(v2, v1, wt));
      }

      ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
      
      gcc(graph,comps);

      System.out.println(comps);
   }
}