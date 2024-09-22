package semProjJTTC;

import java.util.Scanner;

public class movieTicketSystem {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		// Initialize ticket prices and seats
		double childTicket = 8.00;
		double adultTicket = 12.00;
		double seniorTicket = 10.00;
		double studentTicket = 9.00;
		double totalCost = 0.0; // total booking cost
		
		String[] seats = new String [50];
		for (int i = 0; i < seats.length; i++) {
			seats[i] = "Available"; // initialize all seats to "Available"
		}
		
		// Movie selection
		String[] movies = {"1. Spooky Space Adventure 3", "2. The Rotten Orange","3. Underground Kings","4. Doomsday:Apocolypse"};
		System.out.println("Welcome to the ADC Movie Ticket Booking System!");
		System.out.println("Movies currently premiering: ");
		for (String movie : movies) {
			System.out.println(movie);
		}
		System.out.print("Select a movie by entering the corresponding number: ");
		int movieChoice = reader.nextInt();
		if (movieChoice < 1 || movieChoice > 4) {
			System.out.println("Invalid movie selection. Exiting.");
			return;
		}
		reader.nextLine();
		
		// Select number of Tickets
		int adultTicketNum, childTicketNum, seniorTicketNum, studentTicketNum;
		
		System.out.print("Enter the number of adult tickets: ");
		adultTicketNum = reader.nextInt();
		System.out.print("Enter the number of child tickets: ");
		childTicketNum = reader.nextInt();
		System.out.print("Enter the number of senior tickets: ");
		seniorTicketNum = reader.nextInt();
		System.out.print("Enter the number of student tickets: ");
		studentTicketNum = reader.nextInt();
		reader.nextLine();
		
		int totalTicks = adultTicketNum + childTicketNum + seniorTicketNum + studentTicketNum;
		if (totalTicks >50) {
			System.out.println("Not enough seats available. Please book 50 sests or fewer.");
			return;
		}
		// Seat Selection
		System.out.print("Enter how many seats you'd like to book: ");
		int seatsToBook = reader.nextInt();
		reader.nextLine();
		
		// booking multiple seats
		for (int bookingCount = 0; bookingCount < seatsToBook; bookingCount++) {
			System.out.println("---- ADC Movie Ticket Booking System----");
			displaySeats(seats); // display current seat availability
			
			// enter seat numbers separated by spaces
			System.out.print("Enter the seat numbers you want to book (e.g. 1 2 3) or 0 to stop: ");
			String seatInput = reader.nextLine(); // read user input as single line
			
			// split input by spaces to handle multiple seat #s
			String[] seatNumbers = seatInput.split(" ");
			
			for (String seatStr : seatNumbers) {
				// checks if user entered "0" which exits the booking process
				if (seatStr.equals("0")) {
					System.out.println("Exiting booking process.");
					return; // exits the program
				}
				// convert the seat string to an integer
				int seatNumber = 0;
				for (int i = 0; i < seatStr.length(); i++) {
					char c = seatStr.charAt(i);
					
					// make sure digit is between 0 and 9
					if (c >= '0' && c <= '9') {
						seatNumber = seatNumber * 10 + (c - '0'); // convert char to digit
					} else {
						System.out.println("Invalid input: '" + seatStr + "'. Please enter valid seat numbers.");
						seatNumber = -1; // marks as invalid
						break;
					}
				}
				
				// If seatNumber is valid and between 1 and 50
				if (seatNumber >= 1 && seatNumber <= 50) {
					if (seats[seatNumber -1].equalsIgnoreCase("Available")) {
						seats[seatNumber -1] = "Booked";
						System.out.println("Seat " +seatNumber + " sucessfully booked.");
					} else {
						System.out.println("Seat " +seatNumber + " is already booked.");
						bookingCount--; // asks for seat number again
					}
				} else if (seatNumber != -1) {
					System.out.println("Invalid seat number: " +seatNumber + ". Enter a number between 1 and 50.");
					bookingCount--; // re ask for valid seat
				}
				// Customer type for each ticket
				String[] customerTypes = new String[totalTicks];
		        System.out.println("Select customer type for each seat (1. Child, 2. Adult, 3. Senior, 4. Student). Enter the corresponding numbers for each seat:");
		        for (int i = 0; i < totalTicks; i++) {
		            System.out.print("Enter customer type for seat " + (i + 1) + ": ");
		            int customerType = reader.nextInt();
		            
		            // validate customer type and assign correct ticket prices
		            if (customerType >= 1 && customerType <= 4) {
		                switch (customerType) {
		                    case 1:
		                        totalCost += childTicket;
		                        customerTypes[i] = "Child";
		                        break;
		                    case 2:
		                        totalCost += adultTicket;
		                        customerTypes[i] = "Adult";
		                        break;
		                    case 3:
		                        totalCost += seniorTicket;
		                        customerTypes[i] = "Senior";
		                        break;
		                    case 4:
		                        totalCost += studentTicket;
		                        customerTypes[i] = "Student";
		                        break;
		                }
		            } else {
		                System.out.println("Invalid customer type. Try again.");
		                i--; // Re-ask for customer type
			}
		            // Apply discounts
		            System.out.print("Enter discount code (STUDENT10 for 10% off or SENIOR15 for 15% off) or 'no' for no discount: ");
		            String discountCode = reader.next();
		            double discount = 0.0;
		            
		            if (discountCode.equalsIgnoreCase("STUDENT10")) {
		            	discount = totalCost * 0.10;
		            	System.out.println("10% student discount applied.");
		            } else if (discountCode.equalsIgnoreCase("SENIOR15")) {
		            	discount = totalCost * 0.15;
		            	System.out.println("15% senior discount applied.");
		            } else if (!discountCode.equalsIgnoreCase("no")) {
		            	System.out.println("No discount applied");
		            }
		            
		            totalCost -= discount;
		            
		            // Final cost and summary
		            System.out.printf("Total cost after discount: $%.2f\n", totalCost);
		            System.out.println("Booking summary: ");
		            for (int ticketIndex = 0; ticketIndex < totalTicks; ticketIndex++) {
		            	System.out.println("Seat " + (ticketIndex + 1) + ": " + customerTypes[i]);
		            }
		            
		            System.out.println("Thank you for booking with us!");
		        }
			}
		}
		            }
		        public static void displaySeats(String[] seats) {
		        	System.out.println("CUrrent seat availability: ");
		        	for (int i = 0; i < seats.length; i++) {
		        		System.out.println("Seat " + (i + 1) + ": " + seats[i]);
		        	}
		        }
		
	}

