package com.spacechimps.finalapp;

import java.io.Serializable;

/**
 * Created by oscarrodriguez on 29/04/2017.
 */

public class Definition implements Serializable, Comparable<Definition>{
    String word;
    String definition;
    int random;
    public Definition(String w, String d){
        word=w;
        definition=d;
    }
    public int compareTo(Definition e){
        return this.random-e.random;
    }
}
