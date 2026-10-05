import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MainDes {
    public static void main (String [] args){

        Scanner input = new Scanner(Main.class.getResourceAsStream("input.txt"));

        LinkedList<String[]> transaction = new LinkedList<>();
        LinkedList<String[]> customer = new LinkedList<>();

        while(input.hasNextLine()){
            
            String [] trans = {input.next(),input.next(),input.next()};

            transaction.add(trans);
        }

        for (String[] t : transaction){
            boolean status = false;

            for ( String[] c : customer ){
                if(c[0].equals(t[0])){
                    status = true;
                }
            }

            if(!status){
                    String[] detail = {t[0],"0"};
                    customer.add(detail);
            }
        }

        Queue<String []> queue = new LinkedList<>(transaction);
        Stack<String []> stack = new Stack<>();

        while (!queue.isEmpty()){
            String[] data = queue.poll();
            String name = data[0];
            String type = data[1];
            int amount = Integer.parseInt(data[2]);

            if(type.equals("DEPOSIT")){
                for (String[] c : customer ){
                    if(c[0].equals(name)){
                        int balance = Integer.parseInt(c[1]);
                        balance += amount;
                        String saldoBaru = Integer.toString(balance);
                        c[1] = saldoBaru;
                    }
                }
            }

            if(type.equals("WITHDRAW")){
                for (String[] c : customer ){
                    if(c[0].equals(name)){
                        int balance = Integer.parseInt(c[1]);
                        if(amount > balance){
                            stack.push(data);
                        }
                        else{
                        balance -= amount;
                        String saldoBaru = Integer.toString(balance);
                        c[1] = saldoBaru;
                        }
                    }
                }
            }
            
        }

        System.out.println("=== Final Balances ===");
        for (String[] c : customer){
            System.out.println(c[0] + " : " + c[1]);
        }
        System.out.println("=== Failed Transaction ===");
        while(!stack.isEmpty()){
            String[] data = stack.pop();
            System.out.println(data[0] + " " + data[1] + " " + data[2]);
        }

        input.close();
    }
}