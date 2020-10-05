// Writen by Stefan Todorovic for CSI2110 Assignment 2

import java.util.*;
public class GreatStack{
  static Stack<Integer> values = new Stack(); // Where the pushed values are stored
  static Stack<Integer> max_values = new Stack(); // Used as supporting stack for max()

public static void main(String[] Args){

  /* For following test the expected output is
  1  < = max() after push of 1
  3  < = max() after push of 3
  8  < = max() after push of 8
  8  < = max() after push of 6
  10 < = max() after push of 4,7 and then 10
  8  < = max() after pop of 10
  3  < = max() after pop of 7, 4, 6, 8
  and then an error for empty stack < = from extra pop()
*/
  push(1);
  System.out.println(max());
  push(3);
  System.out.println(max());
  push(8);
  System.out.println(max());
  push(6);
  System.out.println(max());
  push(4);
  push(7);
  push(10);
  System.out.println(max());
  pop();
  System.out.println(max());
  pop();
  pop();
  pop();
  pop();
  System.out.println(max());
  pop();
  pop();
  System.out.println(max());

}

//max function returns max value as int
public static int max(){
  //Throws error for empty stack
  if(max_values.empty()){
    throw new EmptyStackException();
  }
  return max_values.peek();
}

//pop function returns last value pushed into stack
public static int pop(){
  if(values.empty()){
    throw new EmptyStackException();
  }
  // if top item in values is max_values pop from max_values
  if(max_values.peek() == values.peek()){
    max_values.pop();
  }
  return values.pop();
}

//push function accepts int i
public static void push(int i){
  // if max_values is empty push i to prevent error from peeking empty stack
  if(max_values.empty()){
    max_values.push(i);
  }
  //if i is greater or equal to current max, push i to max
  else if(i >= max_values.peek()){
    max_values.push(i);
  }
  //push i to values regardless of if i is max or not
  values.push(i);
}

}
