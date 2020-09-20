package LexicalAnalyzer;

import LexicalAnalyzer.pojo.NFA;
import LexicalAnalyzer.pojo.NFAStack;
import LexicalAnalyzer.pojo.SymbolStack;

/**
 * 根据输入的字符串生成符号栈
 * 根据符号栈内容生成NFA存储在NFAStack中
 * 根据不同操作符对NFA进行结合
 * 在最终NFAStack中剩余一个NFA时代表生成了最终的NFA
 */
public class ToNFA {

    public NFA getNFA(String input){
        GenerateNFAMethod generateNFAMethod = new GenerateNFAMethod();

        //建立存储NFA的栈
        NFAStack nfaStack = new NFAStack(null);
        SymbolStack symbolStack = new SymbolStack('#');

//        NFAStack nfaHead = nfaStack;
//        SymbolStack symbolHead = symbolStack;

        for (int i = 0; i < input.length(); i++) {
//            System.out.println(i);
//            if(i>1)
//                NFANode.printNFANode(nfaStack.getTop().headNode);
            char ch = input.charAt(i);
            switch (ch){
                case '(':
                    symbolStack.push('(');
                    break;
                case '|':
                    while(symbolStack.getTop()=='.'){
                        NFA secondNFA = nfaStack.getTop();
                        nfaStack.pop();
                        NFA firstNFA = nfaStack.getTop();
                        nfaStack.pop();
                        NFA newAndNFA = generateNFAMethod.AndSymbol(firstNFA,secondNFA);
                        nfaStack.push(newAndNFA);
                        symbolStack.pop();
                    }
                    symbolStack.push('|');
                    break;
                case '*':
                    NFA topNFA = nfaStack.getTop();
                    nfaStack.pop();;
                    NFA newNFA = generateNFAMethod.StarSymbol(topNFA);
                    nfaStack.push(newNFA);
                    //在符号之间添加and符号
                    if(i!=input.length()-1&&input.charAt(i+1)!='|'&&input.charAt(i+1)!=')'){
                        symbolStack.push('.');
                    }
                    break;
                case ')':
                    while(symbolStack.getTop()!='('){
                        NFA secondNFA = nfaStack.getTop();
                        nfaStack.pop();
                        NFA firstNFA = nfaStack.getTop();
                        nfaStack.pop();
                        if(symbolStack.getTop()=='.'){
                            NFA newAndNFA = generateNFAMethod.AndSymbol(firstNFA,secondNFA);
                            nfaStack.push(newAndNFA);
                        }
                        else{
                            NFA newOrNFA = generateNFAMethod.OrSymbol(firstNFA,secondNFA);
                            nfaStack.push(newOrNFA);
                        }
                        symbolStack.pop();
                    }
                    symbolStack.pop();
                    if(i!=input.length()-1&&input.charAt(i+1)!='|'&&input.charAt(i+1)!=')'&&input.charAt(i+1)!='*'){
                        symbolStack.push('.');
                    }
                    break;
                default:
                    NFA nonSymbolNFA = generateNFAMethod.NonSymbol(ch);
                    if(i!=input.length()-1&&input.charAt(i+1)!='|'&&input.charAt(i+1)!='*'&&input.charAt(i+1)!=')'){
                        symbolStack.push('.');
                    }
                    nfaStack.push(nonSymbolNFA);
                    break;
            }
        }

//        System.out.println("loop end");

//        SymbolStack.printStack(symbolStack);
        //字符串完全读入后，根据符号栈中符号对NFA栈中NFA进行合并操作
        while(symbolStack.getTop()!='#'){
            char symbol = symbolStack.getTop();
            symbolStack.pop();
            NFA secondNFA = nfaStack.getTop();
            nfaStack.pop();
            NFA firstNFA = nfaStack.getTop();
            nfaStack.pop();
            switch (symbol){
                case '|':
                    NFA newOrNFA = generateNFAMethod.OrSymbol(firstNFA,secondNFA);
                    nfaStack.push(newOrNFA);
                    break;
                case '.':
                    NFA newAndNFA = generateNFAMethod.AndSymbol(firstNFA,secondNFA);
                    nfaStack.push(newAndNFA);
                    break;
            }
        }
//        System.out.println("loop end");
//        SymbolStack.printStack(symbolStack);
        return nfaStack.getTop();
    }



    public static void main(String[] args){
        ToNFA toNFA = new ToNFA();
//        String testInput = "(((-)(d)(d)*(.)(d)*)|(d)*|(-)(d)*|((d)(d)*(.)(d)*))";

        String testInput = "1111*";
//        String testInput = "1*1|0";
//        String testInput = "1*111*";
//        String testInput = "(0|1|6)|(2|3)|(4|5)";


        NFA resNFA = toNFA.getNFA(testInput);
    }

}
