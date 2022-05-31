package Service;

import model.Library;

import java.util.Scanner;
import java.util.function.Consumer;

public class ComService {
    public Consumer<Scanner>getShowCommand(Library libr){
        return scanner -> libr.getBooks().forEach((k,v)-> System.out.println(v));
    }
    public  Consumer<Scanner>getSaveCommand(Library libr,LibrSerializer librSerializer){
        return scanner -> {
            librSerializer.saveLib(libr);
            System.out.println("book is added");
        };
    }
}
