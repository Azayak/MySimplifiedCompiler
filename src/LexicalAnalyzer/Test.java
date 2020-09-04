package LexicalAnalyzer;

import LexicalAnalyzer.pojo.DFANode;
import LexicalAnalyzer.pojo.NFA;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        ToNFA toNFA = new ToNFA();

//        String testInput = "1*1|0";
//        String testInput = "(0|1|6)|(2|3)|(4|5)";

        String testInput = "a(a*|b*)a|b*";
        String regExp = "ab";

//        String testInput = "l(l|d)*";
//        String regExp = "ld";

        NFA resNFA = toNFA.getNFA(testInput);

        NFAToDFA nfaToDFA = new NFAToDFA();

        ArrayList<DFANode> resDFA = nfaToDFA.ToDFA(resNFA, regExp);

        for (DFANode dfaNode : resDFA) {
            System.out.print("BeginState: "+dfaNode.beginState.stateString+"  "+dfaNode.beginState.isBegin+"    "+dfaNode.beginState.isEnd+"   EndState: "+dfaNode.endState.stateString+"  "+dfaNode.endState.isBegin+"   "+dfaNode.endState.isEnd+"   Symbol: "+dfaNode.pathSymbol);
            System.out.println();
        }

        System.out.println("****************************************");


        EliminateNoUseState eliminateNoUseState = new EliminateNoUseState();

        ArrayList<DFANode> newDFA = eliminateNoUseState.eliminateNoUseState(resDFA);

        for (DFANode dfaNode : newDFA) {
            System.out.print("BeginState: "+dfaNode.beginState.stateString+"  "+dfaNode.beginState.isBegin+"    "+dfaNode.beginState.isEnd+"   EndState: "+dfaNode.endState.stateString+"  "+dfaNode.endState.isBegin+"   "+dfaNode.endState.isEnd+"   Symbol: "+dfaNode.pathSymbol);
            System.out.println();
        }


        System.out.println("****************************************");

        SplitSameState splitSameState = new SplitSameState();
        ArrayList<ArrayList<String>> res = splitSameState.splitSameState(newDFA, regExp);

        for (ArrayList<String> arrayList : res) {
            for (String s : arrayList) {
                System.out.print(s + "   ");
            }
            System.out.println();
        }


        System.out.println("****************************************");

        ArrayList<DFANode> miniDFA = splitSameState.reduceDFA(newDFA, res);

        for (DFANode dfaNode : miniDFA) {
            System.out.print("BeginState: "+dfaNode.beginState.stateString+"  "+dfaNode.beginState.isBegin+"    "+dfaNode.beginState.isEnd+"   EndState: "+dfaNode.endState.stateString+"  "+dfaNode.endState.isBegin+"   "+dfaNode.endState.isEnd+"   Symbol: "+dfaNode.pathSymbol);
            System.out.println();
        }

    }
}
