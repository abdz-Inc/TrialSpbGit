package com.abdz.trialspbgit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.abdz.trialspbgit.dao.MeterDAO;
import com.abdz.trialspbgit.dao.PowerDAO;
import com.abdz.trialspbgit.dao.ProductDAO;
import com.abdz.trialspbgit.dao.RequestDAO;
import com.abdz.trialspbgit.dao.UserDAO;
import com.abdz.trialspbgit.enitity.Meter;
import com.abdz.trialspbgit.enitity.Power;
import com.abdz.trialspbgit.enitity.Product;
import com.abdz.trialspbgit.enitity.Request;
import com.abdz.trialspbgit.enitity.User;

@SpringBootApplication
public class TrialspbgitApplication{

	public static void main(String[] args) {
		SpringApplication.run(TrialspbgitApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ProductDAO pDAO) {
		return runner -> {
			// User user = new User(10000, "jhbwhdcw", "dcbhjh", "dhcld", "hifvhb cuhbc dch chdoi dihc ioudhci");
			// Product product = new Product(0, 0, 0, null);
			// Request r = new Request(0, 0, 0, null, 0, 0, null);
			// Meter m = new Meter(0, "hb", "gviki", "8978654");
			// Power p = new Power(100002,"generated", 1, null);
			// List<Request> recv = pDAO.findAllRecievedRequest(1);
			// System.out.println("recieved");
			// for(Request r:recv)
			// {
			// 	System.out.println(r);
			// }

			// List<Request> sent = pDAO.findAllSentRequest(1);
			// System.out.println("sent");
			// for(Request r:sent)
			// {
			// 	System.out.println(r);
			// }

			// Power p = pDAO.findById(1);
			// System.out.println(p);

			// List<Power> p = pDAO.findByIdAndMode(100001, "generated");
			// for(Power r:p)
			// {
			// 	System.out.println(p);
			// }

			// Power p = pDAO.findById(1);
			// p.setQuantity(120);
			// pDAO.update(p);
			
			
			// pDAO.delete(5);
			// System.out.println("Deleted. ");

			// List<Product> p = pDAO.findAll();
			// for(Product r:p)
			// {
			// 	System.out.println(p);
			// }

		};

	}

}
