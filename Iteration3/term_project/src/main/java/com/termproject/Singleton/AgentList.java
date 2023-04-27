package com.termproject.Singleton;

import com.termproject.People.TravelAgent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AgentList {
    private static final AgentList uniqueInstance = new AgentList();
    private final String agentsFile = "src/main/java/com/termproject/Singleton/agents.txt";
    ArrayList<TravelAgent> agentList = new ArrayList<>();

    private AgentList() {
        loadAgentsFile();
    }

    public static AgentList getInstance() {
        return uniqueInstance;
    }

    /**
     * Loads information for each TravelAgent object from disk and adds it to an ArrayList
     */
    private void loadAgentsFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(agentsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                String mobilePhone = data[1];
                String username = data[2];
                agentList.add(new TravelAgent(name, mobilePhone, username));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TravelAgent> getAgentList() {
        return agentList;
    }
}
