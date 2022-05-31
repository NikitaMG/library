package model;

import Service.ComService;
import Service.LibrSerializer;
import com.fasterxml.jackson.databind.json.JsonMapper;
import description.Commands;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Initializer {
    private final static Map<String, Consumer<Scanner>> commands = new HashMap();

    private static LibrSerializer librSerializer = new LibrSerializer(new JsonMapper());

    private static ComService comService= new ComService();

    private static  Library libr;
    static {
        initializer();
        try {
            libr = librSerializer.getLib();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Your name: ");
        String command = scanner.nextLine().toLowerCase();
        System.out.println("write help if you need help: ");

        while (!command.equals("exit")){
            command = scanner.nextLine().toLowerCase();
            commands.getOrDefault(command,
                    scanner1 -> System.out.println("incorrect command")).accept(scanner);
        }
    }

    private static void initializer() {
        commands.put(Commands.HELP.value, scanner -> Arrays.stream(Commands.values()).forEach(System.out::println));
        commands.put(Commands.EXIT.value, scanner -> System.out.println("exit"));
        commands.put(Commands.SHOW.value, comService.getShowCommand(libr));
        commands.put(Commands.SAVE.value, comService.getSaveCommand(libr, librSerializer));
        commands.put(Commands.ADD.value, scanner -> {
            var bookBuilder = Book.builder();
            System.out.println("Book name: ");
            bookBuilder.name(scanner.nextLine());
            System.out.println("\nAuthor: ");
            bookBuilder.author(scanner.nextLine());
            System.out.println("\nDescription: ");
            bookBuilder.description(scanner.nextLine());
            System.out.println("\nNumber of page: ");
            Book book = bookBuilder.numberOfPage(scanner.nextInt()).build();
            libr.getBooks().put(book.getName(), book);
            System.out.println("\n this book added: ");
        });
    }

}
