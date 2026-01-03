
package code;


public class ModelData {

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getTotal() {
        return Total;
    }

    public void setAmount(double Total) {
        this.Total = Total;
    }

    public double getWet_weight() {
        return Wet_weight;
    }

    public void setCost(double Wet_weight) {
        this.Wet_weight = Wet_weight;
    }

    public double getsack_weight() {
        return sack_weight;
    }

    public void setProfit(double sack_weight) {
        this.sack_weight = sack_weight;
    }

    public ModelData(String month, double Total, double Wet_weight, double sack_weight) {
        this.month = month;
        this.Total = Total;
        this.Wet_weight = Wet_weight;
        this.sack_weight = sack_weight;
    }

    public ModelData() {
    }

    private String month;
    private double Total;
    private double Wet_weight;
    private double sack_weight;
}
