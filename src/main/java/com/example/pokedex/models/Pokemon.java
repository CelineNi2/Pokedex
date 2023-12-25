package com.example.pokedex.models;

import org.json.simple.JSONObject;

public class Pokemon {
    /**
     * Class containing pokémon information : id, name, height and weight
     * Its construction requires a JSONObject that contains the data obtained from an external database.
     */
    protected Object id;
    protected Object name;
    protected Object height;
    protected Object weight;

    public Pokemon(){};
    public Pokemon(JSONObject pokemon){
        this.id = pokemon.get("id");
        this.name = pokemon.get("name");
        this.height =  pokemon.get("height");
        this.weight = pokemon.get("weight");
    }

    public Object getId() {return id;}
    public Object getName() {return name;}
    public Object getHeight() {return height;}
    public Object getWeight() {return weight;}


}
