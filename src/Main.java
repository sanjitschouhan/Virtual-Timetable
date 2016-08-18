
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Main Frame
 *
 * @author Sanjit Singh Chouhan
 */
public class Main extends Frame {

    //Variables
    /**
     * Panel which stores all the other Panels used in program<p>
     * This panel has a CardLayout
     */
    static JPanel Pan;
    /**
     * CardLayout used for Panel Pan
     */
    static CardLayout Card;

    static Functions fn;
    static Main auto_TT;

    public static void main(String[] args) {
        try {
            Functions.openDatabase();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error: File Not Found \"Database.txt\"");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error Reading from file \"Database.txt\"");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error: Class Not Found");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: Invalid Username or Password");
        }
        auto_TT = new Main();
    }

    /**
     * Main Frame Constructor
     */
    Main() {
        /*
         Create new functions object to perform operations
         */
        fn = new Functions();
        /*
         window listener to respond to window actions like close, minimise, etc.
         */
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        /*
         Set new layout to frame
         */
        setLayout(new BorderLayout());
        /**
         * <code>Title</code> contains heading
         */
        JLabel Title = new JLabel("Time Table");
        Title.setFont(new java.awt.Font("Ubuntu Mono", 1, 24));
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(Title, BorderLayout.NORTH);     //add title to output

        /*
         Layout for displaying output
         */
        Card = new CardLayout();
        Pan = new JPanel();
        Pan.setLayout(Card);

        /*
         Initialize HomePage
         */
        HomePage.init(this);
        HomePage.initHomePage(this);

        /*
         Initialize ShedulePage
         */
        SchedulePage.init();
        SchedulePage.initSchedulePage();

        /*
         Add Pan Panel to output and display HomePage
         */
        Pan.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(Pan);
        Card.show(Pan, "HomePage");

        /*
         Set Window Properties and display output
         */
        pack();
        setVisible(true);
        setResizable(false);
    }

}//main class