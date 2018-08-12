import java.util.Scanner;
import java.util.Stack;

public class P10267 {
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        char [][] image = new char[0][0];
        String line = c.nextLine();
        String [] splitLine = line.split(" ");
        System.out.println();
        while(Character.toUpperCase(line.charAt(0)) != 'X') {
            switch (Character.toUpperCase(line.charAt(0))){
                case 'I':
                    // initiate a new image
                    int n,m;
                    m = Integer.parseInt(splitLine[1]);
                    n = Integer.parseInt(splitLine[2]);
                    image = new char[n][m];

                    // clear the image by resetting its colors to O "White color"
                    for(int i=0; i<image.length; i++)
                        for(int j=0; j<image[i].length; j++)
                            image[i][j] = 'O';
                    break;

                case 'C':
                    for(int i=0; i<image.length; i++)
                        for(int j=0; j<image[i].length; j++)
                            image[i][j] = 'O';
                    break;

                case 'L':
                    int x,y;
                    x = Integer.parseInt(splitLine[1])-1;
                    y = Integer.parseInt(splitLine[2])-1;
                    char clr = splitLine[3].toCharArray()[0];
                    image[y][x] = clr;
                    break;

                case 'V':
                    int y1,y2;
                    x = Integer.parseInt(splitLine[1])-1;
                    y1 = Integer.parseInt(splitLine[2])-1;
                    y2 = Integer.parseInt(splitLine[3])-1;
                    clr = splitLine[4].toCharArray()[0];
                    for(int i=y1; i<=y2; i++)
                        image[i][x] = clr;
                    break;

                case 'H':
                    int x1,x2;
                    x1 = Integer.parseInt(splitLine[1])-1;
                    x2 = Integer.parseInt(splitLine[2])-1;
                    y = Integer.parseInt(splitLine[3])-1;
                    clr = splitLine[4].toCharArray()[0];
                    for(int i=x1; i<=x2; i++)
                        image[y][i] = clr;
                    break;

                case 'K':
                    x1 = Integer.parseInt(splitLine[1])-1;
                    x2 = Integer.parseInt(splitLine[2])-1;
                    y1 = Integer.parseInt(splitLine[3])-1;
                    y2 = Integer.parseInt(splitLine[4])-1;
                    clr = splitLine[5].toCharArray()[0];
                    for(int i = x1; i<=x2; i++)
                        for( int j=y1; j<=y2; j++)
                            image[j][i] = clr;
                    break;

                case 'F':
                    x = Integer.parseInt(splitLine[1])-1;
                    y = Integer.parseInt(splitLine[2])-1;
                    clr = splitLine[3].toCharArray()[0];
                    char currentClr = image[x][y];
                    Stack<Integer[]> stack = new Stack<>();
                    stack.add(new Integer [] {x,y});
                    while(!stack.isEmpty()){
                        // pop out a point from the stack
                        Integer [] point = stack.pop();
                        // color the point to the new color
                        image[point[0]][point[1]] = clr;
                        // check region's points:
                        // check the previous point:
                        if(point[0]>0 && image[point[0]-1][point[1]] == currentClr)
                            stack.push(new Integer[]{point[0]-1, point[1]});
                        // check the top point:
                        if(point[1]>0 && image[point[0]][point[1]-1] == currentClr)
                            stack.push(new Integer[]{point[0], point[1]-1});
                        // check the bottom point:
                        if(point[1]<image[0].length-1 && image[point[0]][point[1]+1] == currentClr)
                            stack.push(new Integer[]{point[0], point[1]+1});
                        // check the top point:
                        if(point[0]<image.length-1 && image[point[0]+1][point[1]] == currentClr)
                            stack.push(new Integer[]{point[0]+1, point[1]});
                    }
                    break;
                case 'S':
                    String name = splitLine[1];
                    System.out.println(name);
                    printImage(image);
                    break;
//                case 'X':
//                   break;
                default:
                    break;
            }
            line = c.nextLine();
            splitLine = line.split(" ");
        }
    }

    private static void printImage(char[][] image) {
        for(int i=0; i<image.length; i++) {
            for (int j = 0; j < image[i].length; j++)
                System.out.print(image[i][j]);
            System.out.println();
        }
    }
}
