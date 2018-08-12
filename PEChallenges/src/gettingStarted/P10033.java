import java.util.Arrays;
import java.util.Scanner;

public class P10033 {
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        int testcases = c.nextInt();
        String [] ram = new String [1000];
        // initially the RAM and Registers were initialized to Zeros.
        Arrays.fill(ram,"000");
        String [] register = new String [10];
        Arrays.fill(register, "000");
        int instructionsCounter = 0;
        while(testcases --> 0){
            c.nextLine();
            c.nextLine();
            String line = c.nextLine();
            int pointer = 0;
            // fill in the RAM from input
            while(!line.trim().isEmpty()) {
                ram[pointer++] = line;
                line = c.nextLine();
            }
            // check array's content [for debugging]
            //System.out.println(Arrays.toString(ram));
            pointer = 0;
            int d,s,a,n;

            // execute RAM instructions
            boolean halt = false;
            while(!halt && pointer < 1000){
                instructionsCounter++;
                switch (ram[pointer].charAt(0)){
                    case '0':
                        // 0ds means goto the location in register d unless register s contains 0.
                        d = (int) ram[pointer].charAt(1) - 48;
                        s = (int) ram[pointer].charAt(2) - 48;
                        if(Integer.parseInt(register[s])!=0)
                            pointer = Integer.parseInt(register[d]);
                        else
                            pointer++;
                        break;
                    case '1':
                        // nothing significant here!
                        // it is the halting instruction.
                        if(Integer.parseInt(ram[pointer]) == 100)
                            halt = true;
                        else
                            pointer++;
                        break;
                    case '2':
                        // 2dn means set register d to n (between 0 and 9)
                        d = (int) ram[pointer].charAt(1) - 48;
                        n = (int) ram[pointer].charAt(2) - 48;
                        register[d] = n+"";
                        pointer++;
                        break;
                    case '3':
                        // 3dn means add n to register d
                        d = (int) ram[pointer].charAt(1) - 48;
                        n = (int) ram[pointer].charAt(2) - 48;
                        register[d] = ((Integer.parseInt(register[d])+n)%1000)+"";
                        pointer++;
                        break;
                    case '4':
                        // 4dn means multiply register d by n
                        d = (int) ram[pointer].charAt(1) - 48;
                        n = (int) ram[pointer].charAt(2) - 48;
                        register[d] = ((Integer.parseInt(register[d]) * n)%1000)+"";
                        pointer++;
                        break;
                    case '5':
                        // 5ds means set register d to the value of register s
                        d = (int) ram[pointer].charAt(1) - 48;
                        s = (int) ram[pointer].charAt(2) - 48;
                        register[d] = register[s];
                        pointer++;
                        break;
                    case '6':
                        // 6ds means add the value of register s to register d
                        d = (int) ram[pointer].charAt(1) - 48;
                        s = (int) ram[pointer].charAt(2) - 48;
                        register[d] = ((Integer.parseInt(register[d])+Integer.parseInt(register[s]))%1000)+"";
                        pointer++;
                        break;
                    case '7':
                        // 7ds means multiply register d by the value of register s
                        d = (int) ram[pointer].charAt(1) - 48;
                        s = (int) ram[pointer].charAt(2) - 48;
                        register[d] = ((Integer.parseInt(register[d])*Integer.parseInt(register[s]))%1000)+"";
                        pointer++;
                        break;
                    case '8':
                        // 8da means set register d to the value in RAM whose address is in register a
                        d = (int) ram[pointer].charAt(1) - 48;
                        a = (int) ram[pointer].charAt(2) - 48;
                        register[d] = ram[Integer.parseInt(register[a])];
                        pointer++;
                        break;
                    case '9':
                        // 9sa means set the value in RAM whose address is in register a to that of register s
                        s = (int) ram[pointer].charAt(1) - 48;
                        a = (int) ram[pointer].charAt(2) - 48;
                        ram[a] = ram[Integer.parseInt(register[s])];
                        pointer++;
                        break;
                }
            }
            System.out.println(instructionsCounter);
            instructionsCounter = 0;
        }
    }
}
