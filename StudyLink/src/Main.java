import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main {
    public static void main(String[] args) {
        try {
            //create instance of the LoginPage
            new GUI();
        }
        catch(Exception e) {
            //handle exception
        }
    }
}