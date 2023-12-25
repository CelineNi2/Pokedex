package com.example.pokedex.services;

import java.sql.*;

public class LocalData implements AccessData{
    /**
     * This class serves to get pokemon data from a local database by giving its constructor a path
     * to the database and the chosen pokemon's id.
     * As usual, data is accessible using the getData() function.
     */
    private String url;
    private int id;

    public LocalData(String url, int id){
        this.url = url;
        this.id = id;
    }

    @Override
    public ResultSet getData(){
        Connection conn = null;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            PreparedStatement stmt  = conn.prepareStatement("SELECT id, name, description, height, weight FROM pokemons WHERE id = ?");
            stmt.setInt(1, id); // Sets the value id for parameter at position 1
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs;

        } catch (SQLException e) {
            return null;
        }
    }

}
