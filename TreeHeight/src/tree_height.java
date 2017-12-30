import java.util.*;
import java.io.*;

public class tree_height {
	boolean debug = false;
	int numOps = 0;
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class TreeHeight {
		int n;
		int parent[];
		int vDepth[];
		Integer children[][];
		ArrayList<Integer>[] childrenArray;
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		int computeHeight() {
                        // Replace this code with a faster implementation
			int maxHeight = 0;
			for (int vertex = 0; vertex < n; vertex++) {
				int height = 0;
				for (int i = vertex; i != -1; i = parent[i])

					
					/////////////////////////////////
					if(debug){
						numOps++;
					}
					
					/////////////////////////////////

				
					height++;
				maxHeight = Math.max(maxHeight, height);
			}
			return maxHeight;
		}
		
		int computeHeightFaster(){
			int maxHeight = 0;
			children = new Integer[n][2];
			childrenArray = new ArrayList[n];
			vDepth = new int[n];
			int currentParent;
			for (int vertex = 0; vertex < n; vertex++) {
				
				/////////////////////////////////
				if(debug){
					numOps++;
				}
				
				/////////////////////////////////
				
				currentParent = parent[vertex];
				if(currentParent == -1)
					continue;
				
				if(children[currentParent][0]==null){
					children[currentParent][0] = vertex;
				} else {
					children[currentParent][1] = vertex;
				}
			}
			for(int vertex = 0; vertex<n; vertex++){
				if(children[vertex][0] == null){
					
					/////////////////////////////////
					if(debug){
						numOps++;
					}
					
					/////////////////////////////////
					
					
					
					int height = 0;
					for (int i = vertex; i != -1; i = parent[i])
						height++;
					maxHeight = Math.max(maxHeight, height);					
				}
			}
			
			return maxHeight;
		}
		
		
		int computeHeightEvenFaster(){
			int maxHeight = 0;
			children = new Integer[n][2];
			childrenArray = new ArrayList[n];

			
			/////////////////////////////////
			if(debug){
				System.out.printf("Set childrenArray to a length of %d%n",childrenArray.length );
			}
			
			/////////////////////////////////

			
			vDepth = new int[n];
			int currentParent;
			int topLevel = 0;
			
			for (int vertex = 0; vertex < n; vertex++) {
				
				/////////////////////////////////
				if(debug){
					numOps++;
				}
				
				/////////////////////////////////
				
				currentParent = parent[vertex];

				
				
				//////////////////////////////////
				if(debug){
					System.out.printf("currentParent of vertex %d is %d%n", vertex, currentParent);
				}
				
				//////////////////////////////////
				
				
				
				
				if(currentParent == -1){
					topLevel = vertex;
					
					
					//////////////////////////////////
					if(debug){
						System.out.printf("setting topLevel to %d%n", vertex);
					}
					
					//////////////////////////////////
					
					
					continue;
				}
				
				if(childrenArray[currentParent] == null)
					childrenArray[currentParent] = new ArrayList<Integer>();
				
				childrenArray[currentParent].add(vertex);
			}
			maxHeight = setChildDepth(topLevel, 1);
			
			
			
			return maxHeight;
		}
		
		private int setChildDepth(int vertex, int depth){
			
			//////////////////////////////////
			if(debug){
				System.out.printf("setting depth of vertex %d to %d%n", vertex, depth);
				System.out.printf("length of vDepth is%d%n", vDepth.length);
				System.out.printf("vDepth[0] is %d %n", vDepth[0]);
			}
			
			//////////////////////////////////
			
			
			vDepth[vertex] = depth;
			int rtrn = depth;
				if(childrenArray[vertex]!=null){
					for(int i=0;i<childrenArray[vertex].size();i++){
		
						/////////////////////////////////
						if(debug){
							numOps++;
							
							System.out.printf("null check on children[%d][%d] %n",vertex, i);
						}
				
					/////////////////////////////////
					
					
				
					rtrn = Math.max(
						rtrn,
						setChildDepth(childrenArray[vertex].get(i), depth + 1)
						);
				}
			}
			return rtrn;
		}
	}
	

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		//System.out.println(tree.computeHeight());
		//System.out.println(tree.computeHeightFaster());
		System.out.println(tree.computeHeightEvenFaster());
		
		/////////////////////////////////
		if(debug){
			System.out.printf("Number of operations %d%n", numOps);
		}
		
		/////////////////////////////////
		
	}
}
