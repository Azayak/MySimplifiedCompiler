package LexicalAnalyzer;

import LexicalAnalyzer.pojo.NFA;
import LexicalAnalyzer.pojo.NFANode;

/**
 * @author zhangjianhao
 *包含了4中不同符号下的NFA操作方法
 */
public class GenerateNFAMethod {
    GetStateNumber getStateNumber = new GetStateNumber();
    char null_ = '~'; //NFA转换条件为空

    /**
     * 遇到非符号数时新建NFA，包含起点和终点
     * @param nonSymbol
     * @return 新生成的NFA
     */
    public NFA NonSymbol(char nonSymbol){
        NFANode headNode = new NFANode(getStateNumber.getCurNFAStateNumber(),null_);
        NFANode tailNode = new NFANode(getStateNumber.getCurNFAStateNumber(),nonSymbol);
        headNode.nextNodes.add(tailNode);
        NFA newNFA = new NFA(headNode,tailNode);
        return newNFA;
    }

    /**遇到闭包符号时在原来的NFA上增加头尾结点并互相连接
     * @param oldNFA 原NFA
     * @return newNFA 增加头尾结点并连接后的新NFA
     */
    public NFA StarSymbol(NFA oldNFA){
        NFANode oldHeadNode = oldNFA.headNode;
        NFANode oldTailNode = oldNFA.tailNode;
        NFANode newHeadNode = new NFANode(getStateNumber.getCurNFAStateNumber(),null_);
        NFANode newTailNode = new NFANode(getStateNumber.getCurNFAStateNumber(),null_);
//        oldTailNode.nextNodes.add(newHeadNode);
//        newHeadNode.nextNodes.add(newTailNode);
//        newTailNode.nextNodes.add(oldHeadNode);
//        oldHeadNode.nextNodes.add(oldTailNode);
        newHeadNode.nextNodes.add(oldHeadNode);
        newHeadNode.nextNodes.add(newTailNode);
        oldTailNode.nextNodes.add(newTailNode);
        oldTailNode.nextNodes.add(oldHeadNode);
        NFA newNFA = new NFA(newHeadNode,newTailNode);
        return newNFA;
    }

    /**
     * 当读入正常的连接符号时，将两个NFA合并为一个新的NFA
     * @param firstNFA 靠前的NFA
     * @param secondNFA 靠后的NFA
     * @return newNFA 新的NFA
     */
    public NFA AndSymbol(NFA firstNFA,NFA secondNFA){
        NFANode newHeadNode = new NFANode(getStateNumber.getCurNFAStateNumber(),null_);
        NFANode newTailNode = new NFANode(getStateNumber.getCurNFAStateNumber(),null_);
        firstNFA.tailNode.nextNodes.add(secondNFA.headNode);
        newHeadNode.nextNodes.add(firstNFA.headNode);
        secondNFA.tailNode.nextNodes.add(newTailNode);
        NFA newNFA = new NFA(newHeadNode,newTailNode);
        return newNFA;
    }

    /**
     * 当读入或符号时，将两个NFA合并为一个新的NFA
     * @param firstNFA 靠前的NFA
     * @param secondNFA 靠后的NFA
     * @return newNFA 新的NFA
     */
    public NFA OrSymbol(NFA firstNFA,NFA secondNFA){
        NFANode newHeadNode = new NFANode(getStateNumber.getCurNFAStateNumber(),null_);
        NFANode newTailNode = new NFANode(getStateNumber.getCurNFAStateNumber(),null_);
        newHeadNode.nextNodes.add(firstNFA.headNode);
        newHeadNode.nextNodes.add(secondNFA.headNode);
        firstNFA.tailNode.nextNodes.add(newTailNode);
        secondNFA.tailNode.nextNodes.add(newTailNode);
        NFA newNFA = new NFA(newHeadNode,newTailNode);
        return newNFA;
    }



}
