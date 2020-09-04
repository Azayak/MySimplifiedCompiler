package LexicalAnalyzer.pojo;

/**一个由NFANode组成的NFA状态机
 * 用于进行正规式的识别
 * @author zhangjianhao
 */
public class NFA {
    public NFANode headNode;
    public NFANode tailNode;
    public NFA(NFANode headNode,NFANode tailNode){
        this.headNode = headNode;
        this.tailNode = tailNode;
    }

//    @Override
//    public String toString(){
//
//        return "";
//    }
}
