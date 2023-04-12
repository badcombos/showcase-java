package edu.odu.cs.cs350.nameExtraction;

/*
*       The Block class handles all the functions of a single 
*       block like adding NER tags and getting/setting the private rawBlock
*
*       @author Group 2 - Thomas Harlow
*       @since  2021-04-07
*/

public class Block{

        // Private Strings
        private String rawBlock;
        //Collection<Token> token = new ArrayList<Token>();

        /*
        *      Constructor that sets the passed parameter block to the rawBlock private String
        *      @param block     This is the parameter that the use wants to set as the rawBlock for this class object
        *      @return          Nothing.
        */
        public Block(String block){
                this.rawBlock = block;
        }
        public Block(){
        }

        /*
        *       Function takes a String rawBlock parameter and uses String concatenation to add the NER tags then returns it
        *       @parm rawBlock          This parameter takes a rawBlock and adds NER tags to it
        *       @return s               This String takes the rawBlock parameter and adds the NER tags to it with concatenation and returns it
        *
        */
        public String addNERTags(String rawBlock){
                String s = "<NER>"+rawBlock+"</NER>";
                return s;
        }

        /*
        *       Function gets the private parameter rawBlock
        *       @parm           None.
        *       @return         Returns rawBlock String
        */
        public String getBlock(){
                return rawBlock;
        }

        /*
        *       Function sets the private rawBlock based off the parameter block
        *       @parm   rawBlock        Takes the parameter and sets it the private rawBlock string as this new string
        *       @return None.
        */
        public void setBlock(String block){
                this.rawBlock = block;
        }

        /*
        *       Function prints the private rawBlock as a toString
        *       @parm           None.
        *       @return         Returns rawBlock as a toString
        */
        public String toString(){
                return rawBlock;
        }
}