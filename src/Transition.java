/**
 * Created by Stephen Yingling on 4/25/14.
 */
public class Transition {
    protected double weight;
    protected String end;

    public Transition(){}

    public Transition(String end, double weight){
        this.weight = weight;
        this.end = end;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
