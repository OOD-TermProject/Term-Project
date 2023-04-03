package com.termproject.Singleton;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class AgentList {
    private static final AgentList uniqueInstance = new AgentList();
    private static final String agentsFile = "term_project/src/main/java/com/termproject/Singleton/agents.json";
    ArrayList<String> agentList;

    private AgentList() {
        loadAgentsFile();
    }

    public static AgentList getInstance() {
        return uniqueInstance;
    }

    private void loadAgentsFile() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(agentsFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Gson gson = new Gson();

        agentList = gson.fromJson(reader, ArrayList.class);
    }

    public ArrayList<String> getAgentList() {
        return agentList;
    }
}
