import br.com.guilda.db.DatabaseInitializer;
import br.com.guilda.ui.MenuConsole;

public class Main {
    static void main() {

        DatabaseInitializer.init();

        MenuConsole menu = new MenuConsole();
        menu.iniciar();
    }
}
