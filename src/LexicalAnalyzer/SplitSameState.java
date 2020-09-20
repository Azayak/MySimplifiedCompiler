package LexicalAnalyzer;

import LexicalAnalyzer.pojo.DFANode;
import LexicalAnalyzer.pojo.DFAState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SplitSameState {

    public ArrayList<DFANode> reduceDFA(ArrayList<DFANode> oldDFANodes, ArrayList<ArrayList<String>> sameStates) {

        for (ArrayList<String> sameState : sameStates) {
            if (sameState.size() > 1) {
                String DFAStateName = sameState.get(0);

                DFAState dfaState = new DFAState("temp",new ArrayList<>(),false,false);
                for (int j = 0; j < oldDFANodes.size(); j++) {
                    if (oldDFANodes.get(j).beginState.stateString.equals(DFAStateName)) {
                        dfaState = oldDFANodes.get(j).beginState;
                    }
                }

                for (int i = 1; i < sameState.size(); i++) {
                    String dupDFAStateName = sameState.get(i);

                    //合并状态
                    for (int j = 0; j < oldDFANodes.size(); j++) {
                        if (oldDFANodes.get(j).endState.stateString.equals(dupDFAStateName)) {
                            oldDFANodes.get(j).endState = dfaState;
                        }
                    }

                    //删除多余状态
                    for (int j = 0; j < oldDFANodes.size(); j++) {
                        if (oldDFANodes.get(j).beginState.stateString.equals(dupDFAStateName)) {
                            oldDFANodes.remove(j);
                            j--;
                        }
                    }
                }
            }
        }

        return oldDFANodes;
    }


//    定义分割法合并等价状态的String集合
    public ArrayList<ArrayList<String>> splitSameState(ArrayList<DFANode> oldDFANodes, String regExp) {
        //划分最终的状态集
        ArrayList<ArrayList<String>> splitStates = new ArrayList<>();
        ArrayList<ArrayList<String>> resultSplitStates = new ArrayList<>();
        //划分终态和非终态
        ArrayList<String> terminalState = new ArrayList<>();
        ArrayList<String> nonterminalState = new ArrayList<>();
        //获取非运算符字符集
        ArrayList<Character> characterList = getStateStr.getCharacters(regExp);
        for (DFANode node : oldDFANodes) {
            if (node.beginState.isEnd) {
                if (!terminalState.contains(node.beginState.stateString))
                    terminalState.add(node.beginState.stateString);
            }
            else {
                if(!nonterminalState.contains(node.beginState.stateString))
                    nonterminalState.add(node.beginState.stateString);
            }
        }

        if(terminalState.size()>0)
            splitStates.add(terminalState);
        if(nonterminalState.size()>0)
            splitStates.add(nonterminalState);
        while (!splitStates.isEmpty()) {
            ArrayList<String> currentState = splitStates.get(0);

            //初状态指向末状态的map，键为已存在状态，值为新分裂出来的状态
            Map<ArrayList<String>, ArrayList<String>> aimStateTypeList = new HashMap<>();
            for (Character cha : characterList) {

                //?????????
//                ArrayList<String> diffDFANodes = new ArrayList<>();

                for (int i = 0; i < currentState.size(); i++) {
                    String oldState = currentState.get(i);

                    int count = 0;

                    for (DFANode node : oldDFANodes) {

                        //找到当前节点对应的转换路径，加入以状态节点集合为键值的map中
                        if (node.beginState.stateString.equals(oldState) && node.pathSymbol == cha) {
                            ArrayList<String> endStateList = getContainArrayList(splitStates,node.endState.stateString);
                            if (aimStateTypeList.containsKey(endStateList)) {
                                aimStateTypeList.get(endStateList).add(oldState);
                            }
                            else{
                                ArrayList<String> temp = new ArrayList<>();
                                temp.add(oldState);
                                aimStateTypeList.put(endStateList, temp);
                            }
                        }



                        //?????????
//                        if (node.beginState.stateString.equals(oldState) && node.pathSymbol != cha) {
//                            diffDFANodes.add(oldState);
//                            currentState.remove(i);
//                            count++;
////                            i--;
//                        }
                    }
//                    i -= lkcount;
                }

                //?????????????
//                if (diffDFANodes.size() > 0) {
//                    aimStateTypeList.put(new ArrayList<String>(), diffDFANodes);
//                }




                //如果map的大小为1说明对于当前字符来说，这个转换是到相同状态,重置后继续对下一个字符转换进行判断,否则直接break
                if (aimStateTypeList.size() == 1) {
                    aimStateTypeList = new HashMap<>();
                    continue;
                }
                else{
                    break;
                }
            }
            //判断ArrayList的长度是否为0，如果为0，说明当前的状态均为同一个状态，将该状态从splitStates中移除并加入最终的状态集
            if (aimStateTypeList.size() == 0) {
                resultSplitStates.add(currentState);
                splitStates.remove(0);
            }
            //否则移出旧状态，将map中的新状态均加入splitStates中
            else{
                splitStates.remove(0);
                for (ArrayList<String> newList : aimStateTypeList.values()) {
                    splitStates.add(newList);
                }
            }
        }
        return resultSplitStates;
    }


    //找到包含当前状态的那一个切分子集
    private ArrayList<String> getContainArrayList(ArrayList<ArrayList<String>> splitStates, String stateStr) {
        for (ArrayList<String> states : splitStates) {
            if (states.contains(stateStr)) {
                return states;
            }
        }
        return null;
    }

    //定义分割法合并等价状态的String集合
//    public ArrayList<ArrayList<String>> splitSameState(ArrayList<DFANode> oldDFANodes,String regExp){
//        //划分最终的状态集
//        ArrayList<ArrayList<String>> splitStates = new ArrayList<>();
//        ArrayList<ArrayList<String>> resultSplitStates = new ArrayList<>();
//        //划分终态和非终态
//        ArrayList<String> terminalState = new ArrayList<>();
//        ArrayList<String> nonterminalState = new ArrayList<>();
//        //获取非运算符字符集
//        ArrayList<Character> characterList = getStateStr.getCharacters(regExp);
//        for(DFANode node:oldDFANodes){
//            if(node.beginState.isEnd){
//                if(!terminalState.contains(node.beginState.stateString))
//                    terminalState.add(node.beginState.stateString);
//            }
//            else{
//                if(!nonterminalState.contains(node.beginState.stateString))
//                    nonterminalState.add(node.beginState.stateString);
//            }
//        }
//        if(terminalState.size()>0)
//            splitStates.add(terminalState);
//        if(nonterminalState.size()>0)
//            splitStates.add(nonterminalState);
//        while(!splitStates.isEmpty()){
//            ArrayList<String> currentState = splitStates.get(0);
//            //初状态指向末状态的map，键为已存在状态，值为新分裂出来的状态
//            Map<ArrayList<String>,ArrayList<String>> aimStateTypeList= new HashMap<>();
//            for(Character cha:characterList){
//                for(String oldState:currentState){
//                    for(DFANode node:oldDFANodes){
//                        //找到当前节点对应的转换路径，加入以状态节点集合为键值的map中
//                        if(node.beginState.stateString.equals(oldState)&&node.pathSymbol==cha){
//                            ArrayList<String> endStateList = getContainArrayList(splitStates,node.endState.stateString);
//                            if(aimStateTypeList.containsKey(endStateList)){
//                                aimStateTypeList.get(endStateList).add(oldState);
//                            }
//                            else{
//                                ArrayList<String> temp = new ArrayList<>();
//                                temp.add(oldState);
//                                aimStateTypeList.put(endStateList, temp);
//                            }
//                        }
//                    }
//                }
//                //如果map的大小为1说明对于当前字符来说，这个转换是到相同状态,重置后继续对下一个字符转换进行判断,否则直接break
//                if(aimStateTypeList.size()==1){
//                    aimStateTypeList= new HashMap<>();
//                    continue;
//                }
//                else{
//                    break;
//                }
//            }
//            //判断ArrayList的长度是否为0，如果为0，说明当前的状态均为同一个状态，将该状态从splitStates中移除并加入最终的状态集
//            if(aimStateTypeList.size()==0){
//                resultSplitStates.add(currentState);
//                splitStates.remove(0);
//            }
//            //否则移出旧状态，将map中的新状态均加入splitStates中
//            else{
//                splitStates.remove(0);
//                for(ArrayList<String> newList:aimStateTypeList.values()){
//                    splitStates.add(newList);
//                }
//            }
//        }
//        return resultSplitStates;
//    }






}
