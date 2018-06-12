package pkg3dapp;

/**
 *  Главный класс с точкой входа
 * 
 * @author Timur
 */
public class Main {
    public static void main(String[] args) {
        MainFrame f = new MainFrame();
        Screen s = new Screen();
        f.add(s);
    }
}
