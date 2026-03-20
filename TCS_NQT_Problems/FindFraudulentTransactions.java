/*

Given a list of transactions, identify and report the fraudulent transactions. 
Each transaction consists of sender, receiver, amount and time. 
A transaction is considered fraudulent if there exists another transaction with the same sender, receiver and amount and the time difference between them is less than or equal to 60 seconds. 
Such transactions should be marked as "Fraud Transaction". 
It is guaranteed that at least one fraudulent transaction will always be present in the input.                                          

Note: An efficient solution is expected. The use of appropriate data structures such as HashMaps is recommended to handle large inputs.

Input Format:
5
{ANU,RAM,200.50} : 1000
{ANU,RAM,200.50} : 1050
{RAHUL,SAM,300.00} : 2000
{ANU,RAM,200.50} : 2000
{RAM,SAM,300.00} : 2050

Output Format:
Fraud Transaction
Fraud Transaction
Valid
Valid
Valid

*/

// Code:

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Transaction {
    String sender;
    String receiver;
    double amount;
    int time;
    int index;

    Transaction(String s, String r, double a, int t, int idx) {
        sender = s;
        receiver = r;
        amount = a;
        time = t;
        index = idx;
    }
}

public class FindFraudulentTransactions {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        List<Transaction> list = new ArrayList<>();

        for(int i = 0; i < n; i++) {

            String line = sc.nextLine();

            // Remove spaces
            line = line.replace(" ", "");

            // Split into parts
            String[] parts = line.split(":");

            // Left part → {ANU,RAM,200.50}
            String left = parts[0];

            // Remove {}
            left = left.replace("{", "").replace("}", "");

            String[] data = left.split(",");

            String sender = data[0];
            String receiver = data[1];
            double amount = Double.parseDouble(data[2]);

            int time = Integer.parseInt(parts[1]);

            list.add(new Transaction(sender, receiver, amount, time, i));
        }

        // Result array
        String[] result = new String[n];
        Arrays.fill(result, "Valid");

        // Grouping
        HashMap<String, List<Transaction>> map = new HashMap<>();

        for(Transaction t : list) {

            String key = t.sender + "|" + t.receiver + "|" + t.amount;

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(t);
        }

        // Process each group
        for(List<Transaction> group : map.values()) {

            // Sort by time
            group.sort((a, b) -> a.time - b.time);

            for(int i = 1; i < group.size(); i++) {

                if(group.get(i).time - group.get(i-1).time <= 60) {

                    result[group.get(i).index] = "Fraud Transaction";
                    result[group.get(i-1).index] = "Fraud Transaction";
                }
            }
        }

        // Output
        for(String r : result) {
            System.out.println(r);
        }
    }
}