package edu.odu.cs.cs350.nameExtraction;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.odu.cs.cs350.nameExtraction.Token;

public class TestToken {
    
    private Token defaultToken;
    private Token expectedToken;

    @Before
    public void setUp(){
        defaultToken = new Token();
        expectedToken = new Token("John R. Doe");
    }

   /*
    *   Test that will test the constructor with empty parameters
    *   and a string parameter that matches the parameter in the @Before
    *
    *   @parm   None.
    *   @return None.
    */
    @Test
    public void testContructor(){
        assertEquals(null, defaultToken.getToken());
        assertEquals("John R. Doe", expectedToken.getToken());
    }

    /*
    *   Function will test the addPERTags function in Token.
    *   It will make a test String then an expected output String
    *   while using the default Token with no parameter and pass it to the function
    *
    *   @parm   None.
    *   @return None.
    */
    @Test
    public void testAddPERTags(){
        String raw = "Thomas L. Johnson";
        String expected = "<PER>Thomas L. Johnson</PER>";

        assertEquals(defaultToken.addPERTags(raw), expected);
    }

    /*
    *   Functions tests the getter and setter functions in the Token class.
    *   It first tests the two Token objects we created @Before then tests the setToken
    *   function on the expectedToken object then tests getToken again for it
    *
    *   @parm   None.
    *   @return None.
    */
    @Test
    public void testGetSetToken(){
        assertEquals(null, defaultToken.getToken());
        assertEquals("John R. Doe", expectedToken.getToken());

        defaultToken.setBlock("Thomas");
        expectedToken.setBlock("David");

        assertEquals("Thomas", defaultToken.getToken());
        assertEquals("David", expectedToken.getToken());
    }

    /*
    *   Function tests the toString function in the Token class
    *   Creates a new Token object and creates string with expected output string
    *   then compares its equals calling the toString function
    *
    *   @parm   None.
    *   @return None.
    */
    @Test
    public void testToString(){
        Token t = new Token("Nelson");
        String expected = "Nelson";
        assertEquals(t.toString(), expected);
    }
}
