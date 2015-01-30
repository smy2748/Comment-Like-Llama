import java.util.HashMap;
import java.util.Random;

/**
 * Created by Stephen Yingling on 1/29/15.
 */
public class MSubChain {
    protected HashMap<String, MNode> nodes;
    int n_gram;

    public MSubChain(int n_gram){
        this.n_gram = n_gram;
        nodes = new HashMap<String, MNode>();
    }

    public void addContext(String text){
        String trimmed = text.replaceAll("\n"," ").trim();
        trimmed=trimmed.replaceAll("_","");
        trimmed = trimmed.replaceAll("  "," ");
        String [] words = trimmed.split(" ");
        MNode cur=null, next;
        for (int i=0; i<words.length; i++){
            if (i+n_gram+1 < words.length){
                String str = "";
                int j=i, found=0;
                while (found < n_gram && j+n_gram+1 < words.length){
                    if (!words[j].equals("")){
                        str += words[j] + "_";
                        found++;
                    }
                    j++;
                }
                if (str.length() >2){
                    str = str.substring(0,str.length()-1);
                }

                if (!nodes.containsKey(str)){
                    next = new MNode(str);
                    nodes.put(str,next);
                }
                else{
                    next = nodes.get(str);
                }

                if (cur != null){
                    cur.addConnection(words[i+n_gram-1]);
                }
                cur = next;
            }
        }
    }

    public void finalizeChain(){
        for(MNode m : nodes.values()){
            m.constructTransitions();
        }
    }

    public String calcNext(String str){
        if (nodes.containsKey(str)){
            return nodes.get(str).getNext();
        }
        else{
            return "";
        }
    }

    public String randomVal(){
        Random r = new Random();
        return nodes.values().toArray(new MNode[0])[r.nextInt(nodes.size())].getData();
    }
}
