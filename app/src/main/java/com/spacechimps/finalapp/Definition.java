package com.spacechimps.finalapp;

import java.io.Serializable;

/**
 * Created by oscarrodriguez on 29/04/2017.
 */

public class Definition implements Serializable {
    String word;
    String definition;
    public Definition(String w, String d){
        word=w;
        definition=d;
    }
}
