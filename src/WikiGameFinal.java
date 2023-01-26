import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;

public class WikiGameFinal implements ActionListener {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel header;
    private JLabel statusLabel;
    private JPanel controlPanel, printerPanel, resultPanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextArea a; // 1st text box
    private JTextArea b;// 2nd text box
    private JTextArea c; //result box
    private JScrollPane scroll;
    private String aRealText;
    private int xResult = 0;
    private int yResult = 0;
    private ArrayList<String> alreadyClicked = new ArrayList<>();
    private ArrayList<String> path = new ArrayList<>();
    private int WIDTH=800;
    private int HEIGHT=700;


    public WikiGameFinal() {
        prepareGUI();
    }

    public static void main(String[] args) {
        WikiGameFinal wiki = new WikiGameFinal();
        wiki.showEventDemo();
        //WikiGame w = new WikiGame();
        //wiki.WikiGame();

    }
    public void WikiGame(String s, String e) {

        String startLink = s;//"https://en.wikipedia.org/wiki/Barack_Obama";  // beginning link, where the program will start
        alreadyClicked.add(startLink);
        String endLink = e;//"https://en.wikipedia.org/wiki/Tom_Holland";    // ending link, where the program is trying to get to
        //String inLink = "Leonardo_Dicaprio"
        //maxDepth = 1;           // start this at 1 or 2, and if you get it going fast, increase
//        if (findLink(startLink, endLink, 0)) {
        if (findLink(s, e, 0)) {

            System.out.println("found it****");
            path.add(startLink);
        } else {
            System.out.println("couldn't find it****");
        }
        for (int i = 2; i >=0; i--) {
            System.out.println(path.get(i));
            c.append(path.get(i));
            c.append("\n");
        }

    }
    private void prepareGUI() {
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        mb.add(file);
        mb.add(edit);
        mb.add(help);
        mainFrame.add(mb);
        mainFrame.setJMenuBar(mb);

        a = new JTextArea();
        a.setEditable(true);
        b = new JTextArea();
        c = new JTextArea();

        a.setLineWrap(true);
        b.setLineWrap(true);
        c.setLineWrap(true);

        //c.setBounds(50,5,WIDTH-100,HEIGHT-50);

        headerLabel = new JLabel("Start Link");
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel = new JLabel("End Link");
        statusLabel.setSize(350, 100);
        statusLabel.setHorizontalAlignment(JLabel.CENTER);

        printerPanel = new JPanel();
        printerPanel.setLayout(new GridLayout(5,1));
        printerPanel.add(headerLabel, BorderLayout.NORTH);
        //1st print box

        printerPanel.add(a, BorderLayout.NORTH);
        printerPanel.add(statusLabel, BorderLayout.NORTH);
        // 2nd print box
        printerPanel.add(b, BorderLayout.NORTH);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(printerPanel, BorderLayout.NORTH);
        printerPanel.add(controlPanel, BorderLayout.CENTER);

        resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.add(c, BorderLayout.CENTER);
        c.setEditable(true);
        scroll = new JScrollPane(c);
        resultPanel.add(scroll, BorderLayout.CENTER);


        mainFrame.add(resultPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);

        //JScrollPane scrollableResultPanel = new JScrollPane(resultPanel);
        //scrollableResultPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //scrollableResultPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //mainFrame.getContentPane().add(scrollableResultPanel);
    }

    private void showEventDemo() {
        //headerLabel.setText("Control in action: Button");

        JButton okButton = new JButton("OK");
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        okButton.setActionCommand("OK");
        submitButton.setActionCommand("Submit");
        cancelButton.setActionCommand("Cancel");

        okButton.addActionListener(new ButtonClickListener());
        submitButton.addActionListener(new ButtonClickListener());
        cancelButton.addActionListener(new ButtonClickListener());

        controlPanel.add(okButton, BorderLayout.CENTER);
        //controlPanel.add(submitButton);
        //controlPanel.add(cancelButton);

        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            b.cut();
        if (e.getSource() == paste)
            b.paste();
        if (e.getSource() == copy)
            b.copy();
        if (e.getSource() == selectAll)
            b.selectAll();
    }
    public boolean findLink(String startLink, String endLink, int depth) {
        String answer = startLink;

        System.out.println("depth is: " + depth + ", link is: " + startLink);

        if (answer.equals(endLink)) {
            return true;
        } else if (depth == 2) {
            return false;
        } else {
            String newLink = null;
            try {
                System.out.println();
                System.out.print("hello \n");
                URL url = new URL(startLink);
                //use .contains(), .indexOf(), .substring()
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(url.openStream())
                );
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("/wiki/")) {
//                        System.out.println(line);
//                        System.out.println("IN THE IF STATEMENT");

                        xResult = line.indexOf("/wiki/");
//                        System.out.println("index of start of link is: " + xResult);
                        String halfString;
                        halfString = line.substring(xResult);
//                        System.out.println(halfString);
                        yResult = line.indexOf("\"", xResult);
//                        System.out.println("index of end of link is: " + yResult);


//                        System.out.println(line.substring(xResult, yResult));
                        String Links = "https://en.wikipedia.org" + line.substring(xResult, yResult);
                        System.out.println("print");
                        if (!alreadyClicked.contains(Links) && !alreadyClicked.contains(":")){
                            alreadyClicked.add(Links);
                            System.out.println("*****" + Links);
                            if (findLink(Links, endLink, depth + 1)) {
                                path.add(Links);
                                return true;
                            }
                        }
                    }
                }

                reader.close();
            } catch(Exception ex) {
                System.out.println(ex);
            }

        }
        System.out.println("end of findLink, returning false");
        return false;
    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("OK")) {
                path.clear();
                alreadyClicked.clear();
//                depth = 0;
//                findLink(depth.set(0));
                aRealText = a.getText();
//                System.out.println(aRealText);

                System.out.println(a.getText());
                System.out.println(b.getText());
                WikiGame(a.getText(), b.getText());
                //findLink(a.getText(), b.getText(), 2);
            } else if (command.equals("Submit")) {
                statusLabel.setText("Submit Button clicked.");
            } else {
                statusLabel.setText("Cancel Button clicked.");
            }
        }
    }
}