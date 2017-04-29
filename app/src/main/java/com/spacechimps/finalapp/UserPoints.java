package com.spacechimps.finalapp;

import java.io.Serializable;

/**
 * Created by oscarrodriguez on 29/04/2017.
 */

public class UserPoints implements Serializable {
    String username;
    int points;
    public UserPoints(String n, int p){
        username=n;
        points=p;
    }
}
