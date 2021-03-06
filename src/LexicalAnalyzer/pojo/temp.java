//package LexicalAnalyzer.pojo;
//
//import java.util.ArrayList;
//
//public class temp {
//
//    public void unknownMethod(){
//    //返回最终的DFA状态转换节点
//    ArrayList<DFANode> resultDFANodes = new ArrayList<>();
//    //记录NFA状态图中的起始状态和终止状态
//    int beginNFAState = resultNFA.headNode.stateNum;
//    int endNFAState = resultNFA.tailNode.stateNum;
//    //获取正则表达式中的除运算符外的字符
//    ArrayList<Character> characterList = getStateStr.getCharacters(regExp);
//    //获取起始节点通过控制所能到达的左右状态节点
//    ArrayList<NFANode> initialState = new ArrayList<>();
//        initialState.add(resultNFA.headNode);
//    ArrayList<NFANode> tempState =  findNulMatchNFANodes(initialState,new ArrayList<NFANode>());
//    //建立一个list表示已有的未标记的状态，其中元素为含有NFANode的list
//    ArrayList<ArrayList<NFANode>> unsignedState = new ArrayList<>();
//    ArrayList<ArrayList<Integer>> unsignedStateNums = new ArrayList<>();
//    //建立一个Map表示存储已产生的状态，键为list，值表示状态名；用来查找现有状态的状态名
//    Map<ArrayList<Integer>,String> existState = new HashMap<ArrayList<Integer>,String>();
//        unsignedState.add(tempState);
//        unsignedStateNums.add(getStateNumList(tempState));
//        existState.put(getStateNumList(tempState), getStateStr.getStateStr());
//        while(!unsignedState.isEmpty()){
//        DFAState beginState = new DFAState(existState.get(getStateNumList(unsignedState.get(0))),getStateNumList(unsignedState.get(0)),testIsBegin(beginNFAState,getStateNumList(unsignedState.get(0))),testIsEnd(endNFAState,getStateNumList(unsignedState.get(0))));
//        for(Character cha:characterList){
//            ArrayList<NFANode> nextState = findNewNFAStateSet(unsignedState.get(0),cha);
//            ArrayList<Integer> tempIntegerList = getStateNumList(nextState);
//            //已有的状态集中不含有当前状态则新建一个状态
//            if(!existState.containsKey(tempIntegerList)){
//                existState.put(tempIntegerList, getStateStr.getStateStr());
//            }
//            DFAState endState = new DFAState(existState.get(tempIntegerList),tempIntegerList,testIsBegin(beginNFAState,tempIntegerList),testIsEnd(endNFAState,tempIntegerList));
//            DFANode tempDFANode = new DFANode(beginState,endState,cha);
//            resultDFANodes.add(tempDFANode);
//            if(!unsignedStateNums.contains(tempIntegerList)){
//                unsignedState.add(nextState);
//                unsignedStateNums.add(tempIntegerList);
//            }
//        }
//        unsignedState.remove(0);
//    }
//
//        return resultDFANodes;
//}
//
//    //输入旧状态节点集合和转换字符，输出新状态节点集合
//    public ArrayList<NFANode> findNewNFAStateSet(ArrayList<NFANode> oldNFAStateSet, char pathChar){
//        ArrayList<NFANode> newNFAStateSet = new ArrayList<>();//记录最终返回的NFANode状态集
//        if(oldNFAStateSet.size()==0){
//            return newNFAStateSet;
//        }
//        //先找到匹配的状态节点加入matchNodes中
//        ArrayList<NFANode> matchNodes = new ArrayList<>();
//        for(NFANode node:oldNFAStateSet){
//            for(NFANode nextNode:node.nextNodes){
//                if(nextNode.pathChar==pathChar&&!matchNodes.contains(nextNode)){
//                    newNFAStateSet.add(nextNode);
//                    matchNodes.add(nextNode);
//                }
//            }
//        }
//        ArrayList<NFANode> matchResult = findNulMatchNFANodes(matchNodes,new ArrayList<NFANode>());
//        for(NFANode node:matchResult){
//            if(!newNFAStateSet.contains(node)){
//                newNFAStateSet.add(node);
//            }
//        }
//        return newNFAStateSet;
//    }
//
//    //找到能够通过空字符转换得到的节点
//    public ArrayList<NFANode> findNulMatchNFANodes(ArrayList<NFANode> currentNodes,ArrayList<NFANode> NFANodeStack) {
//        ArrayList<NFANode> newNFAStateSet = new ArrayList<>();
//        ArrayList<NFANode> nextNFAStateSet = new ArrayList<>();
//        if(currentNodes.size()==0){
//            return newNFAStateSet;
//        }
//        for(NFANode node:currentNodes){
//            NFANodeStack.add(node);
//            newNFAStateSet.add(node);
//            for(NFANode nextNode:node.nextNodes){
//                if(nextNode.pathChar==nul&&!NFANodeStack.contains(nextNode)){
//                    nextNFAStateSet.add(nextNode);
//                }
//            }
//        }
//        ArrayList<NFANode> tempNodes = findNulMatchNFANodes(nextNFAStateSet,NFANodeStack);
//        for(NFANode node:tempNodes){
//            if(!newNFAStateSet.contains(node)){
//                newNFAStateSet.add(node);
//            }
//        }
//        return newNFAStateSet;
//    }
//}
