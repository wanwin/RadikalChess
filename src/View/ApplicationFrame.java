package View;

import Model.ChessPiece;
import Model.aima.AdversarialSearch;
import Model.aima.AlphaBetaSearch;
import Model.aima.IterativeDeepeningAlphaBetaSearch;
import Model.aima.Metrics;
import Model.aima.MinimaxSearch;
import Model.aima.RadikalChessGame;
import Model.aima.RadikalChessState;
import Model.aima.XYLocation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ApplicationFrame extends JFrame{
    
    ChessPiece[] blackChessPieceSet;
    ChessPiece[] whiteChessPieceSet;
    static AdversarialSearch<RadikalChessState, XYLocation> search;
    private static String[] board=new String[4*6];
    
    public ApplicationFrame (ChessPiece[] blackChessPieceSet, ChessPiece[] whiteChessPieceSet){
        this.whiteChessPieceSet = whiteChessPieceSet;
        this.blackChessPieceSet = blackChessPieceSet;  
    }
    public JFrame constructApplicationFrame() {
        JFrame frame = new JFrame();
        JPanel panel = new RadikalChessPanel();
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private static class RadikalChessPanel extends JPanel implements ActionListener{
        
        JComboBox strategyCombo;
        JButton proposeButton;
        JButton[] squares;
        JLabel statusBar;
        RadikalChessGame game;
        RadikalChessState currState;
        Metrics searchMetrics;
        RadikalChessPanel(){
            this.setLayout(new BorderLayout());
            JToolBar toolbar = new JToolBar();
            toolbar.setFloatable(false);
            strategyCombo = new JComboBox(new String[] {"Minimax", "Alpha-Beta", "Iterative Deepening Alpha-Beta"});
            strategyCombo.setSelectedIndex(0);
            toolbar.add(strategyCombo);
            toolbar.add(Box.createHorizontalGlue());
            proposeButton = new JButton("Propose Move");
            proposeButton.addActionListener(this);
            toolbar.add(proposeButton);
            add(toolbar, BorderLayout.NORTH);
            JPanel spanel = new JPanel();
            spanel.setLayout(new GridLayout(4, 6));
            add(spanel, BorderLayout.CENTER);
            squares = new JButton[4*6];
            Font f = new java.awt.Font(Font.SANS_SERIF, Font.PLAIN, 32);
            for (int i = 0; i < 4*6; i++) {
                JButton square = new JButton("");
                square.setFont(f);
                if ((i % 2) == 0)
                    square.setBackground(Color.BLACK);
                else
                    square.setBackground(Color.WHITE);
                square.addActionListener(this);
                squares[i] = square;
                spanel.add(square);
            }
            statusBar = new JLabel(" ");
            statusBar.setBorder(BorderFactory.createEtchedBorder());
            add(statusBar, BorderLayout.SOUTH);
            game = new RadikalChessGame();
	    actionPerformed(null);
        }

        @Override
        public void actionPerformed(ActionEvent ae){
            if (ae.getSource() == proposeButton)
                proposeMove();
            else{
                for (int i = 0; i < 4*6; i++)
                    if (ae.getSource() == squares[i])
                        currState = game.getResult(currState, new XYLocation(i % 4, i / 6));
            }
            for (int i = 0; i < 4*6; i++){
                String val = currState.getValue(i % 4, i / 6);
                if (val == RadikalChessState.EMPTY)
                    val = "";
                squares[i].setText(val);
            }
            updateStatus();
        }

        /** Uses adversarial search for selecting the next action. */
        private void proposeMove() {            
            XYLocation action;
            switch (strategyCombo.getSelectedIndex()){
            case 0:
                search = MinimaxSearch.createFor(game);
                break;
            case 1:
                search = AlphaBetaSearch.createFor(game);
                break;
            case 2:
                search = IterativeDeepeningAlphaBetaSearch.createFor(game, 0.0,
                        1.0, 1000);
                break;
            default:
                search = IterativeDeepeningAlphaBetaSearch.createFor(game, 0.0,
                        1.0, 1000);
                ((IterativeDeepeningAlphaBetaSearch<?, ?, ?>) search)
                        .setLogEnabled(true);
            }
            action = search.makeDecision(currState);
            searchMetrics = search.getMetrics();
            currState = game.getResult(currState, action);
        }

        /** Updates the status bar. */
        private void updateStatus(){
            String statusText;
            if (game.isTerminal(currState)){
                if (game.getUtility(currState, RadikalChessState.X) == 1)
                    statusText = "X has won :-)";
                else if (game.getUtility(currState, RadikalChessState.O) == 1)
                    statusText = "O has won :-)";
                else
                    statusText = "No winner...";
                System.out.print(search.getTotalExpandedNodes());
            }else
                statusText = "Next move: " + game.getPlayer(currState);
            if (searchMetrics != null)
                statusText += "    " + searchMetrics;
            statusBar.setText(statusText);
        }
    }
}
