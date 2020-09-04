package LexicalAnalyzer;

import LexicalAnalyzer.pojo.DFANode;
import LexicalAnalyzer.pojo.NFA;

import java.io.*;
import java.util.ArrayList;

public class GenerateDFA {

    public static void main(String[] args) throws IOException {

//        String testInput = "1*1|0";
//        String regExp = "01";

//        String testInput = "(0|1|6)|(2|3)|(4|5)";
//        String regExp = "0123456";

//        String testInput = "a(a*|b*)a|b*";
//        String regExp = "ab";

//        String testInput = "l(l|d)*";
//        String regExp = "ld";

//        String letter = "A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z|a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|_";
//        String digit = "0|1|2|3|4|5|6|7|8|9";

        String letter = "l";
        String digit = "d";
        String id = "("+letter+")"+ "("+ "("+letter+")" + "|" + "("+digit+")"+ ")"+"*";
        String digits = "("+digit+")" + "("+digit+")"+"*";
        //@代表小数点
        String fraction = "@"+digits;
//        String exp = "E" + "(+|-|~)" +digits;
//        String num = digits + "("+fraction+ "|" + "~"+")" + "("+exp+ "|" + "~"+")";

        String num = digits + "("+fraction+ "|" + "~"+")";

        String idAndNum = id + "|" + num;
//        String testInput = idAndNum;
//        String regExp = "ld@";


        String testInput = "";
        String regExp = "";

        //根据正规式生产NFA
        ToNFA toNFA = new ToNFA();
        NFA resNFA = toNFA.getNFA(testInput);

        //将生成的NFA转换为DFA
        NFAToDFA nfaToDFA = new NFAToDFA();
        ArrayList<DFANode> resDFA = nfaToDFA.ToDFA(resNFA, regExp);

        //去除生成DFA中的无效状态
        EliminateNoUseState eliminateNoUseState = new EliminateNoUseState();
        ArrayList<DFANode> newDFA = eliminateNoUseState.eliminateNoUseState(resDFA);

        //合并DFA中的等价状态
        SplitSameState splitSameState = new SplitSameState();
        ArrayList<ArrayList<String>> res = splitSameState.splitSameState(newDFA, regExp);
        ArrayList<DFANode> miniDFA = splitSameState.reduceDFA(newDFA, res);

        String path = "D:\\Onedrive\\OneDrive - stu.xjtu.edu.cn\\学业\\编译原理\\SimplifiedCompiler\\miniDFA.txt";
        File file = new File(path);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.flush();

        for (int i = 0; i < miniDFA.size(); i++) {
            DFANode dfaNode = miniDFA.get(i);
            String dfaNodeDesc = dfaNode.beginState.stateString+" "+dfaNode.beginState.isBegin+" "+dfaNode.beginState.isEnd+" "+dfaNode.endState.stateString+" "+dfaNode.endState.isBegin+" "+dfaNode.endState.isEnd+" "+dfaNode.pathSymbol;
            byte[] dfaNodeDescArray = dfaNodeDesc.getBytes();
            outputStream.write(dfaNodeDescArray);
            if (i < miniDFA.size() - 1) {
                outputStream.write("\n".getBytes());
            }
        }
        outputStream.close();

    }
}
