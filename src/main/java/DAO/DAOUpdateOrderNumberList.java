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


        ArrayList<String> sqlOrderNUmber = new ArrayList<>();

        for (int i = 0; i < newInputOfOrderNumber.size() ; i++) {
            sqlOrderNUmber.add("('"+newInputOfOrderNumber.get(i).getBestellnummer()+"')");
        }

        String sqlQuery = "INSERT INTO OrderNumber (`OrderNumber`) VALUES ";

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
        stmt.execute(delTabelle);
       stmt.execute(sqlQuery);

        con.close();

    }




}
