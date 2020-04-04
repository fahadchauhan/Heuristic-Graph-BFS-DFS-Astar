package BFS;
import java.util.*; 

/*
0 - Arad
1 - Zerind
2 - Oradea
3 - Timisoara
4 - Sibiu
5 - Lugoj
6 - Mahadia
7 - Dobreta
8 - Craiova
9 - Rimnicu Vilcea
10 - Pitesti
11 - Fagaras
12 - Bucharest
13 - Giurgiu
14 - Urziceni
15 - Hirsova
16 - Eforie
17 - Vaslui
18 - Lasi
19 - Neamt
*/
public class BFS{
    
    class edge{
        int dest;
        int cost;
        
        edge(int d, int c){
            dest=d;
            cost=c;
        }
        
        @Override
        public String toString(){
            return "(" + dest + "," + cost + ")";
        }
    }
    
    private final int V;
    private LinkedList adj[];
    private final String[] arr = {"Arad","Zerind","Oradea","Timisoara","Sibiu","Lugoj","Mahadia","Dobreta","Craiova","Rimnicu Vilcea","Pitesti","Fagaras","Bucharest","Giurgiu","Urziceni","Hirsova","Eforie","Vaslui","Lasi","Neamt"};
    private int par[]=new int[20];
    int cost=0;
    
    BFS(int v){
        V = v; 
        adj = new LinkedList[v]; 
        for (int i=0; i<v; ++i) 
            adj[i] = new LinkedList(); 
    }

    void addEdge(int v,int w ,int c){
        edge node = new edge(w,c);
        adj[v].addLast(node);
    }

    void Bfs(int s,int e){
        int st =s;
        boolean visited[] = new boolean[V];

        LinkedList queue = new LinkedList(); 

        visited[s]=true; 
        queue.addLast(s);

        while (!queue.isEmpty()){
            s = (int) queue.poll();
            System.out.print(arr[s] + " -> " );
             
            for(int j=0; j<adj[s].size(); j++){
                edge a =  (edge) adj[s].get(j);
                int n = a.dest;
                if (!visited[n]){
                    visited[n] = true;
                    cost += a.cost;
                    queue.addLast(n);
                    par[n]=s;
                    if(n==e){
                        System.out.print(arr[n] + "." );
                        backtrack(n,st);
                        queue.clear();
                        break;
                    }
                }
            }
        }
    }
    
    void backtrack(int s, int e){
        int n=s, k=0, m=0;
        String back[] = new String[20];
        System.out.println("");
        while(n!=e){
            back[k++]=arr[n];
            n=par[n];
        }
        back[k++]=arr[n];

        for(m=k-1; m>0; m--){
            System.out.print(back[m] + "->" );
        }
        
        System.out.println(back[m] + "." );
        System.out.println("Cost is: " + cost);
    }
    
    
    @Override
    public String toString(){
        String result = "";
        for(int i=0; i<20;i++){
            result += i + " -> " + adj[i] + "\n";
        }
        return result;
    }

    public static void main(String args[]){
            BFS g = new BFS(20);

            g.addEdge(0,1,75);
            g.addEdge(0,3,118); 
            g.addEdge(0,4,140); 
            g.addEdge(1,0,75); 
            g.addEdge(1,2,71); 
            g.addEdge(2,1,71);
            g.addEdge(2,4,151);
            g.addEdge(3,0,118);
            g.addEdge(3,5,111);
            g.addEdge(4,2,151);
            g.addEdge(4,0,140);
            g.addEdge(4,11,99);
            g.addEdge(4,9,80);
            g.addEdge(5,3,111);
            g.addEdge(5,6,70);
            g.addEdge(6,5,70);
            g.addEdge(6,7,75);
            g.addEdge(7,6,75);
            g.addEdge(7,8,120);
            g.addEdge(8,9,140);
            g.addEdge(8,10,138);
            g.addEdge(9,4,80);
            g.addEdge(9,10,97);
            g.addEdge(10,8,138);
            g.addEdge(10,9,97);
            g.addEdge(10,12,101);
            g.addEdge(11,4,99);
            g.addEdge(11,12,211);
            g.addEdge(12,10,101);
            g.addEdge(12,11,211);
            g.addEdge(12,13,90);
            g.addEdge(12,14,85);
            g.addEdge(13,12,90);
            g.addEdge(14,12,85);
            g.addEdge(14,15,98);
            g.addEdge(14,17,142);
            g.addEdge(15,14,98);
            g.addEdge(15,16,86);
            g.addEdge(16,15,86);
            g.addEdge(17,14,142);
            g.addEdge(17,18,92);
            g.addEdge(18,17,92);
            g.addEdge(18,19,87);
            g.addEdge(19,18,87);
            
            System.out.println(g); 

            g.Bfs(0,12);
    }
}