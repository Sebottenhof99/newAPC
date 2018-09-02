package DAO;

import logik.Customer;
import logik.data.DbConnectionSingletonFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOUpdateOrderNumberList {


    public void updateDB(List<Customer> newInputOfOrderNumber) throws SQLException {

        System.out.println("<<<"+newInputOfOrderNumber.size()+">>>");


        ArrayList<String> sqlOrderNUmber = new ArrayList<>();

        for (int i = 0; i < newInputOfOrderNumber.size() ; i++) {
            String s = "('";
            s+= newInputOfOrderNumber.get(i).getBestellnummer();
            s+="')";
            System.out.println(s);
            sqlOrderNUmber.add(s);
        }

        String sqlQuery = "INSERT INTO OrderNumber (OrderNumber) VALUES ";

        for (int i = 0; i <sqlOrderNUmber.size() ; i++) {

            if (i==(sqlOrderNUmber.size()-1)){
                sqlQuery += sqlOrderNUmber.get(i)+";";
            }else{
                sqlQuery += sqlOrderNUmber.get(i)+", ";
            }

        }

        String delTabelle = "DELETE from OrderNumber";



        System.out.println(sqlQuery);

        Connection con = DbConnectionSingletonFactory.getConnection();

        Statement stmt = null;
        stmt = con.createStatement();
        stmt.executeUpdate(delTabelle);
       stmt.executeUpdate(sqlQuery);

        con.close();

    }




}
