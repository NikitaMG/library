package model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Library {
    private Map<String,Book> books = new HashMap<>();
}
