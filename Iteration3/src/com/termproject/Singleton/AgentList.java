package com.termproject.Singleton;

public class AgentList {
    private static AgentList uniqueInstance = new AgentList();

    private AgentList() {
        // Load the Agent list in here
    }

    public static AgentList getInstance() {
        return uniqueInstance;
    }
}
