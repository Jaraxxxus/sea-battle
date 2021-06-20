package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.Objects;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class WindowApplic extends JFrame implements ActionListener {

    JButton aboutButton;
    JButton exitButton;
    JButton scoreButton;
    JButton gameButton;

    /**
     * Иконки для ячеек
     */
    private final ImageIcon alive = new ImageIcon(Objects.requireNonNull(getClass().getResource("alive.png")));
    private final ImageIcon water = new ImageIcon(Objects.requireNonNull(getClass().getResource("water.png")));
    private final ImageIcon injured = new ImageIcon(Objects.requireNonNull(getClass().getResource("injured.png")));
    private final ImageIcon missed = new ImageIcon(Objects.requireNonNull(getClass().getResource("missed.png")));
    /**
     * Эллементы главной панели
     * textLabel - label для отображения текстовой информации
     *
     * field1 - Поле игрока 1
     * field2 - Поле игрока 2
     */

    JPanel playerPane;
    JPanel npsPane;
    JPanel buttonPane;
    static JLabel textLabel;
    static JLabel textLabel2;
    GridBagLayout gbl;
    GridBagConstraints c;
    JButton[][] but;
    JButton[][] butNPS;
    ComGame game;
    int steps;
    {
        aboutButton = new JButton("About");
        gameButton = new JButton("New Game");
        scoreButton = new JButton("High Score");
        exitButton = new JButton("Exit");
        textLabel = new JLabel("Your turn ");
        textLabel2 = new JLabel("Computer turn ");
        but = new JButton[10][10];
        butNPS = new JButton[10][10];
        steps = 0;

    }

    public WindowApplic(ComGame agame)
    {
        super("See Battle" );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.game = agame;

        setSize(800, 600);
        setResizable(true);
        gbl = new GridBagLayout();
        c = new GridBagConstraints();

        getContentPane().setLayout(gbl);

        //--------------BUTTON PANE ---------------

        buttonPane = new JPanel(new GridLayout(6, 1));


        buttonPane.add(aboutButton);
        buttonPane.add(gameButton);
        buttonPane.add(scoreButton);
        buttonPane.add(exitButton);
        buttonPane.add(textLabel);
        textLabel.setVisible(false);
        buttonPane.add(textLabel);
        textLabel2.setVisible(false);

        c.anchor = GridBagConstraints.NORTH;
        c.weighty = 1;
        add( buttonPane);
        //------------playerPane -----------
        playerPane = new JPanel();
        playerPane.setLayout(new GridLayout(10,10, 1, 1));
        playerPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playerPane.setVisible(false);
       //---------------NPS PANE------
        npsPane = new JPanel();
        npsPane.setLayout(new GridLayout(10, 10, 1, 1));
        npsPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        npsPane.setVisible(false);

        initializeField(game.playerOne);

        add(playerPane);
        add(npsPane);
//===================================
exitButton.addActionListener(this);
aboutButton.addActionListener(this);
scoreButton.addActionListener(this);
gameButton.addActionListener(this);

//===================================
        setBounds(100,100,300,150);

        setVisible(true);

    }





    private void initializeField(FieldShip player) {
        if (player == null) return;

        ImageIcon elem ;
        ImageIcon elemNPS ;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                elem = water;
                elemNPS = water;


                JButton but1 = new JButton();
                JButton butNPS1 = new JButton();

                but1.setIcon(elem);
                but1.setPreferredSize(new Dimension(15, 15));
                but[i][j] = but1;
                playerPane.add(but[i][j]);

                butNPS1.setIcon(elemNPS);
                butNPS1.setPreferredSize(new Dimension(15, 15));
                butNPS[i][j] = butNPS1;
                npsPane.add(butNPS[i][j]);

                butNPS[i][j].addActionListener(this) ;




            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();

        //========== ABOUT =====================
        if(btn.equals(aboutButton)){
            ComAbout about = new ComAbout();
            showAbout(about.aboutGame);


        }
        //=========== EXIT ===============
        if(btn.equals(exitButton)){
            dispose();
            System.exit(1);


        }
        //========== SCORE =================
        if(btn.equals(scoreButton)){
            showRecordTable(game.score.tableRecord);


        }
        //=========== NEW GAME ==========
        if(btn.equals(gameButton)) {
            game.newGame();
            npsFieldNew();
            npsPane.setVisible(true);
            playerPane.setVisible(true);

            textLabel.setVisible(true);
            textLabel2.setVisible(true);
            ShouBattle( game.playerOne);


        }

         else {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++)
                if (btn == butNPS[i][j]) {
                    if(game.endGame){
                        textLabel.setText(" Game Over ");
                        npsPane.setEnabled(false);
                        break;
                    }
                    steps++;
                    String userCommand = Integer.toString(i)+"-"+
                            Integer.toString(j);
                    //textLabel.setText(userCommand);
                    textLabel.setText("Steps : "+ Integer.toString(steps));
                    game.play(userCommand);
                    ShouBattle( game.playerOne);


                }
            }

        }



    }

    private void npsFieldNew() {
        ImageIcon elem ;


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                elem = water;
                butNPS[i][j].setIcon(elem);
            }
        }
    }

    private void ShouBattle(FieldShip player) {

        if (player == null) return;
        ElementState st;
        ImageIcon elem = water;
        ImageIcon elemNPS = water;

        for (int i = 0; i < 10; i++) {
            for(int j = 0; j<10;j++) {
                st = player.battleMap[i][j].state;
                if (st == ElementState.enWell) {
                    elem = alive;

                } else if (st == ElementState.enKilled ||
                        st == ElementState.enInjured) {
                    elem = injured;
                } else if (st == ElementState.enMissed) {
                    elem = missed;

                } else
                    elem = water;

                switch (player.rivalMap[i][j]) {
                    case " " -> elemNPS = water;
                    case "░" -> elemNPS = alive;
                    case "X" -> elemNPS = injured;
                    case "/" -> elemNPS = missed;
                }


                butNPS[i][j].setIcon(elemNPS);
                but[i][j].setIcon(elem);
            }

        }
    }





    private void showRecordTable(List<String> tableRecord) {
        StringBuilder message = new StringBuilder();
       // message.append("DADA");
        for(String s:tableRecord){
            message.append(s).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString(), "High Score",
                INFORMATION_MESSAGE);
    }

    private void showAbout(List<String> aboutGame) {
        StringBuilder message = new StringBuilder();
        for(String s:aboutGame){
            message.append(s).append("\n");

        }
        JOptionPane.showMessageDialog(null, message.toString(), "About",
                INFORMATION_MESSAGE);
    }
}
