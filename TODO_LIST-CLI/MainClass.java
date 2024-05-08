package todolist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JOptionPane;
 
/**
 *
 * @author John Cipher
 */
class MainClass {
    private static final String red_color = "\u001B[31m";
    private static final String yellow_color2 = "\u001B[33m";
    private static final String reset_color = "\u001B[0m";
    private static final String green_color = "\u001B[32m";
    private static final String gray_background = "\u001B[100m";
    private static final String bold = "\u001B[1m";
    private static final String resetText = "\u001B[0m";
    private static final String underline = "\u001B[4m";
    private static DataList EncapsulateList;
  
    private static void clearScreen(){
        try{
            if(System.getProperty("os.name").contains("Windows")){
              new ProcessBuilder("cmd","/c", "cls").inheritIO().start().waitFor();
            }else{
                 new ProcessBuilder("cmd","/c", "clear").inheritIO().start().waitFor();
            }
        }catch(IOException | InterruptedException e){
            JOptionPane.showMessageDialog(null,"System clear interrupted","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void validateAndViewReturnList(){
        if(EncapsulateList.isEmpty())
            System.out.println("          (No list at the moment)");
        else
            EncapsulateList.viewList();
    }
    
    private static void showOption(){
        int i=0;
        System.out.println(red_color+" ____________________________________________"+reset_color);
        System.out.println(red_color+" "+gray_background+"________________"+reset_color+yellow_color2+bold+underline+" TODO-LIST "+resetText+reset_color+red_color+gray_background+"_________________"+reset_color+red_color+reset_color);
        validateAndViewReturnList();
        System.out.println(red_color+" ____________________________________________"+reset_color);
        String lightgreen_color = "\u001B[92m";
        System.out.println("          ["+lightgreen_color+"a"+reset_color+"]dd   ["+lightgreen_color+"d"+reset_color+"]one   ["+lightgreen_color+"e"+reset_color+"]xit");
        System.out.print(green_color+" command>"+reset_color+" ");
        
    }
    
    public static void main(String[] args) {
      try(Scanner input = new Scanner(System.in)){
          EncapsulateList = new DataList();
          try{
              while(true){
                  clearScreen();
                  showOption();
                  String opt  = input.nextLine();
                  if(opt.equalsIgnoreCase("a")){
                      addList();
                  }else if(opt.equalsIgnoreCase("d")){
                      deleteList();
                  }else if(opt.equalsIgnoreCase("e")){
                      JOptionPane.showMessageDialog(null,"Thank you..have a good day ☺️","MESSAGE",JOptionPane.INFORMATION_MESSAGE);
                      System.exit(0);
                  }else{
                      if(opt.isEmpty())
                          JOptionPane.showMessageDialog(null,"Please dont leave it blank","WARNING",JOptionPane.WARNING_MESSAGE);
                      else{
                          JOptionPane.showMessageDialog(null,"Invalid Input","ERROR",JOptionPane.ERROR_MESSAGE);
                          JOptionPane.showMessageDialog(null,"a, d, e","ACCEPTED VALUE",JOptionPane.INFORMATION_MESSAGE);
                      }
                  }
              }
          }catch(NoSuchElementException nse){
              System.out.println();
              System.out.println(yellow_color2+" GoodBye"+reset_color);
              input.close();
          }
      }    
    }
    
    private static void addList() {
        System.out.print("Processing...");
      try{
        String list = JOptionPane.showInputDialog("To be Added");
        
        if(list.isEmpty())
            JOptionPane.showMessageDialog(null,"Empty value is not accepted","WARNING",JOptionPane.WARNING_MESSAGE);
        else
            EncapsulateList.addList(list);
      }catch(NullPointerException npe){
          /*
           JOptionPane.showMessageDialog(null,npe.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
          */
      }
    }
    
    private static void deleteList() {
        int index;
        if(EncapsulateList.isEmpty()){
            JOptionPane.showMessageDialog(null, "List is Empty","WARNING",JOptionPane.WARNING_MESSAGE);
        }else{
            System.out.print("Processing...");
            try{
                String value = JOptionPane.showInputDialog("Which List");
                if(value.isEmpty())
                    JOptionPane.showMessageDialog(null,"Empty value is not accepted","WARNING",JOptionPane.WARNING_MESSAGE);
                else{
                    index = Integer.parseInt(value);
                    EncapsulateList.removeList(index-1);
                }
               // FIX THE CONDITIONAL ERROR HERE
               // ERROR WAS  NumberFormatException
            }catch(NullPointerException npe){
                //JOptionPane.showMessageDialog(null,"Invalid Input","ERROR",JOptionPane.ERROR_MESSAGE);
                //JOptionPane.showMessageDialog(null,npe.getMessage(),"REASON",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}

class DataList{
   private static final String yellow_color = "\u001B[33m";
   private static final String reset_color = "\u001B[0m";
   private static ArrayList<String> Datalist;

   public DataList(){       
       Datalist = new ArrayList<>();
   }
   
   public void addList(String list){
        if(Datalist.add(list))
            JOptionPane.showMessageDialog(null, "Sucessfully Added","INFO",JOptionPane.INFORMATION_MESSAGE);
   }
   
   public boolean isEmpty(){
        return Datalist.isEmpty();
   }
   
   public void removeList(int index){
        Datalist.remove(index);
        JOptionPane.showMessageDialog(null, "Sucessfully Remove","INFO",JOptionPane.INFORMATION_MESSAGE);     
   }
   
   public void viewList(){
       int list_num=0;
       for(String List : Datalist){
            System.out.println(" ["+yellow_color+(++list_num)+reset_color+"] "+List);
       }
   }
}
