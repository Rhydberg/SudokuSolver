import java.awt.*;
import java.awt.event.*;

public class FrontEnd  extends Frame{

    TextField[][]cell;
    Button reset;
    Button solve;
    Label status;
    int board[][];

    public  FrontEnd()
    {
        setTitle("Sudoku Solver");
        setLayout(new FlowLayout());

        status=new Label();

        board=new int[9][9];
        for(int i=0;i<9;i++)
        for(int j=0;j<9;j++)
            board[i][j]=0;

        cell=new TextField[9][9];
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
                cell[i][j]=new TextField(1);
        reset=new Button("RESET");
        solve=new Button("SOLVE");

        Panel Grid=new Panel(new GridLayout(3,3,5,5));
        Panel subGrid[][]=new Panel[3][3];

        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                subGrid[i][j]=new Panel(new GridLayout(3,3));

        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                subGrid[i/3][j/3].add(cell[i][j]);
            }
        }

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                Grid.add(subGrid[i][j]);
            }
        }

        add(Grid);

        Panel controls=new Panel(new BorderLayout());
        controls.add(reset, BorderLayout.WEST);
        controls.add(solve,BorderLayout.EAST);

        add(controls);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        add(status);

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<9;i++)
                {
                    for(int j=0;j<9;j++)
                    {
                        cell[i][j].setText("");
                        cell[i][j].setEditable(true);

                        board[i][j]=0;
                    }
                }
            }
        });

        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for(int i=0;i<9;i++)
                {
                    for(int j=0;j<9;j++)
                    {
                        cell[i][j].setEditable(false);
                        String txt=cell[i][j].getText();
                        if(!(txt.trim().length()==0))
                        {
                            board[i][j]=Integer.parseInt(txt.trim());
                        }
                        else
                        {
                            board[i][j]=0;
                        }
                    }
                }

                status.setText("SOLVING...");
                board=Backend.resolve(board);
                status.setText("SOLVED");

                if(!(board[0][0]==0))
                {
                    for(int i=0;i<9;i++)
                    {
                        for(int j=0;j<9;j++)
                        {
                            cell[i][j].setText(""+board[i][j]);
                        }
                    }
                }
                else
                {
                    status.setText("UNSOLVABLE");
                }
            }
        });

        pack();
        setVisible(true);

    }

    public static void main(String[] args) {
        new FrontEnd();
        System.out.print("ALl is fine");
    }
}
