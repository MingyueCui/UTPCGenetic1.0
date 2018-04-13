package utpc.utils;

import genetic.Individual;

import java.util.HashSet;

public class Term {
    String term;
    public HashSet<String> literals = new HashSet<>();
    boolean isfalse = false;
    boolean termValue = true;
    private Scanner sc;

    public Term(String term, Scanner sc){
        this.term = term;
        this.sc = sc;
        setLiterals();
    }

    public boolean getIsConstant(){
        return this.isfalse;
    }

    public String getTerm(){
        return this.term;
    }

    public void setLiterals(){
        String symbol = "~";
        for(int j = 0;j<term.length();j++){
            String temp_literal = term.charAt(j) + "";
            if(symbol.equals(temp_literal)){
                if(literals.contains(term.charAt(j+1)+"")){
                    isfalse = true;
                }
                literals.add("~"+term.charAt(j+1)+"");
                j++;
            } else {
                if(literals.contains("~"+term.charAt(j))){
                    isfalse = true;
                }
                literals.add(term.charAt(j)+"");
            }
        }
    }

    //计算当前蕴涵项的真值
    public boolean getTermValue(Individual individual){
        termValue = true;
        boolean literalValue;
        if(!isfalse){
            String current_literal;
            for(String literal:literals) {
                if(literal.contains("~")) {
                    current_literal = literal.charAt(1)+"";
                    literalValue = !individual.getChromosome().get(current_literal);
                } else {
                    literalValue = individual.getChromosome().get(literal);
                }
                termValue = termValue && literalValue;
            }
        } else {
            termValue = false;
        }
        return termValue;
    }
}
