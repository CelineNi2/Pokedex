package com.example.pokedex.controllers;

import com.example.pokedex.models.Pokemon;
import com.example.pokedex.models.PokemonSQL;
import com.example.pokedex.services.AccessData;
import com.example.pokedex.services.ApiData;
import com.example.pokedex.views.PokemonView;
import org.json.simple.JSONObject;

import java.sql.ResultSet;

public class ControllerPokemon {
    /**
     * An instance of this class is created using a given database.
     * Then using this database, this class instanciate a PokemonView object in one of its attribute
     *  using the generatePokemonData() function.
     * The PokemonView Object is then accessible by using getPokemonView().
     */

    private AccessData accessData;
    private PokemonView pokemonView;

    public PokemonView getPokemonView() {return pokemonView;}
    public ControllerPokemon(AccessData accessData){
        this.accessData = accessData;
    }

    public void generatePokemonData(){

        if (accessData.getData() instanceof ResultSet) {
            PokemonSQL pokemon = new PokemonSQL((ResultSet) accessData.getData());
            pokemonView = new PokemonView(pokemon);
        }
        else if (accessData.getData() instanceof JSONObject){
            Pokemon pokemon = new Pokemon((JSONObject) accessData.getData());
            pokemonView = new PokemonView(pokemon);
        }
        else {
            System.err.println("Error : ControllerPokemon");
        }
    }


    //Version 3, part 2 after implementing the connection to the local database
    /*
    private AccessData accessData;

    public ControllerPokemon(AccessData accessData){
        this.accessData = accessData;
    }

    public void generatePokemonData(){

        if (accessData.getData() instanceof ResultSet) {
            PokemonSQL pokemon = new PokemonSQL((ResultSet) accessData.getData());
            PokemonView pokemonView = new PokemonView();
            pokemonView.view(pokemon);
        }
        else if (accessData.getData() instanceof JSONObject){
            Pokemon pokemon = new Pokemon((JSONObject) accessData.getData());
            PokemonView pokemonView = new PokemonView();
            pokemonView.view(pokemon);
        }
        else {
            System.err.println("Error");
        }
    }
    */

    //Version 2, part 1-B when the Api is called via an interface
    /*
    private AccessData accessData;
    private Pokemon pokemon;

    public ControllerPokemon(AccessData accessData){
        this.accessData = accessData;
    }

    public void generatePokemonData(){
        if (accessData.getData() instanceof JSONObject){
            this.pokemon = new Pokemon((JSONObject) accessData.getData());
            PokemonView pokemonView = new PokemonView();
            pokemonView.setPokemon(pokemon);
            pokemonView.view();
        }
        else {
            System.err.println("Error, we expected a JSON Object from the API");
        }
    }
    */

    //Version 1, part 1-A when ApiData is called directly
    /*
    private ApiData apiData;
    private Pokemon pokemon = new Pokemon();

    public ControllerPokemon(int id){
        apiData = new ApiData(id);
    }

    public void generatePokemonData(){
        if (apiData.getData() instanceof JSONObject){
            pokemon.setPokemon((JSONObject) apiData.getData());
            PokemonView pokemonView = new PokemonView();
            pokemonView.setPokemon(pokemon);
            pokemonView.view();
        }
        else {
            System.err.println("Error, we expected a JSON Object from the API");
        }
    }
    */

}
