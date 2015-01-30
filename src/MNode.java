import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Stephen Yingling on 4/25/14.
 */
public class MNode {

    protected HashMap<String, Integer> connecting;
    protected ArrayList<Transition> transitions;
    protected String data;
    protected long outGoing = 0;

    public MNode(){
        connecting = new HashMap<String, Integer>();
        transitions = new ArrayList<Transition>();
    }

    public MNode(String data){
        connecting = new HashMap<String, Integer>();
        transitions = new ArrayList<Transition>();
        this.data = data;
    }

    public void addConnection(String str){
        if(!connecting.containsKey(str)){
            connecting.put(str,1);
        }
        else{
            connecting.put(str,connecting.get(str)+1);
        }
        outGoing++;
    }

    public String getNext(){
        Random r = new Random();

        if(transitions.size() <1){
            return null;
        }

        double rand = r.nextDouble(),sum=0;
        for (Transition t : shuffleTransitions()){
            sum += t.getWeight();
            if (sum <rand){
                return t.getEnd();
            }
        }

        return transitions.get(r.nextInt(transitions.size())).getEnd();
    }

    public void constructTransitions(){
        ArrayList<Transition> trans = new ArrayList<Transition>();
        for(String m: connecting.keySet()){
            trans.add(new Transition(m,((double)connecting.get(m))/((double) outGoing )));
        }

        transitions = trans;
    }

    public HashMap<String, Integer> getConnecting() {
        return connecting;
    }

    public void setConnecting(HashMap<String, Integer> connecting) {
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

    private ArrayList<Transition> shuffleTransitions(){
        ArrayList<Transition> base = new ArrayList<Transition>(transitions);
        ArrayList<Transition> result = new ArrayList<Transition>();
        Random r = new Random();
        for(int i=r.nextInt(base.size()); i< base.size()-1; i=r.nextInt(base.size())){
            result.add(base.remove(i));
        }
        result.add(base.get(0));
        return result;
    }
}
