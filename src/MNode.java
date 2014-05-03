import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Stephen Yingling on 4/25/14.
 */
public class MNode {

    protected HashMap<MNode, Integer> connecting;
    protected ArrayList<Transition> transitions;
    protected String data;
    protected long outGoing = 0;

    public MNode(){
        connecting = new HashMap<MNode, Integer>();
        transitions = new ArrayList<Transition>();
    }

    public MNode(String data){
        connecting = new HashMap<MNode, Integer>();
        transitions = new ArrayList<Transition>();
        this.data = data;
    }

    public void addConnection(MNode m){
        if(!connecting.containsKey(m)){
            connecting.put(m,1);
        }
        else{
            connecting.put(m,connecting.get(m)+1);
        }
        outGoing++;
    }

    public MNode getNext(){
        Random r = new Random();

        if(transitions.size() <1){
            return null;
        }

        while (true){
            Transition t = transitions.get(r.nextInt(transitions.size()));
            double w,ran;
            w= t.getWeight();
            ran = r.nextDouble();
            if(w >= ran){
                return t.getEnd();
            }
        }
    }

    public void constructTransitions(){
        ArrayList<Transition> trans = new ArrayList<Transition>();
        for(MNode m: connecting.keySet()){
            trans.add(new Transition(m,((double)connecting.get(m))/((double) outGoing )));
        }

        transitions = trans;
    }

    public HashMap<MNode, Integer> getConnecting() {
        return connecting;
    }

    public void setConnecting(HashMap<MNode, Integer> connecting) {
        this.connecting = connecting;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
