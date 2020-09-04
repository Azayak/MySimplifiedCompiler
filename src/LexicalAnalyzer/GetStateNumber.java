package LexicalAnalyzer;

public class GetStateNumber {
    private static int NFAStateNumber = 1;
    private static int DFAStateNumber = 1;
    int curNFAStateNumber;
    int curDFAStateNumber;

    public int getCurNFAStateNumber() {
        this.curNFAStateNumber = NFAStateNumber;
        NFAStateNumber++;
        return curNFAStateNumber;
    }

    public int getNFASum(){
        return NFAStateNumber;
    }

    public int getCurDFAStateNumber() {
        this.curDFAStateNumber = DFAStateNumber;
        DFAStateNumber++;
        return curDFAStateNumber;
    }
}
