package LexicalAnalyzer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LexMain {

    Map<State, Map<CharType, State>> trans = new HashMap<>();

    Map<String, Integer> keyWords = new HashMap<>();


    public void mapGenerate() {
        Map<CharType, State> initMap = new HashMap<CharType, State>() {{
            put(CharType.Char_alpha, State.State_Word);
            put(CharType.Char_Num, State.State_Int);
            put(CharType.Char_End, State.State_End);
            put(CharType.Char_Plus, State.State_Plus);
            put(CharType.Char_Sub, State.State_Sub);
            put(CharType.Char_Mult, State.State_Mult);
            put(CharType.Char_Div, State.State_Div);
        }};
        trans.put(State.State_Init, initMap);

        Map<CharType, State> wordMap = new HashMap<CharType, State>() {{
            put(CharType.Char_alpha, State.State_Word);
            put(CharType.Char_Num, State.State_Word);
        }};
        trans.put(State.State_Word, wordMap);

        Map<CharType, State> plusMap = new HashMap<CharType, State>() {{
            put(CharType.Char_Num, State.State_Int);
        }};
        trans.put(State.State_Plus, plusMap);

        Map<CharType, State> subMap = new HashMap<CharType, State>() {{
            put(CharType.Char_Num, State.State_Int);
        }};
        trans.put(State.State_Sub, subMap);

        Map<CharType, State> intMap = new HashMap<CharType, State>() {{
            put(CharType.Char_Point, State.State_Point);
            put(CharType.Char_Num, State.State_Int);
        }};
        trans.put(State.State_Int, intMap);

        Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
            put(CharType.Char_Num, State.State_Float);
        }};
        trans.put(State.State_Point, pointMap);

        Map<CharType, State> floatMap = new HashMap<CharType, State>() {{
            put(CharType.Char_Num, State.State_Float);
        }};
        trans.put(State.State_Float, floatMap);
    }

    public void getKeyWordsMap() throws IOException {
        String path = "D:\\Onedrive\\OneDrive - stu.xjtu.edu.cn\\学业\\编译原理\\SimplifiedCompiler\\keywords.txt";
        File file = new File(path);
        InputStream in = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(input);
        String s;
        while ((s = br.readLine()) != null) {
//            System.out.println(s);
            String[] strings = s.split(" ");
            String keyword = strings[0];
            int num = Integer.parseInt(strings[1]);
            keyWords.put(keyword, num);
        }
    }



    public static void main(String[] args) throws IOException {
        LexMain solve = new LexMain();
        solve.mapGenerate();
        solve.getKeyWordsMap();

        String path = "D:\\Onedrive\\OneDrive - stu.xjtu.edu.cn\\学业\\编译原理\\SimplifiedCompiler\\inputTest.txt";
        File file = new File(path);
        InputStream in = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(input);
        String s;
        while ((s = br.readLine()) != null) {
            String[] subs = s.split(" ");

            for (String sub : subs) {
                State state = State.State_Init;
                int length = sub.length();

                for (int i = 0; i < length; i++) {
                    CharType type = solve.getCharType(sub.charAt(i));
                    if (!solve.trans.get(state).containsKey(type)) {
                        System.out.println("Error");
                        System.exit(-1);
                    }
                    else {
                        state = solve.trans.get(state).get(type);
                    }
                }
//                System.out.println(state);
                if (state == State.State_Word) {
                    if (solve.keyWords.containsKey(sub)) {
                        System.out.println(solve.keyWords.get(sub) + "         " + sub); //预留关键字
                    }else {
                        System.out.println(38 + "         " + sub); //用户自定义字段
                    }
                }
                else if (state == State.State_Int) {
                    System.out.println(39 + "         " + sub);
                }
                else if (state == State.State_Float) {
                    System.out.println(40 + "         " + sub);
                }
                else if (state == State.State_End) {
                    System.out.println(41 + "         " + sub);
                }
                else if (state == State.State_Plus) {
                    System.out.println(42 + "         " + sub);
                }
                else if (state == State.State_Sub) {
                    System.out.println(43 + "         " + sub);
                }
                else if (state == State.State_Mult) {
                    System.out.println(44 + "         " + sub);
                }
                else if (state == State.State_Div) {
                    System.out.println(45 + "         " + sub);
                }
                else {
                    System.out.println("ERROR");
                    System.exit(-1);
                }
            }

        }


    }



    public CharType getCharType(char ch) {
        if (ch >= '0' && ch <= '9') {
            return CharType.Char_Num;
        }
        else if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || ch == '_') {
            return CharType.Char_alpha;
        }
        else if (ch == '(' || ch == ')' || ch == '[' || ch == ']' || ch == '{' || ch == '}' || ch == ',' || ch == ';' || ch == '=') {
            return CharType.Char_End;
        }
        else if (ch == '.') {
            return CharType.Char_Point;
        }
        else if (ch == ' ') {
            return CharType.Char_Space;
        }
        else if (ch == '+') {
            return CharType.Char_Plus;
        }
        else if (ch == '-') {
            return CharType.Char_Sub;
        }
        else if (ch == '*') {
            return CharType.Char_Mult;
        }
        else if (ch == '/') {
            return CharType.Char_Div;
        }
        else {
            return CharType.Char_Error;
        }
    }


    enum CharType {
        Char_Num,
        Char_alpha,
        Char_End,
        Char_Point,
        Char_Space,
        Char_Plus,
        Char_Sub,
        Char_Mult,
        Char_Div,
        Char_Error
    }

    enum State {
        State_Init,
        State_Word,
        State_Int,
        State_Point,
        State_Float,
        State_Plus,
        State_Sub,
        State_Mult,
        State_Div,
        State_End,
        State_Error
    }

}
