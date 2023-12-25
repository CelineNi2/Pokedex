package com.example.pokedex;

import java.sql.*;

public class SQLLiteExample {
    public static void run() {
        /* Connect to the database */
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:../sujet_TP/ressources/pokemondatabase.sqlite";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            PreparedStatement stmt  = conn.prepareStatement("SELECT name, description FROM pokemons WHERE id = ?");
            stmt.setInt(1, 3); // Sets the value "3" for parameter at position 1
            ResultSet rs    = stmt.executeQuery();
            rs.next();
            System.out.println("Pokémon name : " + rs.getString("name"));
            System.out.println("Pokémon description : " + rs.getString("description"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
