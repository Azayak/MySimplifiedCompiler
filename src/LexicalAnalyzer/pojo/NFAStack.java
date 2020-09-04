package LexicalAnalyzer.pojo;

/**
 * NFA栈，用于词法分析过程中
 * NFA的识别以及合成
 * 当最后栈中只有一个NFA时代表识别成功
 */
public class NFAStack {
    public NFA currentNFA;
    public NFAStack nextNFA;

    public NFAStack(NFA currentNFA){
        this.currentNFA = currentNFA;
        this.nextNFA = null;
    }

    public void pop(){
        NFA res;
        NFAStack temp = this;
        NFAStack last = this;
        if(temp.nextNFA==null){
            System.out.println("NFA栈为空");
            //返回错误信息
//            return NFA();
        }
        while (temp.nextNFA!=null){
            last = temp;
            temp = temp.nextNFA;
        }
//        res = last.nextNFA.currentNFA;
        last.nextNFA = null;
//        return res;
    }

    public void push(NFA currentNFA){
        NFAStack temp = this;
        while(temp.nextNFA!=null)
            temp = temp.nextNFA;
        temp.nextNFA = new NFAStack(currentNFA);
    }

    public void push(NFAStack currentNFA){
        NFAStack temp = this;
        while(temp.nextNFA!=null)
            temp = temp.nextNFA;
        temp.nextNFA = currentNFA;
    }

    public NFA getTop(){
        NFA res;
        NFAStack temp = this;
        while(temp.nextNFA!=null)
            temp = temp.nextNFA;
        res = temp.currentNFA;
        return res;
    }
}
