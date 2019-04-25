/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anhha
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PostgreSqlExample {
    
    public static void main(String[] args) {
        
        int guessChoice;
        int receptionChoice;
        int adminChoice;
        String role;
        String receptionId;
        Scanner sc = new Scanner(System.in);
        boolean hiddenFunction = false;
        boolean receptionPermission = false;
        boolean adminPermission = false;
        
	try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/HotelSystem", "postgres", "123456")) {  
            //Generate variable with connection
            Statement statement = connection.createStatement();
            
            /*
            // Default screen when open app
            */
            showDefaultGui();
            ResultSet roomAvailableSet = statement.executeQuery(
                        "select room_id, name, price, price_step, r.type_id, price_day "
                                + "from public.room r, public.room_type ty "
                                + "where (r.type_id = ty.type_id and status= true) order by room_id"  );
            System.out.printf("%-10.10s %-15.15s %-15.15s %-15.15s %-15.15s  %-15.15s%n", "Room id", "Room name", "Room type", "First 2 hours", "Next hour price", "price/day" );
            while (roomAvailableSet.next()) {
                System.out.printf("%-10.10s %-15.15s %-15.15s %-15.15s %-15.15s %-15.15s%n",
                                    roomAvailableSet.getString("room_id"), roomAvailableSet.getString("name"), roomAvailableSet.getString("type_id")
                                    , roomAvailableSet.getString("price"), roomAvailableSet.getString("price_step"), roomAvailableSet.getString("price_day"));
            }
            
            guessChoice = 999;
            /*
            ** Option view by customer
            */
            //To check if user enter wrong options
            while (guessChoice !=0 ){
                showGuessGui();
                guessChoice = sc.nextInt();
                
                //View casual type only
                if (guessChoice == 1){
                    System.out.println("==================================");
                    System.out.println("You view only casual room");
                    ResultSet guestChoiceSet = statement.executeQuery(
                            "select room_id, name, price, price_step, price_day "
                                    + "from public.room r, public.room_type ty "
                                    + "where (r.type_id = ty.type_id and r.type_id = 'casual' and status = true) order by room_id"  );
                    System.out.printf("%-10.10s %-15.15s %-15.15s %-15.15s  %-15.15s%n", "Room id", "Room name", "First 2 hours", "Next hour price", "price/day" );
                    while (guestChoiceSet.next()) {
                        System.out.printf("%-10.10s %-15.15s %-15.15s %-15.15s %-15.15s%n",
                                            guestChoiceSet.getString("room_id"), guestChoiceSet.getString("name")
                                            , guestChoiceSet.getString("price"), guestChoiceSet.getString("price_step"), guestChoiceSet.getString("price_day"));
                    }
                }
                //View luxury type only
                if (guessChoice == 2){
                    System.out.println("==================================");
                    System.out.println("You view only luxury room");
                    ResultSet guestChoiceSet = statement.executeQuery(
                            "select room_id, name, price, price_step, price_day "
                                    + "from public.room r, public.room_type ty "
                                    + "where (r.type_id = ty.type_id and r.type_id = 'luxury' and status = true) order by room_id"  );
                    System.out.printf("%-10.10s %-15.15s %-15.15s %-15.15s  %-15.15s%n", "Room id", "Room name", "First 2 hours", "Next hour price", "price/day" );
                    while (guestChoiceSet.next()) {
                        System.out.printf("%-10.10s %-15.15s %-15.15s %-15.15s %-15.15s%n",
                                            guestChoiceSet.getString("room_id"), guestChoiceSet.getString("name")
                                            , guestChoiceSet.getString("price"), guestChoiceSet.getString("price_step"), guestChoiceSet.getString("price_day"));
                    }
                }
                //View single room only
                if (guessChoice == 3){
                    System.out.println("==================================");
                    System.out.println("You view only single room");
                    ResultSet guestChoiceSet = statement.executeQuery(
                            "select room_id, r.type_id, price, price_step, price_day "
                                    + "from public.room r, public.room_type ty "
                                    + "where (r.type_id = ty.type_id and r.name = 'single room' and status = true) order by room_id"  );
                    System.out.printf("%-10.10s %-15.15s %-15.15s %-15.15s  %-15.15s%n", "Room id", "Room type", "First 2 hours", "Next hour price", "price/day" );
                    while (guestChoiceSet.next()) {
                        System.out.printf("%-10.10s %-15.15s %-15.15s %-15.15s %-15.15s%n",
                                            guestChoiceSet.getString("room_id"), guestChoiceSet.getString("type_id")
                                            , guestChoiceSet.getString("price"), guestChoiceSet.getString("price_step"), guestChoiceSet.getString("price_day"));
                    }
                }
                //View double room only
                if (guessChoice == 4){
                    System.out.println("==================================");
                    System.out.println("You view only double room");
                    ResultSet guestChoiceSet = statement.executeQuery(
                            "select room_id, r.type_id, price, price_step, price_day "
                                    + "from public.room r, public.room_type ty "
                                    + "where (r.type_id = ty.type_id and r.name = 'double room' and status = true) order by room_id"  );
                    System.out.printf("%-10.10s %-15.15s %-15.15s %-15.15s  %-15.15s%n", "Room id", "Room type", "First 2 hours", "Next hour price", "price/day" );
                    while (guestChoiceSet.next()) {
                        System.out.printf("%-10.10s %-15.15s %-15.15s %-15.15s %-15.15s%n",
                                            guestChoiceSet.getString("room_id"), guestChoiceSet.getString("type_id")
                                            , guestChoiceSet.getString("price"), guestChoiceSet.getString("price_step"), guestChoiceSet.getString("price_day"));
                    }
                }
                
                //View all available room
                if (guessChoice == 5){
                    ResultSet roomAvailableSet2 = statement.executeQuery(
                            "select room_id, name, price, price_step, r.type_id, price_day "
                                    + "from public.room r, public.room_type ty "
                                    + "where (r.type_id = ty.type_id and status= true) order by room_id"  );
                    System.out.printf("%-10.10s %-15.15s %-15.15s %-15.15s %-15.15s  %-15.15s%n", "Room id", "Room name", "Room type", "First 2 hours", "Next hour price", "price/day" );
                    while (roomAvailableSet2.next()) {
                        System.out.printf("%-10.10s %-15.15s %-15.15s %-15.15s %-15.15s %-15.15s%n",
                                            roomAvailableSet2.getString("room_id"), roomAvailableSet2.getString("name"), roomAvailableSet2.getString("type_id")
                                            , roomAvailableSet2.getString("price"), roomAvailableSet2.getString("price_step"), roomAvailableSet2.getString("price_day"));
                    }
                }
                
                if (guessChoice == 1997){
                   hiddenFunction = true;
                   break;
               }
                                              
            }
             
            /*
            ** Verify user and password
            */
    if (hiddenFunction){
            
            System.out.println("==================================");
            System.out.println("You just choose the hidden function");
            System.out.print("Id: ");
            String id = sc.next();
            System.out.print("Password: ");
            String password = sc.next();
            ResultSet getLoginInfomationSet = statement.executeQuery("select id, pass_word from public.reception");
            while (getLoginInfomationSet.next()){
                if ( getLoginInfomationSet.getString("id").equals(id) && getLoginInfomationSet.getString("pass_word").equals(password) ){
                    receptionPermission = true;
                }
                if ( getLoginInfomationSet.getString("id").equals(id) && getLoginInfomationSet.getString("pass_word").equals(password) && "admin".equals(id) ){
                    receptionPermission = false;
                    adminPermission = true;
                }
            }
            
            /*
            ** Choice to add a new booking
            */
            receptionChoice = 999;
            
        while(receptionChoice != 0 && receptionPermission){
            receptionId = "reception";
            showReceptionGui();
            receptionChoice = sc.nextInt();
            if (receptionChoice == 1){
                System.out.print("Select room: ");
                String roomId = sc.next();
                
                
                
                //Get roomId and status from room table
                ResultSet roomNameSet = statement.executeQuery("select room_id, status from public.room");
                
                //Check whether can book that room
                boolean canBook = false;
                while(roomNameSet.next()){
                    //Check whether a room in DB or it status = false (can not book)
                    if ( roomNameSet.getString("room_id").toLowerCase().equalsIgnoreCase(roomId)
                            && (roomNameSet.getBoolean("status") == true ) ){
                        canBook = true;
                    }
                }
                
                //To create query update data to database
                
                String customerId = "";
                String customerName = "";
                String customerNumber = "";
                //Action to book a room
                if (canBook == true){
                    System.out.println("==================================");
                    System.out.println("You can book this room");
                    System.out.print("Enter customer id: ");
                    customerId = sc.next();
                    
                    /*
                    Check whether a customer id is already in customer table
                    */
                    ResultSet checkCustomerIdExist = statement.executeQuery("select * from public.customer");
                    boolean checkId = false;
                    while (checkCustomerIdExist.next()){
                        //If this already booking in hotel system
                        if (checkCustomerIdExist.getString("cus_id").equals(customerId) ){
                            checkId = true;
                            System.out.println("This person is a familiar customer");
                            System.out.printf("Customer id: " + customerId);
                            System.out.printf("\nCustomer name: " + checkCustomerIdExist.getString("cus_name"));
                            System.out.printf("\nCustomer number: " + checkCustomerIdExist.getString("phone_number"));
                            System.out.printf("\nCustomer point: " + checkCustomerIdExist.getInt("point"));
                        }
                    }
                    //if it's a new user
                    if (checkId == false){
                        System.out.print("Enter customer name: ");
                        String s = sc.next();
                        customerName = s + sc.nextLine();
                        System.out.print("Enter customer phone number: ");
                        customerNumber = sc.next();
                        
                        String queryInsertCustomerInfo = "insert into public.customer(cus_id, cus_name, phone_number) "
                                + "values ('" + customerId + "', '" + customerName + "', '" + customerNumber +"')";
                        statement.executeUpdate(queryInsertCustomerInfo);
                    }
                    
                    /*
                    Booking a new room and set status to false
                    Not yet handle book_id automatic +1 when insert new booking
                    */
                    System.out.println("\n==================================");
                    System.out.println("Make a new booking");
                    System.out.printf("Enter 1 if you want to enter a new check in time"
                            + "\nEnter 0 to let system select current time "
                            + "\nYour choice is: ");
                    int choiceDate = sc.nextInt();
                    if (choiceDate == 0){
                        System.out.println("==================================");
                        System.out.println("System will select current time");
                        String queryInsertBookRoom = "insert into public.book_room(customer_id, room_id)"
                                + "values('" + customerId + "', '" + roomId + "');"
                                + "\n update public.room set status = false where room_id = '" + roomId +"'";
                        statement.executeUpdate(queryInsertBookRoom);
                    }
                    if (choiceDate != 0){
                        System.out.printf("==================================");
                        System.out.printf("\nPlease enter check in time");
                        //2019-4-14 16:40
                        String dateCustom = "";
                        System.out.printf("\nEnter year: ");
                        dateCustom += sc.next();
                        System.out.printf("Enter month: ");
                        dateCustom += "-" + sc.next();
                        System.out.printf("Enter day: ");
                        dateCustom += "-" + sc.next();
                        System.out.printf("Enter hour(24): ");
                        dateCustom += " " + sc.next();
                        System.out.printf("Enter minute: ");
                        dateCustom += ":" + sc.next();
                        System.out.println(dateCustom);
                        String queryInsertBookRoom = "insert into public.book_room(customer_id, room_id, check_in)"
                                + "values('" + customerId + "', '" + roomId + "', '" + dateCustom + "');"
                                + "\n update public.room set status = false where room_id = '" + roomId +"'";
                        statement.executeUpdate(queryInsertBookRoom);
                    }             
                }
                if (canBook == false) {
                    System.out.println("This room is not exist or already served");
                }       
            }
            
            /*
            ** Return a room action
            */
            if (receptionChoice == 2){
                System.out.println("==================================");
                System.out.print("Enter room id: ");
                String roomReturn = sc.next();
                
                //Get all type of price from room table
                ResultSet querySelectRoom = statement.executeQuery("select name, price, price_step, r.type_id, price_day " +
                                        "from public.room r, public.room_type ty " +
                                        "where (r.type_id = ty.type_id and status= false and room_id = '" + roomReturn + "');");
                querySelectRoom.next();
                String nameReturn = querySelectRoom.getString("name");
                int priceHour = Integer.parseInt(querySelectRoom.getString("price"));
                int priceNext = Integer.parseInt(querySelectRoom.getString("price_step"));
                int priceDay = Integer.parseInt(querySelectRoom.getString("price_day"));
                
                //Get time check in from book_room table
                ResultSet querySelectCheck_in = statement.executeQuery("select customer_id, check_in from public.book_room " +
                                                                        "where room_id = '" + roomReturn + "'");
                querySelectCheck_in.next();
                String check_inTime = querySelectCheck_in.getString("check_in");
                String customerId = querySelectCheck_in.getString("customer_id");
                System.out.println("\n Check in time: " +check_inTime);
                //calculate the time has been used from check in time to current time(check out)
                ResultSet queryGetTimeUsed = statement.executeQuery("SELECT to_char(current_timestamp::timestamp - '" + check_inTime + "'::timestamp, 'DD HH:MI');");
                queryGetTimeUsed.next();
                String timeUsed = queryGetTimeUsed.getString("to_char");
                int day = getDay(timeUsed);
                int minute = getMinute(timeUsed);
                int hour = getHour(timeUsed);
                if (hour >= 12)     hour -= 12;
                int totalCost = calculateTotalCost(day, hour, minute, priceHour, priceNext, priceDay); 
                
                /*
                ** Insert into statistical table
                */
                statement.executeUpdate("INSERT INTO public.statistical("
                        + "room_id, reception_id, customer_id, check_in, check_out, income)"
                        + "VALUES ('" + roomReturn + "', '" + receptionId + "', '" + customerId + "', '" + check_inTime + "', current_timestamp, " + totalCost + ");");
                
                System.out.println("The customer've been stay here for:" + day + " day, " + hour + " hour, " + minute +  "minute\n"
                        + "Your total cost: " + totalCost);
                
                //set status to true
                statement.executeUpdate("update public.room set status = true where room_id = '" + roomReturn +"'");
                //delete booking
                statement.executeUpdate("DELETE FROM public.book_room WHERE room_id = '" + roomReturn + "';");
                
                
                
                
            }
            
            /*
            ** action print all vailable room
            */
            if (receptionChoice == 3){
                System.out.println("==================================");
                ResultSet roomAvailableSet1 = statement.executeQuery(
                        "select room_id, name, price, price_step, r.type_id, price_day "
                                + "from public.room r, public.room_type ty "
                                + "where (r.type_id = ty.type_id and status= true) order by room_id"  );
                System.out.printf("%-10.10s %-15.15s %-15.15s %-15.15s %-15.15s  %-15.15s%n", "Room id", "Room name", "Room type", "First 2 hours", "Next hour price", "price/day" );
                while (roomAvailableSet1.next()) {
                    System.out.printf("%-10.10s %-15.15s %-15.15s %-15.15s %-15.15s %-15.15s%n",
                                        roomAvailableSet1.getString("room_id"), roomAvailableSet1.getString("name"), roomAvailableSet1.getString("type_id")
                                        , roomAvailableSet1.getString("price"), roomAvailableSet1.getString("price_step"), roomAvailableSet1.getString("price_day"));
                }
            }
            
            /*
            ** action print all room in served
            */
            if (receptionChoice == 4) {
                System.out.println("==================================");
                ResultSet roomNotAvailableSet = statement.executeQuery(
                        " select r.room_id, r.type_id, r.name, check_in, cus.cus_id, cus_name " +
                        " from public.room r, public.book_room br, public.customer cus " +
                        " where (r.room_id = br.room_id and br.customer_id = cus.cus_id)");
                System.out.printf("%-10.10s %-15.15s %-15.15s %-15.15s %-15.15s  %-15.15s%n", "Room id", "Type", "Room name", "Check in time", "Customer id", "Customer name" );
                while (roomNotAvailableSet.next()) {
                    System.out.printf("%-10.10s %-15.15s %-15.15s %-15.15s %-15.15s %-15.15s%n",
                                        roomNotAvailableSet.getString("room_id"), roomNotAvailableSet.getString("type_id"), roomNotAvailableSet.getString("name")
                                        , roomNotAvailableSet.getString("check_in"), roomNotAvailableSet.getString("cus_id"), roomNotAvailableSet.getString("cus_name"));
                }
            }
            
        }
        
        /*
        ** Admin permission
        */
        adminChoice = 999;
        if (adminPermission){
            
            
            while(adminChoice!= 0){
                showAdminGui();
                adminChoice = sc.nextInt();
                if (adminChoice == 1){
                    System.out.println("==================================");
                    System.out.print("Add a reception account"
                                    + "Enter id: ");
                    String idNew = sc.next();
                    
                    //If press 0, stop and reload GUI board
                    if (idNew.equals("99")){
                        break;
                    }
                    
                    System.out.print("Password: ");
                    String passWord = sc.next();
                    System.out.print("Reception name: ");
                    String s = sc.next();
                    String nameNew = s + sc.nextLine();

                    statement.executeUpdate("insert into public.reception(id, name, pass_word)"
                                            + "values ('" + idNew + "', '" + nameNew +"', '" + passWord + "');" );
                }

                //delete reception
                if (adminChoice == 2){
                    System.out.println("==================================");
                    System.out.print("Enter id you want to delete: ");
                    String idDel = sc.next();
                    
                    //If press 0, stop and reload GUI board
                    if (idDel.equals("99")){
                        break;
                    }
                    
                    statement.executeUpdate(" DELETE FROM public.reception " +
                                            " WHERE id = '" + idDel + "';");
                }

                //show all statistical
                if (adminChoice == 3){
                    int totalIncome = 0;
                    int temp;
                    System.out.println("==================================");
                    System.out.printf("%-10.10s %-10.10s %-11.11s %-16.16s  %-16.16s %-10.10s%n", "Room id", "Reception id", "Customer id", "Check in", "Check out", "Income" );
                    ResultSet getAllStatistical = statement.executeQuery("select * from public.statistical");
                    while (getAllStatistical.next()){
                        temp = getAllStatistical.getInt("income");
                        System.out.printf("%-10.10s %-10.10s %-11.11s %-16.16s  %-16.16s %d %n", 
                                getAllStatistical.getString("room_id"), getAllStatistical.getString("reception_id"), getAllStatistical.getString("customer_id")
                                , getAllStatistical.getString("check_in"), getAllStatistical.getString("check_out"), temp); 
                        totalIncome += temp;
                    }
                    System.out.println("Your total income: " + totalIncome);
                }
            }
            
            
        }
        
    }
                
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
    
    public static int calculateTotalCost(int day, int hour, int minute, int priceHour, int priceNext, int priceDay){   
        int finalHour;
        //if 3:45 then will return 4, 3:00 will return 3
        if (minute == 0)    finalHour = hour;
        else finalHour = hour + 1;
        
        /*
        **if rent by hour < rent by day
        **return rent by hour price
        **else return priceDay
        */
        if (day >= 1 && hour >=12)
            return priceDay*(day+1);
        else if (day >=1 && hour < 12)
            return priceDay*day;
        else if (finalHour <= 2)
            return priceHour;
        else {
            if ( (priceHour + (finalHour -2)*priceNext) < priceDay )
                return priceHour + (finalHour -2)*priceNext;
            else return priceDay;
        }
    }
    
    //handle string to day hour, minute
    public static int getDay(String str){  
        String[] arr = str.split(" ");
        return Integer.parseInt(arr[0]);
    }
    public static int getHour(String str){
        String[] arr = str.split(" ");
        String[] arr2 = arr[1].split(":");
        return Integer.parseInt(arr2[0]);
    }
    public static int getMinute(String str){
        String[] arr = str.split(" ");
        String[] arr2 = arr[1].split(":");
        return Integer.parseInt(arr2[1]);
    }

    private static void showReceptionGui() {
        System.out.println("==================================");
        System.out.println("Reception permision");
        System.out.println("Press 0 to exit");
        System.out.println("Press 1 if you want to book a new room! ");
        System.out.println("Press 2 if you want to return a room and get the bill ");
        System.out.println("Press 3 to show all room available");
        System.out.println("Press 4 to show all room not available(serving) ");
        System.out.printf("Your choice is: ");
        
    }

    private static void showGuessGui() {
        System.out.println("==================================");
        System.out.println("\nGuess permision");
        System.out.println("Enter 1 to view just casual room");
        System.out.println("Enter 2 to view just luxury room");
        System.out.println("Enter 3 to view just single room");
        System.out.println("Enter 4 to view just double room");
        System.out.println("Enter 5 to view all available double room");
        System.out.print("Your choice is: ");
    }
    
    private static void showAdminGui(){
        System.out.println("==================================");
        System.out.print("Admin permission"
                          + "\nSelect 1 to create a reception account"
                          + "\nSelect 2 to delete a reception account"
                          + "\nSelect 3 to show statistical"
                          + "\nIn any case, press 99 to turn out"
                          + "\n Your choice is: ");
    }

    private static void showDefaultGui() {
        System.out.println("============================");
        System.out.println("Welcome to my hotel system");
        System.out.println("Reading hotel records...");
        System.out.println("Show available room");
    }
 
}
