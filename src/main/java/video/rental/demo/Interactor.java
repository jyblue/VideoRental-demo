package video.rental.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Interactor {

	public void clearRentals(CmdUI cmdUI, int customerCode) {
		Customer foundCustomer = cmdUI.getRepository().findCustomerById(customerCode);
	
		if (foundCustomer == null) {
			System.out.println("No customer found");
		} else {
			System.out.println("Id: " + foundCustomer.getCode() + "\nName: " + foundCustomer.getName() + "\tRentals: "
					+ foundCustomer.getRentals().size());
			for (Rental rental : foundCustomer.getRentals()) {
				System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
				System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
			}
	
			List<Rental> rentals = new ArrayList<Rental>();
			foundCustomer.setRentals(rentals);
	
			cmdUI.getRepository().saveCustomer(foundCustomer);
		}
	}

	public void returnVideo(CmdUI cmdUI, int customerCode, String videoTitle) {
		Customer foundCustomer = cmdUI.getRepository().findCustomerById(customerCode);
		if (foundCustomer == null)
			return;
		
		List<Rental> customerRentals = foundCustomer.getRentals();
	
		for (Rental rental : customerRentals) {
			if (rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented()) {
				Video video = rental.returnVideo();
				video.setRented(false);
				cmdUI.getRepository().saveVideo(video);
				break;
			}
		}
	
		cmdUI.getRepository().saveCustomer(foundCustomer);
	}

	public void listVideos(CmdUI cmdUI) {
		List<Video> videos = cmdUI.getRepository().findAllVideos();
	
		for (Video video : videos) {
			System.out.println(
					"Video type: " + video.getVideoType() + 
					"\tPrice code: " + video.getPriceCode() + 
					"\tRating: " + video.getVideoRating() +
					"\tTitle: " + video.getTitle()
					); 
		}
	}

	public void listCutomers(CmdUI cmdUI) {
		List<Customer> customers = cmdUI.getRepository().findAllCustomers();
	
		for (Customer customer : customers) {
			System.out.println("ID: " + customer.getCode() + "\nName: " + customer.getName() + "\tRentals: "
					+ customer.getRentals().size());
			for (Rental rental : customer.getRentals()) {
				System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
				System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
				System.out.println("\tReturn Status: " + rental.getStatus());
			}
		}
	}

	public void getCustomerReport(CmdUI cmdUI, int code) {
		Customer foundCustomer = cmdUI.getRepository().findCustomerById(code);
	
		if (foundCustomer == null) {
			System.out.println("No customer found");
		} else {
			String result = foundCustomer.getReport();
			System.out.println(result);
		}
	}

	public void rentVideo(CmdUI cmdUI, int code, String videoTitle) {
		Customer foundCustomer = cmdUI.getRepository().findCustomerById(code);
		if (foundCustomer == null)
			return;
	
		Video foundVideo = cmdUI.getRepository().findVideoByTitle(videoTitle);
	
		if (foundVideo == null)
			return;
	
		if (foundVideo.isRented() == true)
			return;
	
		Boolean status = foundVideo.rentFor(foundCustomer);
		if (status == true) {
			cmdUI.getRepository().saveVideo(foundVideo);
			cmdUI.getRepository().saveCustomer(foundCustomer);
		} else {
			return;
		}
	}

	public void registerCustomer(CmdUI cmdUI, String name, int code, String dateOfBirth) {
		Customer customer = new Customer(code, name, LocalDate.parse(dateOfBirth));
		cmdUI.getRepository().saveCustomer(customer);
	}

	public void registerVideo(CmdUI cmdUI, String title, int videoType, int priceCode, LocalDate registeredDate, Rating rating) {
		Video video = new Video(title, videoType, priceCode, rating, registeredDate);
	
		cmdUI.getRepository().saveVideo(video);
	}

}
