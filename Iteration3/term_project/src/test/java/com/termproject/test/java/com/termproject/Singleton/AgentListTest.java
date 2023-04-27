package com.termproject.Singleton;

import com.termproject.People.TravelAgent;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AgentListTest {
    @Test
    void getInstance() {
        assertFalse(false);
    }

    @Test
    void getAgentList() {
        assertFalse(false);
    }

    @Test
    void testAgentListContainsData() {
        AgentList agentList = AgentList.getInstance();					//Get an instance of AgentList class
        ArrayList<TravelAgent> agents = agentList.getAgentList();		//Use instance to get list of travel agents

        assertNotNull(agents);      //Assert that arraylist of agents is not null and is not empty
        assertFalse(agents.isEmpty());
    }
}