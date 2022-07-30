package com.bridgelabz.stock;

import java.io.*;

public class Stock {
    ShareInformation[] stockList;
    int numOfShares;

    public void readStocks() {

        File stockFile = new File("stock.txt");
        FileReader fileReader = null;
        BufferedReader bfr = null;
        try {
            fileReader = new FileReader(stockFile);
            bfr = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String content;
        numOfShares = 0;
        try {
            bfr.mark(1000);
            while (bfr.readLine() != null) {
                numOfShares++;
            }
            bfr.reset();
            System.out.println("Number of shares : " + numOfShares);
            stockList = new ShareInformation[numOfShares];
            numOfShares = 0;
            while ((content = bfr.readLine()) != null) {
                stockList[numOfShares] = new ShareInformation();
                stockList[numOfShares].setShareName(content.split(" ")[0]);
                stockList[numOfShares]
                        .setPrice(Double.parseDouble(content.split(" ")[1]));
                stockList[numOfShares]
                        .setQuantity(Integer.parseInt(content.split(" ")[2]));
                numOfShares++;
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void printAllSharesDetails() {
        for (int i = 0; i < numOfShares; i++) {
            ShareInformation share = stockList[i];
            System.out.println(share.getShareName() + "\t" + share.getPrice() + "\t"
                    + share.getQuantity());
        }
    }

    public void createPortFolioReport() {
        double totalPortFolioValue = 0;
        for (int i = 0; i < numOfShares; i++) {
            ShareInformation share = stockList[i];
            double totalShareValue = (share.price * share.quantity);
            totalPortFolioValue += totalShareValue;
            System.out.println(share.getShareName() + "\t\t\tINR " + share.getPrice() + "\t\t\t"
                    + share.getQuantity() + "\t\t\t INR " + totalShareValue);
        }
        System.out.println("---------------------------------------------");
        System.out.println("Total portfolio value is : INR " + totalPortFolioValue);
    }
}
