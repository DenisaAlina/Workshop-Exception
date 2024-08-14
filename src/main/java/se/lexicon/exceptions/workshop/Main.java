package se.lexicon.exceptions.workshop;

import java.io.IOException;
import java.util.List;

import se.lexicon.exceptions.workshop.data_access.NameService;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.exceptions.DuplicateNameException;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

public class Main {

	public static void main(String[] args) {
		
		List <String> maleFirstNames = CSVReader_Writer.getMaleFirstNames();
        List <String> femaleFirstNames = CSVReader_Writer.getFemaleFirstNames();

        List<String> lastNames = null;
        try {
            lastNames = CSVReader_Writer.getLastNames();
        } catch (IOException e) {
            System.err.println("Failed to read last names: " + e.getMessage());
        }


        NameService nameService = new NameService(maleFirstNames, femaleFirstNames,lastNames);


        Person test = nameService.getNewRandomPerson();
        System.out.println(test);
        // Try adding new names and handle potential duplicates
        try {
            nameService.addFemaleFirstName("Alice");
        } catch (DuplicateNameException e) {
            System.err.println("Error adding female name: " + e.getMessage());
        }

        try {
            nameService.addMaleFirstName("Bob");
        } catch (DuplicateNameException e) {
            System.err.println("Error adding male name: " + e.getMessage());
        }

        try {
            nameService.addLastName("Smith");
        } catch (DuplicateNameException e) {
            System.err.println("Error adding last name: " + e.getMessage());
        }


	}

}
