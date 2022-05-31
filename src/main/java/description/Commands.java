package description;

public enum Commands {
    ADD("add", "add new book"),
    SAVE("save", "save new book"),
    SHOW("show", "show all books"),
    EXIT("exit", "leave library"),
    HELP("help", "give list of all commands");
    public String value;
    public String description;

    Commands(String command, String description) {
        this.value = command;
        this.description = description;
    }
}
