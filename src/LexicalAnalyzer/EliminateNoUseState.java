package LexicalAnalyzer;

import LexicalAnalyzer.pojo.DFANode;

import java.util.ArrayList;

public class EliminateNoUseState {

    //定义消除无用状态的方法
    public ArrayList<DFANode> eliminateNoUseState(ArrayList<DFANode> oldDFANodes) {
        //定义从起点能到达的节点的组合
        ArrayList<DFANode> startPointReachDFANodes = new ArrayList<>();
        //定义未遍历的DFA中的状态
        ArrayList<String> nextDFAStates = new ArrayList<>();
        //定义已遍历的DFA中的状态
        ArrayList<String> existDFAStates = new ArrayList<>();

        //找出开始状态为起点的节点放入开始集和遍历集
        for (DFANode node : oldDFANodes) {
            if (node.beginState.isBegin) {
                startPointReachDFANodes.add(node);
                if (!nextDFAStates.contains(node.beginState.stateString)) {
                    nextDFAStates.add(node.beginState.stateString);
                }
            }
        }

        while (!nextDFAStates.isEmpty()) {
            String currentState = nextDFAStates.get(0);
            existDFAStates.add(currentState);
            nextDFAStates.remove(0);
            for (DFANode node : oldDFANodes) {
                if (node.beginState.stateString.equals(currentState)) {
                    if (!startPointReachDFANodes.contains(node)) {
                        startPointReachDFANodes.add(node);
                    }
                    if (!existDFAStates.contains(node.endState.stateString) && !nextDFAStates.contains(node.endState.stateString)) {
                        nextDFAStates.add(node.endState.stateString);
                    }
                }
            }
        }

//        ArrayList<DFANode> startPointReachDFANodes = new ArrayList<>();
        //定义能够到达终点状态的节点的组合，其为起点的逆过程
        ArrayList<DFANode> reachEndPointDFANodes = new ArrayList<>();
        //重置nextDFAStates和existDFAStates
        nextDFAStates = new ArrayList<>();
        existDFAStates = new ArrayList<>();
        for (DFANode node : startPointReachDFANodes) {
            if (node.endState.isEnd) {
                reachEndPointDFANodes.add(node);
                if (!nextDFAStates.contains(node.endState.stateString)) {
                    nextDFAStates.add(node.endState.stateString);
                }
            }
        }
        while (!nextDFAStates.isEmpty()) {
            String currentState = nextDFAStates.get(0);
            existDFAStates.add(currentState);
            nextDFAStates.remove(0);
            for (DFANode node : startPointReachDFANodes) {
                if (node.endState.stateString.equals(currentState)) {
                    if (!reachEndPointDFANodes.contains(node)) {
                        reachEndPointDFANodes.add(node);
                    }
                    if (!existDFAStates.contains(node.beginState.stateString) && !nextDFAStates.contains(node.beginState.stateString)) {
                        nextDFAStates.add(node.beginState.stateString);
                    }
                }
            }
        }
        return reachEndPointDFANodes;
    }
}
