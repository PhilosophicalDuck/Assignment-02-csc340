package com.csc340.demo;
//Public class for the elixir in the Harry Potter world.
public class Elixir {
    public String name;
    public String effect;
    public String sideEffect;

    public Elixir(String name, String effect, String sideEffect){
        this.name = name;
        this.effect = effect;
        this.sideEffect = sideEffect;
    }

    //This is to override the toString method to be able to iterate through the array
    public String toString(){
        return "Name: " + name + ". Effect: " + effect + ". Side Effect: " + sideEffect + ".";
    }
}

