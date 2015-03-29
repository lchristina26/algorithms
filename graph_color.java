/* Graph Coloring Program using lp_solve 
*   by Monica and Leah
*
*   lp_solve example Java program: 
*   http://lpsolve.sourceforge.net/5.5/formulate.htm#Java
*
*   glp example graph coloring program (glp is similar to lp_solve:
*   http://felix.abecassis.me/2012/11/graph-coloring-with-glpk/
*/

import lpsolve.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class graph_color {
    LpSolve lp;
    int num_V, k_Colors;

    void graph_color(List<int> g) {
        int num_V = g.letgth;
        int max_C = 0;
        for (int i = 0; i < num_V; i++) {
            k_Colors = Math.max(g[i].length() + 1, k_Colors);
        }
        int colBound = lp.makeLp(0, Ncol);

    }
    public int run() throws LpSolveException {
        int[] col = new int[Ncol]; // create enough space for one row
        double[] row = new double[Ncol];
        ArrayList<ArrayList<Integer>> xCol = new ArrayList<ArrayList<Integer>>
                                (num_V, ArrayList<Integer>(k_Colors));
        ArrayList<Integer> rows(1,0);
        ArrayList<Integer> cols(1,0);
        ArrayList<Double> coeffs(1,0);

        lp = LpSolve.makeLp(0, Ncol);
        if (lp.getLp() == 0)    //not able to construct new model
            ret = 1;    
        lp.setColName(1,"x");
        lp.setColName(2,"y");
        lp.setAddRowMode(true); //build row by row
        
        int yCol = add_column(lp, 1);
        set_bounds(lp, yCol, 1, k_Colors); 
        set_obj(lp, yCol, 1);   //same as glp_set_obj_coef??
        set_int(lp, yCol);  //set to Integer Variable - GLP_IV
        
        for (int v = 0; v < num_V; v++) {
            for (int k = 0; k < k_Colors; k++) {
                xCol[v][k] = add_column(lp, 1);
                set_binary(lp, colX[v][k]); //set to binary variable - GLP_BV
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
                cols.add(x[v][k]);
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
                    cols.add(x[dest][k]);
                }
            }
        } 
    }
    // Need to load sparce matrix into lp here//

    //Print results
    double colsTot = 
    System.out.println("Colors: " + colsTot + "\n");
    for (int i = 0; i < num_V; i++) {
        System.out.println(i + ": "); 
        System.out.println(
    }

}
    public static void main(String[] args) {
        try {
            // Create a problem with 4 variables and 0 constraints
            LpSolve solver = LpSolve.makeLp(0, 4);

            // add constraints
            solver.strAddConstraint("3 2 2 1", LpSolve.LE, 4);
            solver.strAddConstraint("0 4 3 1", LpSolve.GE, 3);

            // set objective function
            solver.strSetObjFn("2 3 -2 3");

            // solve the problem
            solver.solve();

            // print solution
            System.out.println("Value of objective function: " + solver.getObjective());
            double[] var = solver.getPtrVariables();
            for (int i = 0; i < var.length; i++) {
                System.out.println("Value of var[" + i + "] = " + var[i]);
            }

            // delete the problem and free memory
            solver.deleteLp();
        }
        catch (LpSolveException e) {
            e.printStackTrace();
        }
    }

}

