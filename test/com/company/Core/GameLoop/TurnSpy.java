package com.company.Core.GameLoop;

public class TurnSpy implements Turn {
    int count = 0;

    public int getPlayedTimes() {
        return count;
    }

    public void play() {
        count++;
    }
}
