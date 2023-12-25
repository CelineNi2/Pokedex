package com.example.pokedex.models;

import org.json.simple.JSONObject;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PokemonSQL extends Pokemon{
    /**
     * This class extends the Pokemon class by having the description of Pokémons.
     * The 2 PokemonSQL constructors serve in case the database request contains or not the description
     * attribute.
     */
    private Object description;

    public PokemonSQL(JSONObject pokemon) {super(pokemon);}

    public PokemonSQL(ResultSet rs) {
        super();
        try {
            id = rs.getString("id");
            name = rs.getString("name");
            height = rs.getString("height");
            weight = rs.getString("weight");
            description = rs.getString("description");
        }
        catch (SQLException e){}
    }

    public Object getDescription() {
        return description;
    }
}
