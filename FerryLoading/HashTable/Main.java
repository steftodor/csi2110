/*
Assignment Two: The ferry loading problem.
         class: CSI 2110
          name: Stefan Todorovic
            id: 300118046
*/


// imports
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;

public class Main {
    // initialize variables
    private int bestK, n, L;// , number of cars, Length of ferry in ( cm converted from m )
    private int[] currX, bestX, length;// , solution, length of cars waiting to board ( in cm ),
    private HashMap<Integer, Boolean> hashMap;// hashmap for visted
    /*
     * carsIn represents the length of the cars in the queue that are ready to board the boat
     * lengthB represents length of the boat
     *
     * @param carsIn
     * @param lengthB
     */
    public Main(ArrayList<Integer> carsIn, int lengthB){
      // setting variables based on length of ship and input
        L = lengthB;
        n = carsIn.size();
        length = new int[n];
        bestK = -1;
        currX = new int[n];
        bestX = new int[n];
        hashMap = new HashMap<Integer, Boolean>(2000);// set hashmap inital capacity
        // Copys from carsIn arraylist into length array
        for(int i = 0; i < n; i++)
            length[i] = carsIn.get(i);
        BacktrackSolve(0, L);

      // prints display output for solution
        System.out.println(bestK);
        for(int i = 0; i < bestK; i++){
            if(bestX[i] == 0)
                System.out.println("port");
            else if(bestX[i] == 1)
                System.out.println("starboard");
        }
    }

    public static void main(String[] args) {
      // initializing Scanner
        Scanner sc = new Scanner(System.in);
        int numOfCases = sc.nextInt();// takes number of cases (first int in input)

      // creates variables used in method
        int i = 0;
        int input;
        int lengthOfBoat;
        int inp;
        ArrayList<Integer> cars = new ArrayList<Integer>();

        while(i < numOfCases){// while there are cases remaing
            lengthOfBoat = sc.nextInt() * 100;// take the length of the boat in m and convert to cm
            cars.clear(); // clear arraylist
           while((inp = sc.nextInt()) != 0) // while the input from the array is not zero ( as 0 indicates the end of the case)
            cars.add(inp);// add the new car length to the arrayList

            if(i>0)// if this is not the first case at a space for formatting the print
            System.out.println("");

            Main m = new Main(cars, lengthOfBoat);
            i++;
        }
    }

    /*
     * Used to set the hash key
     * @param varK
     * @param varS
     */
    public void setHash(int varK, int varS){
      hashMap.put((varK+varS)%2000, true);
    }

    /*
     * Used to verify if the location has been visited
     * @param varK
     * @param varS
     */
    public boolean verifyHash(int varK, int varS  ){
      if (hashMap.containsKey((varK+varS)%2000))
          return false;
      else
        return true;
    }


    /*
     * currK represents the number of cars that have currently been added on the boat
     * currS represents the number amount of space that remains on the left side of the boat
     *
     * @param currK
     * @param currS
     */


    public void BacktrackSolve(int currK, int currS){
        if( currK > bestK ){
            bestK = currK;
            bestX = Arrays.copyOf(currX, currX.length);
        }
        // if true, there are cars left to consider
        if(currK < n ){

            // if next car can be added to the left and (cuurK + 1, currS - length[currK] has not been visited
            if(((currS - length[currK])>= 0)&&(verifyHash((currK + 1),(currS - length[currK])))){
                currX[currK] = 1;
                int newS = (currS - length[currK]);
                BacktrackSolve((currK + 1), newS);
                setHash((currK + 1), newS);
            }

            // if next car can be added to the right and (cuurK + 1, currS] has not been visited
            if (verifyHash((currK + 1), currS)){
                int sumRight = 0;
                // sum all cars on right side ( 0 = port = right)
                for(int i = 0; i < currK; i++){
                    if(currX[i] == 0)
                  sumRight += length[i];}
                // length of boat - sum of right - new car should be able to fit >= 0
                if((L - sumRight - length[currK]) >= 0) {
                    currX[currK] = 0;
                    BacktrackSolve(currK + 1, currS);
                    setHash((currK + 1), currS);
                }
            }
        }
    }
}
