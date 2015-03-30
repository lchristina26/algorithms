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

public class Graph_Color {
    LpSolve lp;
    int num_V, k_Colors, Ncol;

    public Graph_Color() {
    }
    public int run(ArrayList<ArrayList<Integer>> g) throws LpSolveException {
        Ncol = 2;
        int[] col = new int[Ncol]; // create enough space for one row
        double[] row = new double[Ncol];
        ArrayList<ArrayList<Integer>> xCol = new ArrayList<ArrayList<Integer>>
            (num_V, ArrayList<Integer>(k_Colors));
        int num_V = g.size();
        int max_C = 0;
        //set up max number of colors = V + 1
        for (int i = 0; i < num_V; i++) {
            k_Colors = Math.max(g[i].size() + 1, k_Colors);
        }
        lp = LpSolve.makeLp(0, k_Colors);
        if (lp.getLp() == 0) {
            ret = 1; //can't construct new model
            System.out.println("ERROR: Can't construct new model\n");
        }


        ArrayList<Integer> rows = new ArrayList<Integer>();
        ArrayList<Integer> cols = new ArrayList<Integer>();
        ArrayList<Double> coeffs = new ArrayList<Integer>();

        int yCol = add_column(lp, 1);
        set_bounds(lp, yCol, 1, k_Colors); 
        set_obj(lp, yCol, 1);   //same as glp_set_obj_coef??
        set_int(lp, yCol);  //set to Integer Variable - GLP_IV

        for (int v = 0; v < num_V; v++) {
            for (int k = 0; k < k_Colors; k++) {
                xCol[v][k] = add_column(lp, 1);
                set_binary(lp, xCol[v][k]); //set to binary variable - GLP_BV
            }
        }
        // add rows to matrix -first constraint - 
        // add_constraint function in lp_solve the same???
        for (int v = 0; v < num_V; v++) {   //each vert has 1 color only
            int row_new = add_row(lp, 1);
            set_bounds(lp, row_new, 1, 1);  //fixed bounds, not sure if necessary
            for (int k = 0; k < k_Colors; k++) {
                rows.add(row_new);
                coeffs.add(1);
                cols.add(xCol[v][k]);
            }
        }
        // second constraint - lp_solve's add_constraint??
        for (int v = 0; v < num_V; v++) {
            for (int k = 0; k < k_Colors; k++) {
                int new_row = add_row(lp, 1);
                set_bounds(lp, row_new, 0,-1);
                rows.add(new_row);
                coeffs.add(1);
                cols.add(colY);
                rows.add(new_row);
                coeffs.add(-k-1);
                cols.add(x[v][k]);
            }
        }

        // Adjacent vertices must have different colors
        for (int i = 0; i < num_V; i++) {
            ArrayList<Integer> good = g[s];
            for (int j = 0; j < good.length(); j++) {
                int dest = good[j];
                if (i > dest) {
                    for (int k = 0; k < k_Colors; k++) {
                        int new_row = add_row(lp, 1);
                        set_bounds(lp, new_row, -1, 1); //upper lh in add_constraint?
                        rows.add(new_row);
                        coeffs.add(1);
                        cols.add(x[i][k]);
                        rows.add(new_row);
                        coeffs.add(1);
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
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        try {
            Scanner in = new Scanner(file);
            while(in.hasNextLine()) {
                graph.add(in.nextInt());
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            new Graph_Color().run(graph);
        }
        catch (LpSolveException e) {
            e.printStackTrace();
        }
    }
}
