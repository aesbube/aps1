package labs.aps.lab1.ex3;

public class QuarterlySales {
    private int numOfSales;
    private Integer[] revenues;
    private int quarterNo;

    public QuarterlySales() {
    }

    public QuarterlySales(int numOfSales, Integer[] revenues, int quarterNo) {
        this.numOfSales = numOfSales;
        this.revenues = revenues;
        this.quarterNo = quarterNo;
    }

    public int getNumOfSales() {
        return numOfSales;
    }

    public Integer[] getRevenues() {
        return revenues;
    }

    public int getQuarterNo() {
        return quarterNo;
    }

    public void setNumOfSales(int numOfSales) {
        this.numOfSales = numOfSales;
    }

    public void setRevenues(Integer[] revenues) {
        this.revenues = revenues;
    }

    public void setQuarterNo(int quarterNo) {
        this.quarterNo = quarterNo;
    }

    public int getQuarterTotal() {
        int sum = 0;
        for (int i = 0; i < numOfSales; i++) {
            sum += revenues[i];
        }

        return sum;
    }

}
