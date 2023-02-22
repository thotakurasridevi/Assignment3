import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

class practiseProgram {
    public static void main(String[] args) {
        GetFileData menuListGetFileData = new GetFileData("menuList.csv");
        GetFileData orderDetailsGetFileData = new GetFileData("orderDetails.csv");

        int lastID1 = menuListGetFileData.getLastID();
        int lastID2 = orderDetailsGetFileData.getLastID();
        System.out.println("Last ID: " + lastID1 + ":" + lastID2);

        System.out.println(Arrays.toString(menuListGetFileData.searchDataINFile("5")));
        System.out.println(Arrays.toString(orderDetailsGetFileData.searchDataINFile("105")));
    }
}

class GetFileData {
    String fileName = "";
    int countData = 0;
    int sizeCompleteFileData = 0;
    File fileReader;
    Scanner scFileReader;

    ArrayList<String> completeFileData = new ArrayList<String>();

    GetFileData(String fileName) {
        this.fileName = fileName;
        try {
            this.fileReader = new File(fileName);
            this.scFileReader = new Scanner(fileReader);

            while (this.scFileReader.hasNext()) {
                String eachLine = this.scFileReader.nextLine();
                completeFileData.add(eachLine);
            }

            if (fileName == "menuList.csv")
                this.countData = 3;
            else if (fileName == "orderDetails.csv")
                this.countData = 5;

        } catch (Exception e) {
            System.out.println("Unable to open the file: " + fileName);
        }
        scFileReader.close();
    }

    String[][] getCompleteFileDataINArray() {
        String[][] completeFileDataArray = null;

        this.sizeCompleteFileData = this.completeFileData.size();
        completeFileDataArray = new String[sizeCompleteFileData][countData];

        for (int i = 0; i < sizeCompleteFileData; i++) {
            String dataLineOne = completeFileData.get(i);
            for (int j = 0; j < countData; j++) {
                if (j == countData - 1) {
                    completeFileDataArray[i][j] = dataLineOne;
                } else {
                    completeFileDataArray[i][j] = dataLineOne.substring(0, dataLineOne.indexOf(","));
                    dataLineOne = dataLineOne.substring(dataLineOne.indexOf(",") + 1);
                }
            }
        }
        return completeFileDataArray;
    }

    public int getLastID() {
        String[][] completeFileDataArray = this.getCompleteFileDataINArray();
        int lastID = 0;
        lastID = Integer.valueOf(completeFileDataArray[completeFileDataArray.length - 1][0]);
        return lastID;
    }

    public String[] searchDataINFile(String searchID) {
        String[][] completeFileDataArray = this.getCompleteFileDataINArray();
        String outputArray[] = new String[this.countData];

        for (int i = 0; i < sizeCompleteFileData; i++) {
            if (searchID.equals(completeFileDataArray[i][0])) {
                outputArray = completeFileDataArray[i];
            }
        }
        return outputArray;
    }
}