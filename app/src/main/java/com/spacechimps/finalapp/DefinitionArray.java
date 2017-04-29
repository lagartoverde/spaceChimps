package com.spacechimps.finalapp;

import java.io.Serializable;

/**
 * Created by oscarrodriguez on 29/04/2017.
 */

public class DefinitionArray implements Serializable {
    Definition[] definitions;
    public DefinitionArray(){
        definitions=new Definition[5];
    }

}
