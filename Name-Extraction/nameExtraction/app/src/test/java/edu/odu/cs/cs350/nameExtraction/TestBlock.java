package edu.odu.cs.cs350.nameExtraction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.*;

import edu.odu.cs.cs350.nameExtraction.*;

/*
*       The TestBlock class handles all the tests for all of the
*       functions inside of the Block class
*
*       @author Group 2 - Thomas Harlow
*       @since  2021-04-07
*/

public class TestBlock{

    /*
    *   Private Block class objects one used for default constructor 
    *   and other is used as the expected block with a parameter to constructor
    */
    private Block defaultBlock;
    private Block expectedBlock;

    /*
    *   Test Before feature that will set up the Block objects before any tests are ran
    */
    @Before
    public void setUp(){
        defaultBlock = new Block();
        expectedBlock = new Block("TEST");
    }

    /*
    *   Test that will test the constructor with empty parameters
    *   and a string parameter that matches the parameter in the @Before
    *
    *   @parm   None.
    *   @return None.
    */
    @Test
    public void testConstructor(){
        assertEquals(null, defaultBlock.getBlock());
        assertEquals("TEST", expectedBlock.getBlock());
    }

    /*
    *   Test that will test the default constructor with empty parameters
    *   and a string parameter
    *
    *   @parm   None.
    *   @return None.
    */
    @Test
    public void testDefaultConstructor(){
        // FILL HERE
    }

    /*
    *   Function will test the addNERTags function in Block.
    *   It will make a test String then an expected output String
    *   while using the default Block with no parameter and pass it to the function
    *
    *   @parm   None.
    *   @return None.
    */
    @Test
    public void testAddNERTags(){
        String raw = "This adds the tag";
        String expected = "<NER>This adds the tag</NER>";

        assertEquals(defaultBlock.addNERTags(raw), expected);
    }

    /*
    *   Functions tests the getter and setter functions in the Block class.
    *   It first tests the two Block objects we created @Before then tests the setBlock
    *   function on the expectedBlock object then tests getBlock again for it
    *
    *   @parm   None.
    *   @return None.
    */
    @Test
    public void testGetSetBlock(){
        assertEquals(null, defaultBlock.getBlock());
        assertEquals("TEST", expectedBlock.getBlock());

        defaultBlock.setBlock("defaultBlock");
        expectedBlock.setBlock("TEST2");

        assertEquals("defaultBlock", defaultBlock.getBlock());
        assertEquals("TEST2", expectedBlock.getBlock());
    }

    /*
    *   Function tests the toString function in the Block class
    *   Creates a new Block object and creates string with expected output string
    *   then compares its equals calling the toString function
    *
    *   @parm   None.
    *   @return None.
    */
    @Test
    public void testToString(){
        Block b = new Block("Testing toString");
        String expected = "Testing toString";
        assertEquals(b.toString(), expected);
    }
}