import java.util.*;

public class TicTacToe{
    //O->user
    //X->machine
    
    int board[]= {1,2,3,4,5,6,7,8,9};
    char Nboard[]={'\0','\0','\0','\0','\0','\0','\0','\0','\0'};
    int magicSquare[]={2,7,6,9,5,1,4,3,8};
    ArrayList<Integer> user;
    ArrayList<Integer> machine;
   
    public TicTacToe(){
        user = new ArrayList<>();
        machine = new ArrayList<>();
    }


    boolean userTurn(int pos){
        if(board[pos-1]!=10 && board[pos-1]!=11){
            user.add(magicSquare[pos-1]);
            board[pos-1]=10;
            Nboard[pos-1]='O';
            return true;
        }
        return false;
    }

    void machineTurn(){
        boolean flag = false;
        if(machine.size()==0){
            board[(user.get(0)+1)%9]=11;
            Nboard[(user.get(0)+1)%9]='X';
            machine.add(magicSquare[(user.get(0)+1)%9]);
            flag=true;
            return;
        }
        if(!flag && machine.size()==1){
            for(int i=0; i<user.size()-1; i++){
                for(int j=i+1; j<user.size(); j++){
                    int temp = 15 - (user.get(i)+user.get(j));
                    int k=0;
                    for(k=0; k<this.magicSquare.length; k++){
                        if(magicSquare[k]==temp)
                            break;
                    }
                    System.out.println(k);
                    if(temp>=1 && temp<= 9 && board[k]!=10 && board[k]!=11){
                        board[k]=11;
                        Nboard[k]='X';
                        machine.add(temp);
                        flag=true;
                        return;
                    }
                }
            }
        }
        if(!flag && machine.size()>1){
            for(int i=0; i<user.size()-1; i++){
                for(int j=i+1; j<user.size(); j++){
                    int temp = 15 - (user.get(i)+user.get(j));
                    int k=0;
                    for(k=0; k<magicSquare.length; k++){
                        if(magicSquare[k]==temp)
                            break;
                    }
                    if(temp>=1 && temp<= 9 && board[k]!=10 && board[k]!=11){
                        board[k]=11;
                        Nboard[k]='X';
                        machine.add(temp);
                        flag=true;
                        return;
                    }
                }
            }
            for(int i=0; i<machine.size()-1; i++){
                for(int j=i+1; j<machine.size(); j++){
                    int temp = 15-(machine.get(i)+machine.get(j));
                    int k=0;
                    for(k=0; k<magicSquare.length; k++){
                        if(magicSquare[k]==temp)
                            break;
                    }
                    if(temp>=1 && temp<= 9 && board[k]!=10 && board[k]!=11){
                        board[k]=11;
                        Nboard[k]='X';
                        machine.add(temp);
                        flag=true;
                        return;
                    }
                }
            }

        }
        
        if(!flag){
            for(int i=0; i<board.length; i++){
                if(board[i]!=0 || board[i]!=1){
                    board[i]=11;
                    board[i]='X';
                    machine.add(magicSquare[i]);
                    break;
                }
            }
        }
        

    }



    boolean checkUserWin(){
        if((board[0]==10 && board[1]==10 && board[2]==10) || (board[3]==10 && board[4]==10 && board[5]==10) || (board[6]==10 && board[7]==10 && board[8]==10))
            return true;
        if((board[0]==10 && board[3]==10 && board[6]==10) || (board[3]==10 && board[4]==10 && board[7]==10) || (board[2]==10 && board[5]==10 && board[8]==10))
            return true;
        if((board[0]==10 && board[4]==10 && board[8]==10) || (board[2]==10 && board[4]==10 && board[6]==10))
            return true;
        return false;
    }

    boolean checkMachineWin(){
        if((board[0]==11 && board[1]==11 && board[2]==11) || (board[3]==11 && board[4]==11 && board[5]==11) || (board[6]==11 && board[7]==11 && board[8]==11))
            return true;
        if((board[0]==11 && board[3]==11 && board[6]==11) || (board[3]==11 && board[4]==11 && board[7]==11) || (board[2]==11 && board[5]==11 && board[8]==11))
            return true;
        if((board[0]==11 && board[4]==11 && board[8]==11) || (board[2]==11 && board[4]==11 && board[6]==11))
            return true;
        return false;
    }


    void displayBoard(){
        System.out.println("  "+Nboard[0]+" | "+Nboard[1]+" | "+Nboard[2]);
        System.out.println("----------------");
        System.out.println("  "+Nboard[3]+" | "+Nboard[4]+" | "+Nboard[5]);
        System.out.println("----------------");
        System.out.println("  "+Nboard[6]+" | "+Nboard[7]+" | "+Nboard[8]);
    }
    void displayBoard2(){
        System.out.println("  "+board[0]+" | "+board[1]+" | "+board[2]);
        System.out.println("----------------");
        System.out.println("  "+board[3]+" | "+board[4]+" | "+board[5]);
        System.out.println("----------------");
        System.out.println("  "+board[6]+" | "+board[7]+" | "+board[8]);
    }



    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        TicTacToe t = new TicTacToe();
        boolean flag = true;
        //show initial board
        System.out.println("Select board positions as : ");
        t.displayBoard2();
       
        System.out.println("O-> user X-> machine ");
        System.out.println("Initial board : ");
        t.displayBoard();
        while(flag){
            flag = false;
            System.out.println("User Turn : please enter the ramaining values from 1 to 9");
            int n=sc.nextInt();
            if(!t.userTurn(n)){
                flag=true;
                System.out.println("Wrong input Please enter again!");
                continue;

            }
            
            System.out.println("Current board Arrangement");
            t.displayBoard();
            if(t.checkUserWin()){
                System.out.println("User won!");
                flag = true;
                break;
            }


            System.out.println("Machine turn");
            t.machineTurn();
            
            System.out.println("Current board Arrangement");
            t.displayBoard();
            if(t.checkMachineWin()){
                System.out.println("Machine won!");
                flag = true;
                break;
            }

            for(int i=0; i<t.board.length; i++){
                if(t.board[i]!=10 && t.board[i]!=11){
                    flag=true;
                }
            }
            if(!flag){
                break;
            }
        }
        if(!flag){
            System.out.println("There is a Tie!");
        }
    }

}