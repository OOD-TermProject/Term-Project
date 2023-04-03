package com.termproject.Strategy;

import com.termproject.Trip.Trip;

public abstract class RWStrategy {
  abstract void SaveData();
  abstract Trip ReadData();
}