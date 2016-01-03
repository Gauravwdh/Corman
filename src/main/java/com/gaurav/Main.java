package com.gaurav;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gauravwadhwa on 23/12/15.
 */
public class Main {

    public static void main(String[] args) throws ParseException {
        int[] arr = new int[]{2, 3, 4, 6, 7, 9, 13, 15, 17, 18,19, 20};
        BSTAlgo.Node node = BSTAlgo.createBST(arr, 0, arr.length - 1);
        BSTAlgo.Node.print(node,0);
        System.out.println();
        BSTAlgo.delete(node.getRight());
        System.out.println();
        BSTAlgo.Node.print(node,0);
    }


    private static void regex() throws ParseException {
        // Yatra
//        String msg = "Yatra reference number is 18061596545756.Your Airline PNR for Go Air Flight G8-305@05:50 on Sat 27 Jun 15 from Mumbai to Chennai is NY421L .Get best deals on the Go! Download Yatra App @  http://goo.gl/KawObT";
//        String pattern = ".* Flight (.*)@.* (\\d{1,2} [A-Za-z]{3} \\d{2,4}).* is ([A-Za-z0-9]*).*";

        // mmtrip


        String airLine = "([A-Za-z0-9/-]+)";
        String pnr = "([A-Za-z0-9]*)";
        String date = "(\\d{1,2} [A-Za-z]{3} \\d{2,4})";

        String msg = "The PNR for your Air India Flt AI9617 for Delhi-Jabalpur on 18 Jan 16 at  12:40 hrs  is YPL4Q. Tip: Check PNR status and book using MakeMyTrip App! Download at http://bit.ly/mmyt_app. Thank you.";

        msg = msg.toUpperCase();
        String pattern = ".*FLT[ ]+([A-Za-z0-9/-]+).*ON[ ]+(\\d{1,2} [A-Za-z]{3} \\d{2,4}).* IS[ ]+([A-Za-z0-9]*).*";

        System.out.println(msg);

        match(pattern, msg);
        printDate("dd MMM", "27 NOV");
    }


    private static void printDate(String datePattern, String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        System.out.println(dateFormat.parse(date));
    }


    private static boolean match(String regex, String message) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            System.out.println("Found");
            int count = matcher.groupCount();
            for (int i = 1; i <= count; i++) {
                System.out.println("item " + i + " : " + matcher.group(i));
            }
        } else {
            System.out.println("result not found");
        }

        return false;
    }
}
