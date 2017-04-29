package com.spacechimps.finalapp;

import java.io.Serializable;

/**
 * Created by oscarrodriguez on 29/04/2017.
 */

public class UserPointsArray implements Serializable {
    UserPoints[] users;
    public UserPointsArray(int length){
        users=new UserPoints[length];
    }
}
