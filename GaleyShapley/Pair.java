/*
 * Name       :Stefan Todorovic
 * StudentNo:   300118046
 */
public class Pair implements Comparable<Pair>{
    private int key;
    private int value;


    int getKey(){
        return key;
    }

    int getValue(){
        return value;
    }

    public Pair(int k, int v){
        key = k;
        value = v;
    }
    @Override
    public String toString() {
        return "[" + getKey() + ", " + getValue() + "]";
    }

    @Override
    public int compareTo(Pair o) {
        if(this.key == o.getKey()){
            return 0;
        }
        else if(this.key < o.getKey()){
            return -1;
        }else{
            return 1;
        }
    }
}
