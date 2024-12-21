import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // creating a TreeMap to store ISBNs (Strings) and Books
        TreeMap<String, Book> bookData = new TreeMap<String, Book>();

        // making a File for the book data
        File books = new File("BooksDataFile.txt");

        // making a Scanner
        Scanner scan;

        try {
            // Scanner reads the book data
            scan = new Scanner(books);
        } catch (FileNotFoundException e) {
            // if data for books is not found, print a message and exit out of main
            System.out.println("BooksDataFile not found");
            return;
        }

        // skip the header in the data file
        scan.nextLine();

        // while there are still lines in the data file
        while (scan.hasNextLine()) {
            // read in the next line of data
            String line = scan.nextLine();

            // split data at every ~
            String[] fields = line.split("~");

            // collect all the data at the appropriate columns
            String isbn = fields[2].trim();
            String authors = fields[3].trim();
            int pubYear = Integer.parseInt(fields[4].trim());
            String originalTitle = fields[5].trim();
            String title = fields[6].trim();
            double rating = Double.parseDouble(fields[7].trim());

            // use the data to create a Book
            Book book = new Book(isbn, authors, pubYear, originalTitle, title, rating);

            // put Books into the TreeMap
            bookData.put(isbn, book);
        }

        // close Scanner when all the data has been read into the TreeMap
        scan.close();


        // demonstrating TreeMap methods - displaying first 20 keys, titles, and authors in ascending order
        String[] keys = bookData.toKeyArray(new String[0]);

        for (int i = 0; i < 20; i++) {
            System.out.println("ISBN: " + keys[i] + ", Title: " + bookData.get(keys[i]).title() +
                    ", Author(s): " + bookData.get(keys[i]).authors());
        }
    }
}
