
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class main{

    public static void main(String args[]){
        //print_output("----", "-----", "-", "", "", "", 5);

        //Linked HasMap vs HashMap
        //HashMap places entries in order (a-z)
        //LinkedHashMap mantains order
        LinkedHashMap<ArrayList<String>,ArrayList<String>> map = new LinkedHashMap<ArrayList<String>,ArrayList<String>>();
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        ArrayList<String> list3 = new ArrayList<String>();
        ArrayList<String> list4 = new ArrayList<String>();
        ArrayList<String> list5 = new ArrayList<String>();
        ArrayList<String> list6 = new ArrayList<String>();
        ArrayList<String> list7 = new ArrayList<String>();
        ArrayList<String> list8 = new ArrayList<String>();
        ArrayList<String> list9 = new ArrayList<String>();
        ArrayList<String> list10 = new ArrayList<String>();
        ArrayList<String> list11 = new ArrayList<String>();
        ArrayList<String> list12 = new ArrayList<String>();
        ArrayList<String> list13 = new ArrayList<String>();
        ArrayList<String> list14 = new ArrayList<String>();
        ArrayList<String> list15 = new ArrayList<String>();
        ArrayList<String> list16 = new ArrayList<String>();
        String[] first_column = {"p","q","qa","qb"};
        String[] second_column = {"a","b","$","S"};
        String[] third_column = {"q","qa","q$","qa","qb"};
        String[] fourth_column = {"S","aSb"};
        String[] fifth_column = {"1","2","3","4","5","6","7","8"};

        //first row
        list.add(first_column[0]);
        list.add("");
        list2.add(third_column[0]);
        list2.add(fourth_column[0]);
        list2.add(fifth_column[0]);

        map.put(list,list2);

        //second row
        list3.add(first_column[1]);
        list3.add(second_column[0]);
        list4.add(third_column[1]);
        list4.add("");
        list4.add(fifth_column[1]);

        map.put(list3,list4);

        //thrid row
        list5.add(first_column[2]);
        list5.add(second_column[0]);
        list6.add(third_column[0]);
        list6.add("");
        list6.add(fifth_column[2]);

        map.put(list5,list6);

        //fourth row
        list7.add(first_column[1]);
        list7.add("b");
        list8.add(third_column[4]);
        list8.add("");
        list8.add(fifth_column[3]);

        map.put(list7,list8);

        //fifth row
        list9.add(first_column[3]);
        list9.add(second_column[1]);
        list10.add(third_column[0]);
        list10.add("");
        list10.add(fifth_column[4]);

        map.put(list9,list10);

        //sixth row
        list11.add(first_column[1]);
        list11.add(second_column[2]);
        list12.add(third_column[2]);
        list12.add("");
        list12.add(fifth_column[5]);
        map.put(list11,list12);

        //seventh row
        list13.add(first_column[2]);
        list13.add(second_column[3]);
        list14.add(third_column[3]);
        list14.add("aSb");
        list14.add(fifth_column[6]);
        map.put(list13,list14);

        //eigth row
        list15.add(first_column[3]);
        list15.add(second_column[3]);
        list16.add(third_column[4]);
        list16.add("");
        list16.add(fifth_column[7]);
        map.put(list15,list16);
        System.out.println(map);
//--------------------------------------------------------------------------


        //int n used for input string
        int n=0;
        //fills test case
        ArrayList<String> testcase = new ArrayList<String>();

        filltestcases(testcase);
        System.out.println(testcase);

        //loop that checks if users input is valid
        ArrayList<String> userinput = new ArrayList<String>();

        Scanner myObj = new Scanner(System.in);
        System.out.println("Input a test case:");
        String temp = myObj.nextLine();

        for(int i=0; i<testcase.size(); i++){
            if(temp.equals(testcase.get(i))){
                n = i +2;
                break;
            }
            if(i==8){
                System.out.println(temp + "is not in test cases");
                System.exit(0);
            }
        }
        //add more than 1?
        System.out.println("N is equal to " + n);
        int stepnumber = 0;
        String state = "p";
        String charA = "a";
        String charB = "b";
        String dollar = "$";
        String inputstring = charA.repeat(n) + charB.repeat(n) + dollar;
        String stack1 = "";
        String R;

        //prints 3 lines of table

        //processing function?
        //rules = map
        // stacks(stack2) and stack(stack1) are two different stacks
        while(inputstring.isEmpty()==false){
            String stack2 = "";
            //use either contain or equals
            if(state.equals("p")){
                stack2="";
            }
            else if(state.equals("q")){
                stack2 = Character.toString(inputstring.charAt(0));
            }
            else{
                stack2 = Character.toString(stack1.charAt(0));
            }

            //Searching through the hashmap -----------------------------------------

            ArrayList<String> nextStep = new ArrayList<String>();
            nextStep.add(" ");
            ArrayList<String> search = new ArrayList<String>();
            search.add(state);
            //stack1 relooping should be a not S
            search.add(stack2);
            //error
            nextStep = map.get(search);
            //System.out.println("Transversing through hasmap to find this " + search);
            //System.out.println("This is next step: " + map.get(search));

            //Searching through the hashmap -----------------------------------------

            if(state.equals("q")){
                stack2  = inputstring.substring(1, inputstring.length());
            }
            else{
                stack2 = inputstring;
            }
            stepnumber++;
            inputstring = stack2;
            //System.out.println("inputstring is" + inputstring);

            if(state.substring(1, state.length()).isEmpty()==false){
                stack2  = stack1.substring(1, stack1.length());
            }
            else{
                stack2 = stack1;
            }

            state = nextStep.get(0);
            //System.out.println("state is " + state);
            stack1 = nextStep.get(1) + stack2;
            //System.out.println("stack1 is " + stack1);

            if((nextStep.get(2)).equals("7")){
                R = "S -> aSb";
            }
            else if((nextStep.get(2)).equals("8")){
                R = "S -> e";
            }
            else {
                R = "";
            }

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
            //System.out.println("Reloop stepnumber:" + stepnumber);
            print_output(Integer.toString(stepnumber), state, tempInputString, tempStacks, nextStep.get(2), R, n);
            //end of while loop

        }

    }

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
        //finish the second part
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
}