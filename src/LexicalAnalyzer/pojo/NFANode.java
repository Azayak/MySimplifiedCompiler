package LexicalAnalyzer.pojo;

import java.util.ArrayList;

/**是NFA中的结点，包含自身状态标记以及进入本结点的字符
 * @author zhangjianhao
 */
public class NFANode {
    public int stateNumber;
    public char pathSymbol;
    public ArrayList<NFANode> nextNodes;

    public NFANode(int stateNumber,char pathSymbol){
        this.stateNumber = stateNumber;
        this.pathSymbol = pathSymbol;
        this.nextNodes = new ArrayList<NFANode>();
    }


//    public static void printNFANode(NFANode nfaNode){
//        for(int i=0;i<nfaNode.nextNodes.size();i++){
//            System.out.println(nfaNode.stateNumber+"->"+nfaNode.nextNodes.get(i).stateNumber+"    "+nfaNode.nextNodes.get(i).pathSymbol);
//            printNFANode(nfaNode.nextNodes.get(i));
//        }
//    }


//    @Override
//    public String toString(){
//
//        return "";
//    }
}
