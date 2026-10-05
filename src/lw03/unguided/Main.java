package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));
        Map<String, Integer> courses = new LinkedHashMap<>();
        List<String> checks = new ArrayList<>();
        int rejected = 0;

        while (input.hasNextLine()){
            String operation = input.nextLine().trim();
            String[] parts = operation.split(" ");
            String command = parts[0];
            String code = parts[1];

            if(command.equals("REGISTER")){
                int count = Integer.parseInt(parts[2]);
                if(count <= 0){
                    rejected++;
                } else{
                    courses.put(code, courses.getOrDefault(code, 0) + count);
                }
            } else if (command.equals("WITHDRAW")){
                int count = Integer.parseInt(parts[2]);
                if(courses.containsKey(code) && count <= courses.get(code)){
                    courses.put(code, courses.get(code) - count);
                } else{
                    rejected++;
                }
            } else if (command.equals("CHECK")){
                if(courses.containsKey(code)){
                    checks.add(code + ": " + courses.get(code) + " students");
                } else{
                    checks.add(code + ": Not found");
                }
            }
        }
        input.close();

        System.out.println("===== Enrollment Checks =====");
        for (String check : checks) {
            System.out.println(check);
        }
        System.out.println();
        System.out.println("===== Final Enrollment =====");
        for (Map.Entry<String, Integer> entry : courses.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " students");
        }
        System.out.println();
        System.out.println("Rejected operations: " + rejected);
    }
}