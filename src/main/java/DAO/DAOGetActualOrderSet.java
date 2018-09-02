package DAO;

import logik.data.DbConnectionSingletonFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOGetActualOrderSet {

    public ArrayList<String> getLastAddedOrderNumber() throws SQLException {
        ArrayList<String> orderNumber  = new ArrayList<>();

        String getMaterialIdQuery = "SELECT OrderNumber "  +
                "FROM OrderNumber ";



        System.out.println(getMaterialIdQuery);

        Connection con = DbConnectionSingletonFactory.getConnection();

        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(getMaterialIdQuery);
        while (rs.next()) {

            String name = rs.getString("OrderNumber");
            orderNumber.add(name);
        }
        con.close();
        return orderNumber;
    }



}
