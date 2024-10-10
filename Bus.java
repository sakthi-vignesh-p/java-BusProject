import java.text.SimpleDateFormat;
import java.util.*;

class bus
{
  int bus_number;
  int bus_seats;
  boolean bus_Ac;

  void set_bus (int input_bus_number, int input_bus_seats,
					 boolean input_bus_Ac)
  {
	 this.bus_number = input_bus_number;
	 this.bus_seats = input_bus_seats;
	 this.bus_Ac = input_bus_Ac;
  }									  //end of bus constructer:

  void display_bus_details ()
  {
	 System.out.print ("Bus number : " + bus_number + " , " + "bus  seats : " +
							 bus_seats);
	 if (bus_Ac == true)
		{
		  System.out.println (" ," + "Bus is Ac");
		}
	 else
		{
		  System.out.println (" ," + "Bus is  Non Ac");
		}
  }//end of display_bus_details:

  int get_bus_number ()
  {
	 return bus_number;
  }

  int get_bus_seats ()
  {
	 return bus_seats;
  }

  static boolean check (ArrayList < bus > buses, int x)
  {
	 int count = 0;
  for (bus check_new_bus:buses)
		{
		  if (check_new_bus.get_bus_number () == x)
			 {
				count = 1;
			 }
		}
	 if (count == 1)
		{
		  return true;
		}
	 else
		{
		  return false;
		}
  }

}	 //end of bus class:									 

class booking
{
  String passenger_name;
  String passenger_gender;
  Date travel_date = null;
  int passenger_bus_number;
  static int id = 0;
  int passenger_id=0;
  void set_booking ()
  {   
	  booking.id+=1;
	  this.passenger_id=id;
	  Scanner scan = new Scanner (System.in);
	   System.out.println ("Enter bus number ");
	   passenger_bus_number = scan.nextInt ();
	   System.out.println ("Enter your name");
	   passenger_name = scan.next ();
	   System.out.println ("Enter Gender");
	   passenger_gender = scan.next ();;
	   System.out.println ("Enter Date");
	  String input_Date = scan.next ();
	  SimpleDateFormat DateFormat = new SimpleDateFormat ("dd-MM-yyyy");

	   try
	 {
		travel_date = DateFormat.parse (input_Date);
	 }
	 catch (Exception e)
	 {
		e.printStackTrace ();
	 }
  }	 //end of booking constructer:								 


  int bus_capacity;
  int booked_tickets;
  boolean ticket_available (ArrayList < bus > buses,
									 ArrayList < booking > bookings)
  {

  for (bus check_bus:buses)
		{
		  if (check_bus.get_bus_number () == passenger_bus_number)
			 {
				this.bus_capacity = check_bus.get_bus_seats ();
			 }
		}
  for (booking check_book:bookings)
		{

		  if (check_book.passenger_bus_number == passenger_bus_number
				&& check_book.travel_date.equals (travel_date))
			 {
				booked_tickets++;
				passenger_id++;
			 }
		}

	 if (booked_tickets < bus_capacity)
		{
		  return true;
		}
	 else
		{
		  return false;
		}
  }									  //end of ticket_available method:

  void display_passenger_details ()
  {
	 System.out.println ("_______________________________________");
	 System.out.println ("passenger Id  : " + passenger_id + "\n" + "name :" +
								passenger_name + "\n" + "Gender : " +
								passenger_gender + "\n" + "travel date :" +
								travel_date);
	 System.out.println ("_______________________________________");
  }

  int get_pass_id ()
  {
	 return passenger_id;
  }

  boolean check (int input_passenger_id, ArrayList < booking > bookings)
  {
	 int cancel = 0;
  for (booking cancel_check:bookings)
		{
		  if (cancel_check.passenger_id == input_passenger_id)
			 {
				cancel = 1;
			 }
		}
	 if (cancel == 1)
		{
		  return true;
		}
	 return false;
  }


}										  //end of booking class

class Main
{
  static void line ()
  {
	 System.out.println ("_______________________________________");
  }

  public static void main (String[]args)
  {
	 Scanner scan = new Scanner (System.in);
	 boolean loop = true;
	 int user_option;
	 int total_bookings = 0;
	 int bus_count = 0;
	 ArrayList < bus > buses = new ArrayList < bus > ();
	 ArrayList < booking > bookings = new ArrayList < booking > ();
	 Map < Date, booking > map = new HashMap <> ();
	 Map < Integer, bus > bus_map = new HashMap <> ();


	 bus bus_1 = new bus ();
	 bus_1.set_bus (1, 21, true);
	 buses.add (bus_1);
	 bus_map.put (1, bus_1);
	 bus bus_2 = new bus ();
	 bus_2.set_bus (2, 21, false);
	 buses.add (bus_2);
	 bus_map.put (2, bus_2);
	 bus bus_3 = new bus ();
	 bus_3.set_bus (3, 45, true);
	 buses.add (bus_3);
	 bus_map.put (3, bus_3);
	 bus_count += 3;
	 while (loop == true)
		{
		  line ();
		for (bus b:buses)
			 {
				b.display_bus_details ();
			 }

		  line ();
		  System.out.println ("Enter 1 for booking" + "\n" +
									 "Enter 2 for cancel ticket" + "\n" +
									 "Enter 3 for Admin page" + "\n" +
									 "Enter 4 for view passengers" + "\n" +
									 "Enter 5 for exit");
		  line ();
		  user_option = scan.nextInt ();

		  switch (user_option)
			 {

			 case 1:
				if (bus_count > 0)
				  {
					 booking new_ticket = new booking ();
					 new_ticket.set_booking ();
					 if (new_ticket.ticket_available (buses, bookings))
						{
						  bookings.add (new_ticket);;
						  System.out.println ("your Booking is confirmed");
						  map.put (new_ticket.travel_date, new_ticket);
						  total_bookings++;
						}
					 else
						{
						  System.out.println
							 ("Sorry , Bus is full , try another bus or another date");
						}
				  }
				else
				  {
					 System.out.println ("No buses at now for booking");
				  }
				break;

			 case 2:
				if (total_bookings > 0)
				  {
					 booking cancel_book = new booking ();
					 Date cancel_date = null;
					 SimpleDateFormat date = new SimpleDateFormat ("dd-MM-yyyy");
					 int check_passenger_id;
					 System.out.println ("enter passenger Id");
					 check_passenger_id = scan.nextInt ();
					 System.out.println ("enter booked date");
					 String pass_date = scan.next ();

					 try
					 {
						cancel_date = date.parse (pass_date);
					 }
					 catch (Exception e)
					 {
						e.printStackTrace ();
					 }
					 if (map.containsKey (cancel_date)
						  && cancel_book.check (check_passenger_id, bookings))
						{
						  cancel_book = map.get (cancel_date);
						  map.remove (cancel_date);
						  bookings.remove (cancel_book);
		  System.out.println("your booking is cancelled");
						  total_bookings--;
						}
				  }
				else
				  {
					 System.out.println ("No bookings at now");
				  }

				break;

			 case 3:
				int admin_password = 9360;
				System.out.println ("Enter password");
				int input_password = scan.nextInt ();
				while (admin_password != input_password)
				  {
					 System.
						out.println ("Wrong password , enter password correctly " +
										 "\n" + "enter 0 for main menu");
					 input_password = scan.nextInt ();
					 if (input_password == 0)
						{
						  break;
						}
				  }
				if (input_password != 0)
				  {
					 line ();
					 System.out.println (" Enter 1 for add new bus" + "\n" +
												" Enter 2 for remove bus");
					 line ();
					 int Admin_option = scan.nextInt ();
					 if (Admin_option == 1)
						{
						  int new_bus_number = 1;
						  line ();
						  System.out.println ("Enter new bus number");
						  new_bus_number = scan.nextInt ();
						  while (bus.check (buses, new_bus_number))
							 {
								line ();
								System.out.println
								  ("the bus number is already exist ,enter another busnumber"
									+ "\n" + "enter 0 for main menu");
								new_bus_number = scan.nextInt ();
								if (new_bus_number == 0)
								  {
									 break;
								  }
							 }			  //while loop
						  System.out.println ("Enter number of seats");
						  int new_bus_seats = scan.nextInt ();
						  System.out.println ("Bus is Ac enter 1 or enter 2");
						  int new_bus_ac = scan.nextInt ();
						  boolean Ac;
						  if (new_bus_ac == 1)
							 {
								Ac = true;
							 }
						  else
							 {
								Ac = false;
							 }
						  bus new_bus = new bus ();
						  new_bus.set_bus (new_bus_number, new_bus_seats, Ac);
						  buses.add (new_bus);
						  bus_map.put (new_bus_number, new_bus);
						  System.out.println ("bus added successfully ");
						  bus_count++;
						}

					 if (Admin_option == 2)
						{
						  bus remove_bus = new bus ();
						  if (bus_count > 0)
							 {
								System.
								  out.println
								  ("enter bus number you wants to remove");
								int remove_bus_number = scan.nextInt ();
								if (bus_map.containsKey (remove_bus_number))
								  {
									 remove_bus = bus_map.get (remove_bus_number);
									 buses.remove (remove_bus);
									 bus_map.remove (remove_bus);
									 System.out.println ("bus  removed successfully");
									 bus_count--;
								  }
								else
								  {
									 System.out.println ("bus not found");
								  }
							 }
						  else
							 {
								System.out.println ("No buses at now");
							 }

						}
				  }
				break;

			 case 4:
				if (total_bookings > 0)
				  {
				  for (booking pass:bookings)
						{
						  pass.display_passenger_details ();
						}
				  }
				else
				  {
					 System.out.println ("no passengers at now");
				  }
				break;

			 case 5:
				loop = false;
				break;

			 default:
				System.out.println ("Enter valid option");

			 }							  //end of switch case
		}								  //end of l:oop
//end of main:
  }
}
