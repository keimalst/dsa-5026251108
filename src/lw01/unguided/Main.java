package lw01.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("rentals.txt"));
        int x = sc.nextInt();
        Rental[] r = new Rental[x];
        int[] count = new int[x];
        sc.nextLine();
        for(int i = 0; i < x; i++){
            String type = sc.next();
            String id = sc.next();
            int days = sc.nextInt();
            count[i]  = sc.nextInt();

            if(type.equals("LAPTOP")){
                r[i] = new LaptopRental(id,days);
            }
            else{
                r[i] = new ProjectorRental(id, days);
            }
        }

        sc.close();

        for(int i = 0; i < x; i++){
           
            System.out.println(r[i].getId() + " | " + r[i].label() + " | " + r[i].calculateCharge(count[i]));
        }
    }
}