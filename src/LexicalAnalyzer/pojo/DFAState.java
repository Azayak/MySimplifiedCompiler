package LexicalAnalyzer.pojo;

import java.util.ArrayList;

/**
 * DFA状态机
 * stateString化简后状态名称
 * NFAStateNumber划分的NFA状态集
 * isBegin是否为开始结点
 * isEnd是否为末结点
 */
public class DFAState {
    public String stateString;
    public ArrayList<Integer> NFAStateNumber;
    public boolean isBegin;
    public boolean isEnd;

    public DFAState(String stateString,ArrayList<Integer> NFAStateNumber,boolean isBegin,boolean isEnd){
        this.stateString = stateString;
        this.NFAStateNumber = NFAStateNumber;
        this.isBegin = isBegin;
        this.isEnd = isEnd;
    }
}
