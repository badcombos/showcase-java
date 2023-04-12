package edu.odu.cs.cs350.nameExtraction;

import java.util.Scanner;

public class Token {

    // Private variables w/ object class for FeatureSet
    private String rawToken;
    FeatureSet features;

    /*
    *      Constructor that sets the passed parameter token to the rawToken private String
    *      @param token     This is the parameter that the use wants to set as the rawToken for this class object
    *      @return          Nothing.
    */
    public Token(String token){
        this.rawToken = token;
    }
    public Token(){

    }

    /*
    *       Function takes a String rawToken parameter and uses String concatenation to add the NER tags then returns it
    *       @parm rawToken          This parameter takes a rawToken and adds NER tags to it
    *       @return s               This String takes the rawToken parameter and adds the NER tags to it with concatenation and returns it
    *
    */
    public String addPERTags(String rawToken){
        String s = "<PER>"+rawToken+"</PER>";
        return s;
    }

    /*
    *       Function gets the private parameter rawToken
    *       @parm           None.
    *       @return         Returns rawToken String
    */
    public String getToken(){
        return rawToken;
    }

    /*
    *       Function sets the private rawToken based off the parameter token
    *       @parm   rawToken        Takes the parameter and sets it the private rawToken string as this new string
    *       @return None.
    */
    public void setBlock(String token){
        this.rawToken = token;
    }

    /*
    *       Function prints the private rawToken as a toString
    *       @parm           None.
    *       @return         Returns rawToken as a toString
    */
    public String toString(){
        return rawToken;
    }
}