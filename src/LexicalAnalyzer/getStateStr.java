package LexicalAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class getStateStr {

    public static int state = 0;

    //获取正则表达式中的除运算符外的字符
//    ArrayList<Character> characterList = getStateStr.getCharacters(regExp);

    public static ArrayList<Character> getCharacters(String regExp){
        ArrayList<Character> res = new ArrayList<>();
        for (int i = 0; i < regExp.length(); i++) {
            res.add(regExp.charAt(i));
        }
        return res;
    }

    public static String getStateStr() {
        StringBuilder sb = new StringBuilder();
        sb.append((char) ('A' + state));
        state++;
        return sb.toString();
    }

}
