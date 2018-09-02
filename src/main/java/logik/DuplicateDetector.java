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
          if (orderNUmbers==null||orderNUmbers.isEmpty()){
              System.out.println("Add Actual Order numbers to db");
              daoUpdateOrderNumberList.updateDB(customerFromActualList);
              return (ArrayList<Customer>) customerFromActualList;
          }else{

              for (int i = 0; i <customerToProcess.size() ; i++) {
                  Customer customer = customerToProcess.get(i);

                  for (int j = 0; j <orderNUmbers.size() ; j++) {
                      if (customer.getBestellnummer().equalsIgnoreCase(orderNUmbers.get(j))){
                          System.out.println("Bestellnummer "+ customer.getBestellnummer()+" ist ein Duplikat und wird aus der Liste entfernt ");
                          customerToProcess.remove(customer);
                          break;
                      }

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
