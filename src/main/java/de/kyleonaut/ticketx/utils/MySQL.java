package de.kyleonaut.ticketx.utils;

import de.kyleonaut.ticketx.TicketX;
import java.sql.*;
import java.util.ArrayList;

public class MySQL {

    private Connection connection;

    private void newCon(){

        String host = TicketX.getMySQLhost();
        int port = TicketX.getMySQLport();
        String database = TicketX.getMySQLdatabase();
        String username = TicketX.getMySQLusername();
        String password = TicketX.getMySQLpassword();

        try{

            synchronized (this){
                if(getConnection() != null && !getConnection().isClosed()){
                    return;
                }

                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database +
                        "?autoReconnect=true&useSSL=false", username, password));
            }
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    private Connection getConnection() {
        return connection;
    }

    private void setConnection(Connection connection) {
        this.connection = connection;
    }



    public ArrayList<Object> get(String statement,String column){
        try {
            newCon();
            PreparedStatement cmd;
            cmd = this.connection.prepareStatement(statement);
            ResultSet res = cmd.executeQuery();
            if (res == null) return null;
            else{
                ArrayList<Object> value = new ArrayList();

                while (res.next()){
                    value.add(res.getObject(column));
                }
                disconnect();
                return value;
            }
        }catch (SQLException e){

        }
        return null;

    }

    public ArrayList<Object> get(String statement,int columnIndex) {
        try{
            newCon();
            PreparedStatement cmd;
            cmd = this.connection.prepareStatement(statement);
            ResultSet res = cmd.executeQuery();
            ArrayList<Object> value = new ArrayList();

            while (res.next()){
                value.add(res.getObject(columnIndex));
            }
            disconnect();
            return value;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void execute(String statement){
        try {
            newCon();
            PreparedStatement cmd;
            cmd = this.connection.prepareStatement(statement);
            cmd.executeUpdate();
            disconnect();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void disconnect(){
        if (isConnected()){
            try {
                this.connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private boolean isConnected(){
        return (this.connection != null);
    }
}
