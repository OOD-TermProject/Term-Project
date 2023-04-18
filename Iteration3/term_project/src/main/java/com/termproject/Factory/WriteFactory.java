package com.termproject.Factory;

import com.termproject.Strategy.JSONStrategy;
import com.termproject.Strategy.RWStrategy;
import com.termproject.Strategy.XMLStrategy;

public class WriteFactory {

    public RWStrategy createStrategy(String dataFormat) {
        if (dataFormat.equalsIgnoreCase("json")) {
            return new JSONStrategy();
        } else if (dataFormat.equalsIgnoreCase("xml")) {
            return new XMLStrategy();
        } else {
            return null;
        }
    }
}
