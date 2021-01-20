/*
 * Name       :Stefan Todorovic
 * StudentNo:   300118046
 */
import java.awt.*;
import java.util.*;
import java.io.*;
public class GaleShapley {

    static Stack sue = new Stack();
    static int n;
    static int[] employers;
    static String[] e_names;
    static int[] students;
    static String[] s_names;
    static int[][] a;
    static PriorityQueue<Pair>[] pq;

    public static void initialize(String fileName) {
        try{
        // Imports file and splits by line
        String scannedFile = "";
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine())
            scannedFile += sc.nextLine() +"\n";
        sc.close();
        String lines[] = scannedFile.split("\\r?\\n");
        //first line is number of employers and students
        n = Integer.parseInt(lines[0]);
        // initialize all needed arrays and queues
        employers = new int[n];
        e_names = new String[n];
        students = new int[n];
        s_names = new String[n];
        pq = new PriorityQueue[n];
        a = new int[n][n];
        int y=0;
        //fills employers and students with -1, prepares pq and copys names to arrays
        for(int i =0; i < n;i++){
            employers[i] = -1;
            students[i] = -1;
            sue.push(i);
            pq[i] = new PriorityQueue<Pair>();
            e_names[i] = lines[1+i];
            s_names[i] = lines[1+i+n];
        }
        //Fills 2d Array and pq
        for(int i = n+n+1; i< n+n+n+1;i++){
            String[] rankings = lines[i].split("\\s+");
            for(int x =0; x < rankings.length;x++){
                a[x][y] = (Integer.parseInt(rankings[x].split(",")[1])-1);
                Pair p = new Pair((Integer.parseInt(rankings[x].split(",")[0])-1),x);
                pq[y].add(p);
            }
            y++;
        }}
        catch (IOException e){
            e.printStackTrace();

        }
    }
    public static void execute(){
        // copied psudocode and converted java
        while(!(sue.isEmpty())){
            int e = (int) sue.pop();
            int s = pq[e].poll().getValue();
            int eTemp = students[s];
            if(students[s] == -1 ){
                students[s] = e;
                employers[e] = s;
            }else if(a[s][e] < a[s][eTemp]){
                students[s] = e;
                employers[e] = s;
                employers[eTemp] = -1;
                sue.push(eTemp);
            }else{
                sue.push(e);
            }


}
    }
    public static void save(String filename){
        // New file writer, writes all matches and saves file
        try{
            FileWriter fw=new FileWriter(filename);
            for(int i = 0; i < n; i++){
                fw.write(("Match "+i+":"+ e_names[i] + " - " + s_names[employers[i]] +"\n"));
        }
        fw.close();


} catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        System.out.print("Please entre a file name to be matched: ");
        String fileName = kb.nextLine();
        kb.close();
        initialize(fileName);
        execute();
        save("matches_"+fileName);
        System.out.println("Matches saved to: matches_"+fileName);



    }
}
