import java.util.HashMap;

/**
 * Created by Stephen Yingling on 4/25/14.
 */
public class MChain {
    protected MNode start;
    protected HashMap<String, MNode> nodes;


    public MChain(){
        start = new MNode(((char)2) + "");
        nodes = new HashMap<String, MNode>();
        nodes.put(((char)2) + "", start);
    }

    public void addContext(String text){
        String trimmed = text.replaceAll("\n"," ").trim();
        String [] words = trimmed.split(" ");

        MNode cur = start, next;
        for(String s : words){
            if(!s.equals("")){

                if(!nodes.containsKey(s)){
                    next = new MNode(s);
                    nodes.put(s,next);
                }
                else{
                    next = nodes.get(s);
                }
                cur.addConnection(next);
                cur = next;
            }
        }
    }

    public void finalizeChain(){
        for(MNode m : nodes.values()){
            m.constructTransitions();
        }
    }

    public String genString(){
        MNode cur = start;
        StringBuilder res = new StringBuilder();
        long count =0;
        while (cur != null && count < 140){
            cur = cur.getNext();
            if(cur != null){
                res.append(cur.getData());
                res.append(" ");
            }
            count++;
        }

        return res.toString();
    }
}
