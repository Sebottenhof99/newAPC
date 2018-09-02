package logik;

import DAO.DAOGetActualOrderSet;
import DAO.DAOUpdateOrderNumberList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DuplicateDetector {
    DAOGetActualOrderSet daoGetActualOrderSet = new DAOGetActualOrderSet();
    DAOUpdateOrderNumberList daoUpdateOrderNumberList  = new DAOUpdateOrderNumberList();

  public List<Customer>filterDuplicate(List<Customer> customerFromActualList){

      List<Customer> customerToProcess = customerFromActualList;
      try {
          ArrayList<String> orderNUmbers = daoGetActualOrderSet.getLastAddedOrderNumber();
          System.out.println("Size of db list: " + orderNUmbers.size());

          if (orderNUmbers==null||orderNUmbers.isEmpty()){
              System.out.println("Add Actual Order numbers to db");
              daoUpdateOrderNumberList.updateDB(customerFromActualList);
              return (ArrayList<Customer>) customerFromActualList;
          }else{


              for (int i = 0; i <customerToProcess.size() ; i++) {
                  Customer customer = customerToProcess.get(i);
                  if(orderNUmbers.contains(customer.getBestellnummer())){
                      System.out.println("CONTAINS");
                      customerToProcess.remove(i);
                  }

              }


          }

          System.out.println("Add Actual Order numbers to db");
          daoUpdateOrderNumberList.updateDB(customerFromActualList);
          return customerToProcess;
      } catch (SQLException e) {
          e.printStackTrace();
          System.out.println("Feherl bei der Verbindung mit DB");
      }

      return customerToProcess;

  }




}
