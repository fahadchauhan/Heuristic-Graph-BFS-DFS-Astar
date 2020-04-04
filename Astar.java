package astar;
import java.util.LinkedList;
import java.util.Stack;

public class Astar {

    class edge{
        int dest;
        int cost;
        
        edge(int d, int c){
            dest=d;
            cost=c;
        }
    }
    
    private final int V;
    private LinkedList adj[];
    private final String[] arr = {"Arad","Zerind","Oradea","Timisoara","Sibiu","Lugoj","Mahadia","Dobreta","Craiova","Rimnicu Vilcea","Pitesti","Fagaras","Bucharest","Giurgiu","Urziceni","Hirsova","Eforie","Vaslui","Lasi","Neamt"};
    private final int[] heur = {366,374,380,329,253,244,241,242,160,193,100,176,0,77,80,151,161,199,226,234};
    int cost[] = new int[20];
    int back[]=new int[20];
    int cos=0;
    
    Astar(int v){
        V = v;
        adj = new LinkedList[v]; 
        for (int i=0; i<v; ++i) 
            adj[i] = new LinkedList(); 
    }

    void addEdge(int v,int w ,int c){
        edge node = new edge(w,c);
        adj[v].addLast(node);
    }
    
    void print(){
        for(int i=0; i<20; i++){
            System.out.print(i + " -> [ ");
            for(int j=0; j<adj[i].size(); j++){
                edge a = (edge) adj[i].get(j);
                System.out.print("(" +a.dest + "," + a.cost + ")");
            }
            System.out.println(" ]");
        }
    }
    
    void Astar(int s,int e){
        int count=0,z=0;
        boolean visited[] = new boolean[V];
 
        Stack stack= new Stack();
        visited[s]=true;
        stack.push(s);
        cost[s]=0;
        int c=0;
        if(s!=e){
            while(true){
                c=0;
                z=0;
                s = (int) stack.peek();
                for(int j=0; j<adj[s].size(); j++){
                    edge a =  (edge) adj[s].get(j);
                    int n = a.dest;

                    if (!visited[n]){
                        c += 1;
                        visited[n] = true;
                        back[z++]=cos + a.cost + heur[n];
                        
                        if(n==e){
                            stack.push(n);
                            cos += a.cost;
                            cost[n]=a.cost;
                            int p=0;
                            while(!stack.isEmpty()){
                                n = (int) stack.pop();
                                back[p++]=n;
                            }
                            print(p-1);
                            count=1;
                            break;
                        }
                        
                    }
                }
                int n = find(back,z-1);
                edge a = (edge) adj[s].get(0);
                for(int i=0; i<adj[s].size(); i++){
                    a = (edge) adj[s].get(i);
                    if(n==a.cost + cos + heur[a.dest]){
                        n=a.dest;
                        stack.push(a.dest);
                        cos += a.cost;
                        cost[a.dest] = a.cost;
                        break;
                    }
                }
                if(c==0){
                    stack.pop();
                }
                if(count==1) break;
            }
        }
        else{
            System.out.print("starting and ending points are same.\n Cost is: 0" );
        }
    }
    int find(int back[], int z){
        int min=100000,j=0;
        for(int i=0; i<=z; i++){
            if(back[i]<min){
                min=back[i];
                j=i;
            }
        }
        return min;
    }
    void print(int p){
        int i=0;
        System.out.println("");
        for(i=p; i>0; i--){
            System.out.print(arr[back[i]] + "(" + cost[back[i]] + ")" + " -> " );
        }
        System.out.println(arr[back[i]] + "(" + cost[back[i]] + ")" + "." );
        System.out.println("Cost is: " + cos);
    }
    
    public static void main(String args[]){
        Astar g = new Astar(20);

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
        g.addEdge(9,8,140);
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

        g.print();
        g.Astar(0,12);
    }

}
