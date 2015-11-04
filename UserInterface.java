import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class UserInterface extends JFrame {

    private static final short maxX = 300;
    private static final short maxY = 300;
    private static final short windowZoom = 2;

    //**rozmisteni UI elements//
    private JPanel rootPanel;
    private JTabbedPane tabs;
    private JPanel park;
    private JPanel rozmisteni;
    private JPanel udaje;
    private JLabel introLabel;
    private JLabel drawLabel;
    private JLabel chooseLabel;
    private JLabel editLabel;
    private JPanel mapa;
    private JButton drawButton;
    private JButton editButton;
    private JButton deleteButton;
    private JComboBox select;
    private JButton polygonButton;
    private JButton circleButton;
    private JButton saveButton;
    private Rozmisteni polygon;
    private Rozmisteni circle;
    private Image shape;
    //**rozmisteni UI elements//

    public UserInterface() {
        createGUI();
    }
    /** Method creates the main frame */
    public void createGUI() {
        //initialization of the main frame
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("hhh");
        frame.getContentPane().add(rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(maxX * windowZoom, maxY * windowZoom);
        //End of initialization of the main frame

        //Buttons actions list on tab "Rozmisteni"
        ActionListener actionListener = new ButtonActionListener();
        drawButton.addActionListener(actionListener);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseLabel.setEnabled(false);
                selectVozidlo.setEnabled(false);
                polygonButton.setEnabled(false);
                circleButton.setEnabled(false);
                mapaBrna.setEnabled(true);
            }
        });

        ActionListener actionListenerShapes = new ShapeButtonActionListener();
        polygonButton.addActionListener(actionListenerShapes);
        circleButton.addActionListener(actionListenerShapes);
        //End of buttons actions list on tab "Rozmisteni"

        //Initialization of drawable component "mapa" on "Rozmisteni" tab
        mapa = new JPanel() {
            public void paintComponent (Graphics g) {
                if ( shape == null) {
                    shape = createImage(getSize().width, getSize().height);
                    g=(Graphics2D)shape.getGraphics();
                }
                g.drawImage(shape, 0, 0, null);
            }
        };

    }

    tab
    public class ShapeButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == polygonButton) {
                polygon.drawPolygon();
            } else if (e.getSource() == circleButton) {
                circle.drawCircle();
            }
        }

    }
    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           chooseLabel.setEnabled(true);
            selectVozidlo.setEnabled(true);
            polygonButton.setEnabled(true);
            circleButton.setEnabled(true);
            mapaBrna.setEnabled(true);
        }

    }
    //End of Action listeners list for "Rozmisteni" tab








}
