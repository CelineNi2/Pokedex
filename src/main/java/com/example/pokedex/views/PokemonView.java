package com.example.pokedex.views;

import com.example.pokedex.models.Pokemon;
import com.example.pokedex.models.PokemonSQL;
import com.example.pokedex.utilities.MultipleFormatGenerator;

public class PokemonView implements MultipleFormatGenerator {
    /**
     * This class serves to format and print pokémon data in the terminal.
     * Defining the 2 attributes within this class is a refactoring done during the 3rd part of the
     *  practical work to be able to give different output regarding the type Pokémon information
     *  given by the database.
     */
    private Pokemon pokemon = null;
    private PokemonSQL pokemonSQL = null;

    public PokemonView(Pokemon pokemon){this.pokemon = pokemon;}
    public PokemonView(PokemonSQL pokemonSQL){this.pokemonSQL = pokemonSQL;}

    @Override
    public String generateHumanReadableText(){
        if (pokemon!=null){
            return "=".repeat(25) + "\n"
                    + "Pokémon # " + pokemon.getId() + "\n"
                    + "Name : " + pokemon.getName() + "\n"
                    + "Height : " + pokemon.getHeight() + "\n"
                    + "Weight : " + pokemon.getWeight() + "\n"
                    + "=".repeat(25);
        }
        else if (pokemonSQL!=null) {
            return "=".repeat(25) + "\n"
                    + "Pokémon # " + pokemonSQL.getId() + "\n"
                    + "Name : " + pokemonSQL.getName() + "\n"
                    + "Height : " + pokemonSQL.getHeight() + "\n"
                    + "Weight : " + pokemonSQL.getWeight() + "\n"
                    + "Description : " + pokemonSQL.getDescription() + "\n"
                    + "=".repeat(25);
        }
        else{
            return "Error : PokemonView HumanReadableText";
        }
    }

    @Override
    public String generateHTML(){
        if (pokemon!=null){
            return "<h1>" + pokemon.getName() + "</h1>" + "\n"
                    + "<ul>" + "\n"
                    + "<li>ID : " + pokemon.getId() + "</li>" + "\n"
                    + "<li>Taille : " + pokemon.getHeight() + "</li>" + "\n"
                    + "<li>Poids : " + pokemon.getWeight() + "</li>" + "\n"
                    +"</ul>";
        }
        else if (pokemonSQL!=null) {
            return "<h1>" + pokemonSQL.getName() + "</h1>" + "\n"
                    + "<ul>" + "\n"
                    + "<li>ID : " + pokemonSQL.getId() + "</li>" + "\n"
                    + "<li>Taille : " + pokemonSQL.getHeight() + "</li>" + "\n"
                    + "<li>Poids : " + pokemonSQL.getWeight() + "</li>" + "\n"
                    + "<li>Description : " + pokemonSQL.getDescription() + "</li>" + "\n"
                    +"</ul>";
        }
        else{
            return "Error : PokemonView HTML";
        }
    }

    @Override
    public String generateCSV() {
        if (pokemon!=null){
            return "ID;Name;Height;Weight" + "\n"
                    + pokemon.getId() + ";"
                    + pokemon.getName() + ";"
                    + pokemon.getHeight() + ";"
                    + pokemon.getWeight() + ";";
        }
        else if (pokemonSQL!=null) {
            return "ID;Name;Height;Weight" + "\n"
                    + pokemonSQL.getId() + ";"
                    + '"' + pokemonSQL.getName() + '"' + ";"
                    + pokemonSQL.getHeight() + ";"
                    + pokemonSQL.getWeight() + ";"
                    + '"' + pokemonSQL.getDescription() + '"';
        }
        else{
            return "Error : PokemonView CSV";
        }
    }


    //Version 3, part 2 after implementing the connection to the local database
    /*
    public void view(Pokemon pokemon){
        System.out.println("=".repeat(25));
        System.out.println("Pokémon # " + pokemon.getId());
        System.out.println("Name : " + pokemon.getName());
        System.out.println("Height : " + pokemon.getHeight());
        System.out.println("Weight : " + pokemon.getWeight());
        System.out.println("=".repeat(25));
    }

    public void view(PokemonSQL pokemon){
        System.out.println("=".repeat(25));
        System.out.println("Pokémon # " + pokemon.getId());
        System.out.println("Name : " + pokemon.getName());
        System.out.println("Height : " + pokemon.getHeight());
        System.out.println("Weight : " + pokemon.getWeight());
        System.out.println("Description : " + pokemon.getDescription());
        System.out.println("=".repeat(25));
    }
    */


    //Version 1 and 2, part 1-B when the Api is called via an interface
    /*
    private Pokemon pokemon;

    public void setPokemon(Pokemon pokemon) {this.pokemon = pokemon;}

    public void view(){
        System.out.println("=".repeat(25));
        System.out.println("Pokémon # " + pokemon.getId());
        System.out.println("Name : " + pokemon.getName());
        System.out.println("Height : " + pokemon.getHeight());
        System.out.println("Weight : " + pokemon.getWeight());
        System.out.println("=".repeat(25));
    }
    */

}
