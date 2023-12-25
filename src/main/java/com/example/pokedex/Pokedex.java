package com.example.pokedex;


import com.example.pokedex.controllers.ControllerPokemon;
import com.example.pokedex.models.Pokemon;
import com.example.pokedex.models.PokemonSQL;
import com.example.pokedex.services.ApiData;
import com.example.pokedex.services.LocalData;
import com.example.pokedex.utilities.ConsoleOutputUtility;
import com.example.pokedex.utilities.OutputFormat;
import com.example.pokedex.views.PokemonView;
import org.apache.commons.cli.*;
import org.json.simple.JSONObject;

public class Pokedex {
    /**
     * This is the main part of the project.
     * It initializes its attributes using the arguments given in the command line.
     * According to the format of the command line, the main() function acts differently to print out
     *  data about a chosen pokemon.
     * The complete form of the command line is :
     *      run --args="4 -d jdbc:sqlite:../sujet_TP/ressources/pokemondatabase.sqlite -f csv"
     *  The '4' is a necessary input corresponding the chosen pokemon id.
     *  The "-d jdbc:sqlite:../sujet_TP/ressources/pokemondatabase.sqlite" is the optionnal path to a
     *   local database.
     *  The "-f csv" part is the optionnal choice of the output format (between text, html and csv)
     */

    private enum DataSource {WEB_API, LOCAL_DATABASE}
    private static DataSource dataSource = DataSource.WEB_API;
    private static String databasePath;
    private static OutputFormat outputFormat = OutputFormat.TEXT;
    private static int pokemonId;

    public static void main(String[] args) throws ParseException {

        /* Parse the command line arguments, this is done for you, keep this code block */
        try {
            parseCommandLineArguments(args);
        } catch (PokemonCommandLineParsingException e) {
            System.err.println(e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("./Pokedex <PokemonId> [-d|--database <databaseFile>] [-f|--format <format>]", e.getOptions());
            System.exit(0);
        }

        if (dataSource==DataSource.LOCAL_DATABASE){
            LocalData localData = new LocalData(databasePath, pokemonId);
            ControllerPokemon controllerPokemon = new ControllerPokemon(localData);
            controllerPokemon.generatePokemonData();
            ConsoleOutputUtility consoleOutputUtility = new ConsoleOutputUtility(outputFormat, controllerPokemon.getPokemonView());
            consoleOutputUtility.makeOutput();
        }
        else {
            ApiData apiData = new ApiData(pokemonId);
            ControllerPokemon controllerPokemon = new ControllerPokemon(apiData);
            controllerPokemon.generatePokemonData();
            controllerPokemon.generatePokemonData();
            ConsoleOutputUtility consoleOutputUtility = new ConsoleOutputUtility(outputFormat, controllerPokemon.getPokemonView());
            consoleOutputUtility.makeOutput();
        }


        // Uncomment this when you are at part 3 of the assignment
        //ConsoleOutputUtility consoleOutputUtility = new ConsoleOutputUtility(outputFormat, /* PokemonView instance */);


        //Version 3, part 2 after implementing the connection to the local database
        /*
        if (dataSource==DataSource.LOCAL_DATABASE){
            LocalData localData = new LocalData(databasePath, pokemonId);
            ControllerPokemon controllerPokemon = new ControllerPokemon(localData);
            controllerPokemon.generatePokemonData();
        }
        else {
            ApiData apiData = new ApiData(pokemonId);
            ControllerPokemon controllerPokemon = new ControllerPokemon(apiData);
            controllerPokemon.generatePokemonData();
        }
        */

        //Version 2, part 1-B when the Api is called via an interface
        /*
        ApiData apiData = new ApiData(pokemonId);
        ControllerPokemon controllerPokemon = new ControllerPokemon(apiData);
        controllerPokemon.generatePokemonData();
        */

        //Version 1, part 1-A when ApiData is called directly
        /*
        ControllerPokemon controllerPokemon = new ControllerPokemon(pokemonId);
        controllerPokemon.generatePokemonData();
        */
    }

    public static void parseCommandLineArguments(String[] args) throws PokemonCommandLineParsingException, ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("d", "database", true, "Path to a SQLite database containing pokemons");
        options.addOption("f", "format", true, "Specify the output format, between 'text', 'html' and 'csv'. By default 'text'.");

        // parse the command line arguments
        CommandLine line = parser.parse(options, args);
        if (line.hasOption("d")) {
            dataSource = DataSource.LOCAL_DATABASE;
            databasePath = line.getOptionValue("d");
        }

        if (line.hasOption("f")) {
            String formatArgValue = line.getOptionValue("f");
            if (formatArgValue.equals("html")) {
                outputFormat = OutputFormat.HTML;
            } else if (formatArgValue.equals("csv")) {
                outputFormat = OutputFormat.CSV;
            } else if (formatArgValue.equals("text")) {
                outputFormat = OutputFormat.TEXT;
            } else {
                throw new PokemonCommandLineParsingException("Invalid value for the option -f/--format", options);
            }
        }

        // Get pokemon ID from remaining arguments
        String[] remainingArgs = line.getArgs();
        if (remainingArgs.length < 1) {
            throw new PokemonCommandLineParsingException("You must provide a pokemon ID", options);
        }
        try {
            pokemonId = Integer.parseInt(remainingArgs[0]);
        } catch (NumberFormatException e) {
            throw new PokemonCommandLineParsingException("'" + remainingArgs[0] + "' is not a valid pokemon ID", options);
        }
    }


    static class PokemonCommandLineParsingException extends Exception {

        private Options options;

        public PokemonCommandLineParsingException(String msg, Options options) {
            super(msg);
            this.options = options;
        }

        public Options getOptions() {
            return options;
        }

    };
}
