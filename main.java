/*
CISC 4090
Dr. Frank Hsu
Theory of Computation Final Project: Push-down Automata 
By: Winsor Tse, Robert Riccelli, Alexander Plaza, Albert Ezizov, Md Rahman 
*/

import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;




public class pushdownautomata{

    public static void main(String args[]){

        //LinkedHashMap stores the rules for the PDA, where the left is the state and the right is the new state/rule number
        LinkedHashMap<ArrayList<String>,ArrayList<String>> map = new LinkedHashMap<ArrayList<String>,ArrayList<String>>();

        //common strings used in each column of the PDA
        String[] first_column = {"p","q","qa","qb"};
        String[] second_column = {"a","b","$","S"};
        String[] third_column = {"q","qa","q$","qa","qb"};
        String[] fourth_column = {"S","aSb"};
        String[] fifth_column = {"1","2","3","4","5","6","7","8"};

        //fillHashMap fills the hashmap based on given strings
        //first row
        fillHashMap(map, first_column[0], "", third_column[0], fourth_column[0], fifth_column[0]);
        //second row
        fillHashMap(map, first_column[1], second_column[0], third_column[1], "", fifth_column[1]);
        //thrid row
        fillHashMap(map, first_column[2], second_column[0], third_column[0], "", fifth_column[2]);
        //fourth row
        fillHashMap(map, first_column[1], second_column[1], third_column[4], "", fifth_column[3]);
        //fifth row
        fillHashMap(map, first_column[3], second_column[1], third_column[0], "", fifth_column[4]);
        //sixth row
        fillHashMap(map, first_column[1], second_column[2], third_column[2], "", fifth_column[5]);
        //seventh row
        fillHashMap(map, first_column[2], second_column[3], third_column[3],  fourth_column[1], fifth_column[6]);
        //eigth row
        fillHashMap(map, first_column[3], second_column[3], third_column[4],  "", fifth_column[7]);

        //measures the number of a's and b's to match userinput
        int n=0;
        //stores the testcases to be matched with userinput
        ArrayList<String> testcase = new ArrayList<String>();
        //fills the ArrayList of testcases
        filltestcases(testcase);
        System.out.println(testcase);

        //takes in input from user
        Scanner myObj = new Scanner(System.in);
        System.out.println("Input a test case:");
        String temp = myObj.nextLine();

        //checks for a valid input that matches the testcases
        for(int i=0; i<testcase.size(); i++){
            //if the input matches the testcase
            if(temp.equals(testcase.get(i))){
                //store the number of occurances of a and b, and break
                n = i +2;
                break;
            }
            if(i==8){
                //once it reaches the maximum length, exit the program
                System.out.println(temp + "is not in test cases exiting program");
                System.exit(0);
            }
        }

        print_output("Step", "State", "Input", "Stack", "Rule", "R used", n);


        //keeps track of step number
        int stepnumber = 0;
        //starting state, also keeps track of current state
        String state = "p";
        String charA = "a";
        String charB = "b";
        String dollar = "$";
        //current input string entered by user, repeats the characters based on the input
        String inputstring = charA.repeat(n) + charB.repeat(n) + dollar;
        //string stack1, keeps track of the remaining input string we have left
        //updates out of the while loop
        String stack1 = "";
        //tracks the Rule
        String R;
        print_output("0", "p", inputstring, "e", "~~", "", n);

        //keeps looping until the inputString is Empty
        while(inputstring.isEmpty()==false){
            //current stack to search the hashmap and shorten the userinput
            String stack2 = "";

            if(state.equals("p")){
                stack2="";
            }
            else if(state.equals("q")){
                stack2 = Character.toString(inputstring.charAt(0));
            }
            else{
                stack2 = Character.toString(stack1.charAt(0));
            }

            //Arraylist that stores the right part of the Hashmap eg. [p, ]= *[q, S, 1]
            ArrayList<String> nextStep = new ArrayList<String>();
            //Arraylist that searches Hashmap using the left part of the Hashamp eg. *[p, ]= [q, S, 1]
            ArrayList<String> search = new ArrayList<String>();
            
            //add the current state and current stack (stack2)
            search.add(state);
            search.add(stack2);
            //searches through the left part of the Hashmap and stores it in nextStep
            nextStep = map.get(search);


            if(state.equals("q")){
                stack2  = inputstring.substring(1, inputstring.length());
            }
            else{
                stack2 = inputstring;
            }
            //increase stepnumber
            stepnumber++;
            //inputstring is shortened by stack2 or current stack
            inputstring = stack2;

            if(state.substring(1, state.length()).isEmpty()==false){
                stack2  = stack1.substring(1, stack1.length());
            }
            else{
                stack2 = stack1;
            }

            //get the state eg. [q, S, 1] which would be q
            state = nextStep.get(0);
            //get the stack eg. [q, S, 1] which would be S plus the current stack (stack2)
            stack1 = nextStep.get(1) + stack2;

            if((nextStep.get(2)).equals("7")){
                R = "S -> aSb";
            }
            else if((nextStep.get(2)).equals("8")){
                R = "S -> e";
            }
            else {
                R = "";
            }

            //Strings used for the end of the PDA
            String tempInputString = "";
            String tempStacks = "";

            if(inputstring.isEmpty()==false){
                tempInputString = inputstring;
            }
            else{
                tempInputString = "e";
            }

            if(stack1.isEmpty()==false){
                tempStacks = stack1;
            }
            else{
                tempStacks = "e";
            }
            //print function that formats the output
            print_output(Integer.toString(stepnumber), state, tempInputString, tempStacks, nextStep.get(2), R, n);

        }

    }

    //precondition: All strings must be defined
    //postcondition: prints out a table in format of step state input stack rule Rused with vertical alignment
    public static void print_output(String a, String b, String c, String d, String f, String e, int nlength){
        if(nlength>2){
            String space = " ";
            String spacerepeat1 = space.repeat(4 - a.length());
            String p1 = "|" + a + spacerepeat1;
            String spacerepeat2 = space.repeat(5 - b.length());
            String p2 = "|" + b + spacerepeat2;
            String spacerepeat3 = space.repeat(2*nlength+1 - c.length());
            String p3 = "|" + c + spacerepeat3;
            String spacerepeat4 = space.repeat((2 + nlength) - d.length());
            String p4 = "|" + d + spacerepeat4;
            String spacerepeat5 = space.repeat(4 - f.length());
            String p5 = "|" + f + spacerepeat5;
            String spacerepeat6 = space.repeat(8 - e.length());
            String p6 = "|" + e + spacerepeat6;
            System.out.println(p1 + p2 + p3 + p4 + p5 + p6);
        }
        else{
            String space = " ";
            String spacerepeat1 = space.repeat(4 - a.length());
            String p1 = "|" + a + spacerepeat1;
            String spacerepeat2 = space.repeat(5 - b.length());
            String p2 = "|" + b + spacerepeat2;
            String spacerepeat3 = space.repeat(5 - c.length());
            String p3 = "|" + c + spacerepeat3;
            String spacerepeat4 = space.repeat(5 - d.length());
            String p4 = "|" + d + spacerepeat4;
            String spacerepeat5 = space.repeat(4 - f.length());
            String p5 = "|" + f + spacerepeat5;
            String spacerepeat6 = space.repeat(8 - e.length());
            String p6 = "|" + e + spacerepeat6;
            System.out.println(p1 + p2 + p3 + p4 + p5 + p6);
        }
    }
    //precondition: ArrayList test must not be null
    //postcondition: fills the ArrayList of test used in the PDA, ranging from a^2b^2$ to a^10b^10$
    public static void filltestcases(ArrayList<String> test){
        String charA = "a";
        String charB = "b";
        String dollar = "$";
        String temp;
        for(int i=2; i<11; i++){
            temp = charA.repeat(i) + charB.repeat(i) + dollar;
            test.add(temp);
        }
    }

    //precondition: LinkedHashMap/ArrayList/Strings must not be null
    //postcondition: Takes in the LinkedHashMap and fills it with ArrayLists. The ArrayLists are filled using the String parameters
    public static void fillHashMap(LinkedHashMap<ArrayList<String>,ArrayList<String>> hash, String col1, 
    String col2, String col3, String col4, String col5){
        ArrayList<String> one = new ArrayList<String>();
        ArrayList<String> two = new ArrayList<String>();
        one.add(col1);
        one.add(col2);
        two.add(col3);
        two.add(col4);
        two.add(col5);
        hash.put(one,two);
    }
}
