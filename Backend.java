public class Backend {
    private static int [][]board=new int[9][9];

    private static boolean check(int row,int col,int val)
    {
        boolean r=true,c=true,g=true;
        for(int i=0;i<9;i++)
        {
            if(board[row][i]==val)
            {
                r=false;
                break;
            }
        }
        for(int i=0;i<9;i++)
        {
            if(val==board[i][col])
            {
                c=false;
                break;
            }
        }
        int grno=(row/3)*3;//starting row of subgrid the cell belongs to
        int gcno=(col/3)*3;//starting column of subgrid the cell belongs to

        o:for(int i=grno;i<grno+3;i++)
        {
            for(int j=gcno;j<gcno+3;j++)
            {
                if(board[i][j]==val)
                {
                    g=false;
                    break o;
                }
            }
        }
        return r&&c&&g;
    }

    protected static boolean solve(int row, int col)
    {
        if(col==9)
            return true;
        if(row==9)
            return solve(0,col+1);
        if(board[row][col]>=1)
            return solve(row+1,col);
        for(int i=1;i<=9;i++) {
            if(check(row,col,i))
            {
                board[row][col]=i;
                if(solve(row+1,col))
                {
                    return true;
                }
                else
                {
                    board[row][col]=-1;
                }
            }
        }
        return false;
    }
    public static int[][] resolve(int[][]input)
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                board[i][j]=input[i][j];
            }
        }
        if(solve(0,0))
        {
            System.out.println("SOLVED");
            return board;
        }
        else
        {
            return new int[9][9];
        }
    }

}
/**
Mild:
x x 7 x x x 9 x x
2 x x 5 x 7 x x 6
x 8 x 1 x 4 x 7 x
x 4 x x 1 x x 3 x
6 x 1 x x x 8 x 9
x 9 x x 8 x x 6 x
x 5 x 8 x 9 x 1 x
1 x x 6 x 3 x x 2
x x 6 x x x 3 x x

Difficult:

x x x x x 8 x x 7
8 1 7 x x 2 x x x
x x x x 5 x x 1 4
7 x 2 x 9 x 1 x 5
x x x x x x x x x
5 x 8 x 7 x 3 x 2
3 7 x x 8 x x x x
x x x 4 x x 9 7 3
4 x x 5 x x x x x

Fiendish:

4 6 x x x 1 x x x
x x 2 x 9 6 x x x
x 3 x x x x x 6 8
x x x x x x x 3 7
x x x 6 x 7 x x x
5 1 x x x x x x x
8 4 x x x x x 5 x
x x x 7 1 x 9 x x
x x x 3 x x x 2 4

x x x x 7 x x x 1
6 4 x x x 2 x x 9
x x 8 x x x x x x
8 x 3 5 x x x x x
x x 4 x 8 x 9 x x
x x x x x 9 7 x 6
x x x x x x 3 x x
9 x x 1 x x x 6 2
5 x x x 4 x x x x

x x x x 5 7 x 8 x
3 x x x x x x x x
x x 2 9 x x 4 x x
2 x x 8 x 9 3 x x
4 x x x x x x x 7
x x 7 4 x 6 x x 8
x x 6 x x 2 7 x x
x x x x x x x x 5
x 9 x 3 1 x x x x
**/


