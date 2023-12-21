package labs.aps.lab1.ex3;

public class SalesPerson {
    private String name;
    private QuarterlySales[] quarters;

    public SalesPerson(String name, QuarterlySales[] quarters) {
        this.name = name;
        this.quarters = quarters;
    }

    public QuarterlySales[] getQuarters() {
        return quarters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuarters(QuarterlySales[] quarters) {
        this.quarters = quarters;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        builder.append("   ");
        builder.append(quarters[0].getQuarterTotal());
        builder.append("   ");
        builder.append(quarters[1].getQuarterTotal());
        builder.append("   ");
        builder.append(quarters[2].getQuarterTotal());
        builder.append("   ");
        builder.append(quarters[3].getQuarterTotal());
        builder.append("   ");
        return builder.toString();
    }
}
