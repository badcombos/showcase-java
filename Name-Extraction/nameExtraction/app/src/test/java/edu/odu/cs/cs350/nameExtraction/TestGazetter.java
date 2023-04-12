package edu.odu.cs.cs350.nameExtraction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;

public class TestGazetter {
    
    // Private Gazetter object
    Gazetteer gaz;

    /*
            Sets up the Gazetteer object before the tests are ran
    */
    @Before
    public void setUp(){
        gaz = new Gazetteer();
    }
    /*
            Tests to see if the contains function actually found the name parameter
    */
    @Test
    public void testContains(){
        
    }
}
