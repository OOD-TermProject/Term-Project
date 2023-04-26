package com.termproject;

import com.termproject.People.TravelAgent;
import com.termproject.Singleton.AgentList;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Test;

public class AgentListTest {

	@Test
	public void testAgentListContainsData() {
        
        AgentList agentList = AgentList.getInstance();					//Get an instance of AgentList class
        ArrayList<TravelAgent> agents = agentList.getAgentList();		//Use instance to get list of travel agents

        assertNotNull(agents);					//Assert that arraylist of agents is not null and is not empty
        assertFalse(agents.isEmpty());

    }
	
}
