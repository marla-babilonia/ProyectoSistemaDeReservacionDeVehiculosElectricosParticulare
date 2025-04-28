
import menus.Menu;

public class Main {
    public static void main(String[] args) {
        Menu.displayMainMenu();
    }
}


// Get-ChildItem -Recurse -Filter *.class | Remove-Item
//javac -d bin -sourcepath Src Src/Main.java
//javac -d bin src\CSVHandlers\*.java src\HelpfulClasses\*.java src\information\*.java src\menus\*.java src\*.java
//java -cp bin Main