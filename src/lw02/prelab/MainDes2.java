import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class MainDes2 {
    public static void main(String[] args) {
        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();
        Queue<String[]> processes = new LinkedList<>();
        Stack<String[]> failReqs = new Stack<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));

        while (sc.hasNext()) {
            String name = sc.next();
            String subject = sc.next();

            String[] bookReq = new String[2];
            bookReq[0] = name;
            bookReq[1] = subject;

            requests.add(bookReq);

            String[] bookStock = new String[3];
            bookStock[0] = "2"; //Kalkulus
            bookStock[1] = "1"; //Fisika
            bookStock[2] = "2"; //Statistika

            books.add(bookStock);

            boolean exist = false;
            for (String[] member : members){
                if (member[0].equals(name)){
                    exist = true;
                    break;
                }
            }

            if (!exist){
                String[] newMember = new String[2];
                newMember[0] = name;
                newMember[1] = "0"; // Borrowed book

                members.add(newMember);
            }
        }

        while (!requests.isEmpty()) {
            processes.add(requests.removeFirst());
        }

        for (String[] member : members){
            for(String[] process : processes){
                for(String[] book : books){
                    if (member[0].equals(process[0])){
                        int borrowedBook = Integer.parseInt(member[1]);
                        int stokKalkulus = Integer.parseInt(book[0]);
                        int stokFisika = Integer.parseInt(book[1]);
                        int stokStatistika = Integer.parseInt(book[2]);
                        
                        if (Integer.parseInt(member[1]) < 3){
                            if (stokKalkulus > 0 && process[1].equals("Kalkulus")){
                                stokKalkulus--;
                                borrowedBook++;

                                book[0] = String.valueOf(stokKalkulus);
                                member[1] = String.valueOf(borrowedBook);
                            }
                            else if (stokFisika > 0 && process[1].equals("Fisika")){
                                stokFisika--;
                                borrowedBook++;

                                book[1] = String.valueOf(stokFisika);
                                member[1] = String.valueOf(borrowedBook);
                            }
                            else if (stokStatistika > 0 && process[1].equals("Statistika")){
                                stokStatistika--;
                                borrowedBook++;

                                book[2] = String.valueOf(stokStatistika);
                                member[1] = String.valueOf(borrowedBook);
                            }
                        } else {
                            failReqs.add(0, process);
                            break;
                        }
                    }
                }
            }
        }

        System.out.println("=== Successfully Processed Requests ===");
        for (String[] proces : processes){
            for (String word : proces){
                System.out.print(word + " ");
            }
            System.out.println();
        }
        
        System.out.println();
        
        System.out.println("=== Remaining Book Stock ===");
        for (String[] book : books){
            for (String word : book){
                System.out.print(word + " ");
            }
            System.out.println();
        }
        
        System.out.println();

        System.out.println("=== Failed Requests ===");
        for (String[] failReq : failReqs){
            for (String word : failReq){
                System.out.print(word + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}