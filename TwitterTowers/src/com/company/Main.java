package com.company;

import java.util.Scanner;


public class Main {


    static void rectangularTower(Scanner in){
        int width;
        int height;

            System.out.print("Enter the height of the tower: ");
             height = in.nextInt();
            System.out.print("Enter the width of the tower: ");
             width = in.nextInt();

        if(Math.abs(height-width)>5)
        {
            int area=height*width;
            System.out.println("Area of a rectangular tower "+area);
        }
        else
        {
            int perimeter=2*(height+width);
            System.out.println("Perimeter of a rectangular tower "+perimeter);
        }

    }

    static double calcPerimeterTriangularTower(int height,int width)
    {
        double sideLength = Math.sqrt(Math.pow(width / 2, 2) + Math.pow(height, 2));
        double perimeter = 2 * sideLength + width;
        return perimeter;

    }

    static boolean validition(int height,int width){
        if(width%2==0||width>2*height)
        {
            System.out.println("Triangle tower cannot be printed");
            return false;
        }
        if(width==3&&height>2||width==1)
        {
            System.out.println("Not enough resources to build your tower :)");
            return false;
        }
        return true;
    }

    static void triangularTower(Scanner in){
        int width;
        int height;

            System.out.print("Enter the height of the tower: ");
            height = in.nextInt();
            System.out.print("Enter the width of the tower: ");
            width = in.nextInt();




        System.out.println("1. Perimeter of the tower");
        System.out.println("2. Tower print");
        System.out.print("Enter your choice: ");
        int choice=in.nextInt();
        switch (choice) {
            case 1: {
                double perimeter =calcPerimeterTriangularTower(height,width);
                System.out.println("Perimeter of a triangular tower " + perimeter);
            }
                break;
            case 2:
                boolean valid=validition(height,width);
                if(valid) {
                    printTower(height, width);
                }

                break;

            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }

    }


    static void printTower(int height, int width) {
        printTopRow(width);
        printMiddleRows(height, width);
        printBottomRow(width);
    }

    static void printTopRow(int width) {
        int numberSpaces = (width - 1) / 2;
        printLine(' ', numberSpaces);
        System.out.println("*");

    }

    static void printMiddleRows(int height, int width) {
        int numberRows = height - 2;
        int numberOdds = width / 2 - 1;
        int division = numberRows / numberOdds;
        int remainder = numberRows % numberOdds;
        int numberAsterisks = 3;
        int numberSpaces = (width - 1) / 2-1;

        if (remainder != 0) {
            printRemainderRows(remainder, numberSpaces, numberAsterisks);
        }

        printDivisionRows(numberOdds, division, numberSpaces, numberAsterisks);
    }

    static void printRemainderRows(int remainder, int numberSpaces, int numberAsterisks) {
        for (int i = 0; i < remainder; i++) {
            printLine(' ', numberSpaces);
            printLine('*', numberAsterisks);
            printLine(' ', numberSpaces);
            System.out.println();
        }
    }

    static void printDivisionRows(int numberOdds, int division, int numberSpaces, int numberAsterisks) {
        for (int i = 0; i < numberOdds; i++) {
            for (int j = 0; j < division; j++) {
                printLine(' ', numberSpaces);
                printLine('*', numberAsterisks);
                printLine(' ', numberSpaces);
                System.out.println();
            }
            numberAsterisks += 2;
            numberSpaces -= 1;
        }
    }

    static void printBottomRow(int width) {
        printLine('*', width);
    }

    static void printLine(char symbol, int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(symbol);
        }
    }


static void menu()
{
    Scanner in=new Scanner(System.in);
    int choice=3;
    do{
        System.out.println("\nMenu:");
        System.out.println("1. Rectangular Tower");
        System.out.println("2. Triangular tower Tower");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        choice=in.nextInt();

        switch (choice) {
            case 1:
                rectangularTower(in);
                break;
            case 2:
                triangularTower(in);
                break;
            case 3:
                System.out.println("Exiting program...");
                break;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }


    }while(choice!=3);

}

    public static void main(String[] args) {
       menu();

    }
}
