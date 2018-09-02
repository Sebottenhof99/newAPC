package logik;

import DAO.DAOGetActualOrderSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DuplicateDetector {
    DAOGetActualOrderSet daoGetActualOrderSet = new DAOGetActualOrderSet();

  public List<Customer>filterDuplicate(List<Customer> customerFromActualList){

      List<Customer> customerToProcess = customerFromActualList;
      try {
          ArrayList<String> orderNUmbers = daoGetActualOrderSet.getLastAddedOrderNumber();
          if (orderNUmbers==null||orderNUmbers.isEmpty()){
              return (ArrayList<Customer>) customerFromActualList;
          }else{

              for (Customer customer:customerToProcess) {
                  for (int j = 0; j <orderNUmbers.size() ; j++) {
                      if (customer.getBestellnummer().equalsIgnoreCase(orderNUmbers.get(j))){
                          System.out.println("Bestellnummer "+ customer.getBestellnummer()+" ist ein Duplikat und wird aus der Liste entfernt ");
                          customerFromActualList.remove(customer);
                          continue;
                      }

                  }
              }

          }
      } catch (SQLException e) {
          System.out.println("Feherl bei der Verbindung mit DB");
      }
      return customerToProcess;

  }




}
