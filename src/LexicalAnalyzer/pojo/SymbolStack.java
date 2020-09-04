package LexicalAnalyzer.pojo;

/**
 * 符号栈，用于扫描字符串时对其中字符进行存储
 */
public class SymbolStack {
    public char currentSymbol;
    public SymbolStack nextSymbol;

    public SymbolStack(char currentSymbol){
        this.currentSymbol = currentSymbol;
        this.nextSymbol = null;
    }

    public void pop(){
        char res;
        SymbolStack temp = this;
        SymbolStack last = this;
        if(temp.nextSymbol==null){
            System.out.println("符号栈为空");
//            return '!';
        }
        while(temp.nextSymbol!=null){
            last = temp;
            temp = temp.nextSymbol;
        }
        res = last.nextSymbol.currentSymbol;
        last.nextSymbol = null;
//        return res;
    }

    public void push(char currentSymbol){
        SymbolStack temp = this;
        while(temp.nextSymbol!=null)
            temp = temp.nextSymbol;
        temp.nextSymbol = new SymbolStack(currentSymbol);
    }

    public void push(SymbolStack currentSymbol){
        SymbolStack temp = this;
        while(temp.nextSymbol!=null)
            temp = temp.nextSymbol;
        temp.nextSymbol = currentSymbol;
    }

    public char getTop(){
        SymbolStack temp = this;
        while(temp.nextSymbol!=null)
            temp = temp.nextSymbol;
        return temp.currentSymbol;
    }

    public static void printStack(SymbolStack symbolStack){
        SymbolStack temp = symbolStack;
        while(symbolStack.nextSymbol!=null){
            System.out.println(symbolStack.currentSymbol);
            symbolStack = symbolStack.nextSymbol;
        }
    }
}
