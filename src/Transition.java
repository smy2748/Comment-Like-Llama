/**
 * Created by Stephen Yingling on 4/25/14.
 */
public class Transition {
    protected double weight;
    protected MNode end;

    public Transition(){}

    public Transition(MNode end, double weight){
        this.weight = weight;
        this.end = end;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public MNode getEnd() {
        return end;
    }

    public void setEnd(MNode end) {
        this.end = end;
    }
}
