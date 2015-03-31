/* Graph Coloring Program using lp_solve 
   va arraylist*   by Monica and Leah
 *
 *   lp_solve example Java program: 
 *   http://lpsolve.sourceforge.net/5.5/formulate.htm#Java
 *
 *   glp example graph coloring program (glp is similar to lp_solve:
 *   http://felix.abecassis.me/2012/11/graph-coloring-with-glpk/
 *   G = (V,E)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import lpsolve.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class colorGraph {
    LpSolve lp;
    int num_V, k_Colors, Ncol,ret;

    public colorGraph() {
    }
    public int run(ArrayList<Integer[]> g) throws LpSolveException {
        Ncol = 2;
        ArrayList<Integer> rows, cols, cfs;
        int[] col = new int[Ncol]; // create enough space for one row
        double[] row = new double[Ncol];
        double dub = 1.0;
        int num_V = g.size();
        int max_C = 0;
        //set up max number of colors = V + 1
        for (int i = 0; i < num_V; i++) {
            k_Colors = Math.max(g.size() + 1, k_Colors);
        }
        int xCol[][] = new int[num_V][k_Colors];
        lp = LpSolve.makeLp(0, k_Colors);
        if (lp.getLp() == 0) {
            ret = 1; //can't construct new model
            System.out.println("ERROR: Can't construct new model\n");
        }

        int yCol = lp.addColumn(1);
        lp.setBounds(yCol, 1, k_Colors); 
        lp.setObj(yCol, 1);   //same as glp_set_obj_coef??
        lp.setInt(yCol, true);  //set to Integer Variable - GLP_IV

        for (int v = 0; v < num_V; v++) {
            for (int k = 0; k < k_Colors; k++) {
                xCol[v][k] = lp.addColumn(1);
                lp.setBinary(xCol[v][k], true); //set to binary variable - GLP_BV
            }
        }
        // add rows to matrix -first constraint - 
        // add_constraint function in lp_solve the same???
        for (int v = 0; v < num_V; v++) {   //each vert has 1 color only
            int row_new = lp.addRow(1);
            lp.setBounds(row_new, 1, 1);  //fixed bounds, not sure if necessary
            for (int k = 0; k < k_Colors; k++) {
                rows.add(row_new);
                cfs.add(1);
                cols.add(xCol[v][k]);
            }
        }
        // second constraint - lp_solve's add_constraint??
        for (int v = 0; v < num_V; v++) {
            for (int k = 0; k < k_Colors; k++) {
                int new_row = lp.addRow(1);
                lp.setBounds(new_row, 0,-1);
                rows.add(new_row);
                cfs.add(1);
                cols.add(yCol);
                rows.add(new_row);
                cfs.add(-k-1);
                cols.add(xCol[v][k]);
            }
        }

        // Adjacent vertices must have different colors
        for (int i = 0; i < num_V; i++) {
            Integer good[] = g.get(i);
            for (int j = 0; j < good.length; j++) {
                int dest = good[j];
                if (i > dest) {
                    for (int k = 0; k < k_Colors; k++) {
                        int new_row = lp.addRow(1);
                        lp.setBounds(new_row, -1, 1); //upper lh in add_constraint?
                        rows.add(new_row);
                        cfs.add(1);
                        cols.add(xCol[i][k]);
                        rows.add(new_row);
                        cfs.add(1);
                        cols.add(xCol[dest][k]);
                    }
                }
            } 
        }
        // Need to load sparce matrix into lp here//


        //Print results
        lp.getVariables(row);
        for (int i = 0; i < k_Colors; i++) {
            System.out.println("Node " + i + row[i]); 
        }

    }
    public static void main(String[] args) {
        File file = new File("graph.txt");
        //I am not reading this in correctly...needs to be fixed
        ArrayList<Integer[]> graph = new ArrayList<Integer[]>();
        try {
            @SuppressWarnings("resource")
			Scanner in = new Scanner(file);
            while(in.hasNextLine()) {
            	Integer [] line = {in.nextInt(), in.nextInt()};
                graph.add(line);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            new colorGraph().run(graph);
        }
        catch (LpSolveException e) {
            e.printStackTrace();
        }
    }
}