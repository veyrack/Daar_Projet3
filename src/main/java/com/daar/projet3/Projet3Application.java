package com.daar.projet3;

import com.daar.projet3.models.CV;
import com.daar.projet3.services.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Projet3Application {


	@Autowired
	private CvService cvService;

	public static void main(String[] args) {
		SpringApplication.run(Projet3Application.class, args);

	}

	public void run(String... args) throws Exception {

		/*
		printElasticSearchInfo();
		List<String> l = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		l.add("Java");
		l.add("C");
		l.add("Haskell");
		l2.add("Java");

		cvService.save(new CV(1001, "Jean", "Lin",23,"a@b.fr","01", l));
		cvService.save(new CV(1002, "Claude", "Lil",23,"a@b.fr","01", l2));
		cvService.save(new CV(1003, "Pedro", "Lo",23,"a@b.fr","01", l2));
		cvService.save(new CV(1007, "George", "Lala",23,"a@b.fr","01", l));
		cvService.save(new CV(1008, "Max", "Li",23,"a@b.fr","01", l));
		//fuzzey search
		CV cv = cvService.findOne("1001");

		//List<Book> books = bookService.findByTitle("Elasticsearch Basics");

		System.out.println(cv);
		*/

	}
	/*
	private void printElasticSearchInfo() {

		System.out.println("--ElasticSearch--");
		Client client = es.get
				getClient();
		Map<String, String> asMap = client.settings().getAsMap();

		asMap.forEach((k, v) -> {
			System.out.println(k + " = " + v);
		});
		System.out.println("--ElasticSearch--");
	}
	*/
}
