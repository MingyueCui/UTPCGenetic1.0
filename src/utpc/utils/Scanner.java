package utpc.utils;

import java.util.*;

public class Scanner {
    public List<Term> terms;
    public Hashtable<String,Boolean> testcase = new Hashtable<>();

    public Scanner(String expr){
        expr = expr.replace(" ","");
        expr = expr.replace("(","");
        expr = expr.replace(")","");
        expr = expr.replace("&","");
        System.out.println(expr);
        setTerms(expr.split("\\|"));
        setTestcase();
    }

    public void setTestcase(){
        String symbol = "~";
        for(int i = 0;i<terms.size();i++){
            String currentTerm = terms.get(i).getTerm();
            for(int j = 0;j<currentTerm.length();j++){
               if(!symbol.contains(currentTerm.charAt(j)+"")){
                   testcase.put(currentTerm.charAt(j)+"",true);
               } else {
                   testcase.put(currentTerm.charAt(j+1)+"",true);
                   j++;
               }
            }
        }
    }
    public void setTerms(String[] temp){
        terms = new ArrayList<>();
        for(int i = 0;i<temp.length;i++){
            Term new_term = new Term(temp[i],this);
            terms.add(new_term);
        }
    }
    public List<Term> getTerms(){
//        for(int i = 0;i < terms.size();i++){
//            System.out.println("项："+terms.get(i)+" ");
//        }
        return this.terms;
    }
    public void printTestCase(){
        ArrayList<String> list = new ArrayList(testcase.keySet());
        Collections.sort(list,new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
               if(0+o1.charAt(0)>0+o2.charAt(0)){
                   return 1;
               } else {
                   return -1;
               }
            }
        });
        for (String key : list) {
            System.out.print("字句:"+ key +"_"+testcase.get(key)+" ");
        }
        System.out.println(" ");
    }
    public void printNotConstant(){
        for(int i = 0;i < terms.size();i++){
            if(!terms.get(i).getIsConstant())
            System.out.println("项："+terms.get(i).term+" ");
        }
    }
}
