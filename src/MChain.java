import java.util.HashMap;

/**
 * Created by Stephen Yingling on 4/25/14.
 */
public class MChain {
    protected MSubChain subChains[];
    protected int n_grams;


    public MChain(int n_grams){
        this.n_grams = n_grams;
        subChains = new MSubChain[n_grams];
        for(int i= 0; i< n_grams; i++){
            subChains[i] = new MSubChain(n_grams-i);
        }
    }

    public void addContext(String str){
        for (MSubChain chain: subChains){
            chain.addContext(str);
        }
    }

    public void finalizeChain(){
        for (MSubChain chain: subChains){
            chain.finalizeChain();
        }
    }

    public String genString(){
        StringBuilder res = new StringBuilder();
        long count=0;
        String cur = subChains[0].randomVal();
        String next="", tempCur;

        while (count <140){
            tempCur = cur.toString();

            for(int i=subChains.length-joinedWords(cur); i<subChains.length; i++){
                next = subChains[i].calcNext(tempCur);
                if (!next.equals("")){
                    res.append(next);
                    res.append(" ");
                    break;
                }
                else{
                    tempCur = shrinkJoined(tempCur);
                }
            }

            if (next.equals("")){
                //TODO: Look up new shit
            }
            else{
                cur = reJoin(tempCur,next);
            }

            count++;
        }

        return res.toString();
    }

    private String reJoin(String existing, String next){
        String temp[] = existing.split("_");
        StringBuilder result = new StringBuilder();
        for(int i=1; i< temp.length; i++){
            result.append(temp[i] +"_");
        }
        result.append(next);
        return result.toString();
    }

    private String shrinkJoined(String existing){
        String temp[] = existing.split("_");
        if (temp.length <2){
            return "";
        }
        StringBuilder result = new StringBuilder();

        for(int i=0; i< temp.length-1; i++){
            result.append(temp[i]);
            result.append("_");
        }
        result.deleteCharAt(result.length()-1);
        return result.toString();
    }

    private int joinedWords(String words){
        return words.split("_").length;
    }

    }
