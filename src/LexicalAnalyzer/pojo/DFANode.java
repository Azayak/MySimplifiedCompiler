package LexicalAnalyzer.pojo;

/**
 * 描述DFA中的状态转换结点
 */
public class DFANode {
    public DFAState beginState;
    public DFAState endState;
    public char pathSymbol;

    public DFANode(DFAState beginState,DFAState endState,char pathSymbol){
        this.beginState = beginState;
        this.endState = endState;
        this.pathSymbol = pathSymbol;
    }
}
