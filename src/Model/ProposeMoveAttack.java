package Model;

import Model.Pieces.Bishop;
import Model.Pieces.King;
import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import Model.Pieces.Rook;

public class ProposeMoveAttack {
    
    private static ProposeMoveAttack instance;

    private ProposeMoveAttack() {
    }
    
    public static ProposeMoveAttack getInstance(){
        if(instance==null)instance=new ProposeMoveAttack();
        return instance;
    }
    
    public boolean selectMoveAttack(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if(chessPiece instanceof Pawn)return attackPawn(chessPiece, movement, chessBoard);
        if(chessPiece instanceof Rook)return attackRook(chessPiece, movement, chessBoard);
        if(chessPiece instanceof Bishop)return attackBishop(chessPiece, movement, chessBoard);
        if(chessPiece instanceof Queen)return attackQueen(chessPiece, movement, chessBoard);
        if(chessPiece instanceof King)return attackKing(chessPiece, movement, chessBoard);
        return false;
    }
    
    private boolean attackPawn(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if("White".equals(chessPiece.getColour())){
            if(movement.getOrigin().getRow()-movement.getDestination().getRow()==1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==-1){
                if(chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece()!=null&&
                        "Black".equals(chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece().getColour()))
                    return true;
            }
            if(movement.getOrigin().getRow()-movement.getDestination().getRow()==1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==1){
                if(chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece()!=null&&
                        "Black".equals(chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece().getColour()))
                    return true;
            }
        }
        if("Black".equals(chessPiece.getColour())){
            if(movement.getOrigin().getRow()-movement.getDestination().getRow()==-1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==1){
                if(chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece()!=null&&
                        "White".equals(chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece().getColour()))
                    return true;
            }
            if(movement.getOrigin().getRow()-movement.getDestination().getRow()==-1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==-1){
                if(chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece()!=null&&
                        "White".equals(chessBoard.getCell()[movement.getDestination().getRow()]
                                [movement.getDestination().getColumn()].getChessPiece().getColour()))
                    return true;
            }
        }
        return false;
    }
    
    private boolean attackRook(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        return moveHorizontalAttack(chessPiece, movement, chessBoard)||
                moveVerticalAttack(chessPiece, movement, chessBoard);
    }
    
    private boolean attackBishop(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        return moveDiagonalDownAttack(chessPiece, movement, chessBoard)||
                moveDiagonalUpAttack(chessPiece, movement, chessBoard);
    }
    
    private boolean attackQueen(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        return (moveHorizontalAttack(chessPiece, movement, chessBoard)||
                moveVerticalAttack(chessPiece, movement, chessBoard)||
                moveDiagonalDownAttack(chessPiece, movement, chessBoard)||
                moveDiagonalUpAttack(chessPiece, movement, chessBoard));
    }
    
    private boolean attackKing(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
            if(chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()!=null&&
                    chessBoard.getCell()[movement.getDestination().getRow()]
                    [movement.getDestination().getColumn()].getChessPiece().getColour()!=
                    chessPiece.getColour()&&movement.getOrigin().getRow()-
                    movement.getDestination().getRow()==1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==0)
                return true;
            if(chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()!=null&&
                    chessBoard.getCell()[movement.getDestination().getRow()]
                    [movement.getDestination().getColumn()].getChessPiece().getColour()!=
                    chessPiece.getColour()&&movement.getOrigin().getRow()-
                    movement.getDestination().getRow()==1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==-1)
                return true;
            if(chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()!=null&&
                    chessBoard.getCell()[movement.getDestination().getRow()]
                    [movement.getDestination().getColumn()].getChessPiece().getColour()!=
                    chessPiece.getColour()&&movement.getOrigin().getRow()-
                    movement.getDestination().getRow()==0&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==-1)
                return true;
            if(chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()!=null&&
                    movement.getDestination().getRow()-movement.getOrigin().getRow()==1&&
                    chessBoard.getCell()[movement.getDestination().getRow()]
                    [movement.getDestination().getColumn()].getChessPiece().getColour()!=
                    chessPiece.getColour()&&movement.getOrigin().getRow()-
                    movement.getDestination().getRow()==-1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==-1)
                return true;
            if(chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()!=null&&
                    chessBoard.getCell()[movement.getDestination().getRow()]
                    [movement.getDestination().getColumn()].getChessPiece().getColour()!=
                    chessPiece.getColour()&&movement.getOrigin().getRow()-
                    movement.getDestination().getRow()==-1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==0)
                return true;
            if(chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()!=null&&
                    chessBoard.getCell()[movement.getDestination().getRow()]
                    [movement.getDestination().getColumn()].getChessPiece().getColour()!=
                    chessPiece.getColour()&&movement.getOrigin().getRow()-
                    movement.getDestination().getRow()==-1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==1)
                return true;
            if(chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()!=null&&
                    chessBoard.getCell()[movement.getDestination().getRow()]
                    [movement.getDestination().getColumn()].getChessPiece().getColour()!=
                    chessPiece.getColour()&&movement.getOrigin().getRow()-
                    movement.getDestination().getRow()==0&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==1)
                return true;
            if(chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()!=null&&
                    chessBoard.getCell()[movement.getDestination().getRow()]
                    [movement.getDestination().getColumn()].getChessPiece().getColour()!=
                    chessPiece.getColour()&&movement.getOrigin().getRow()-
                    movement.getDestination().getRow()==1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==1)
                return true;
        return false;
    }

    private boolean moveHorizontalAttack(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard) {
        if(movement.getOrigin().getColumn()-movement.getDestination().getColumn()>0&&
                movement.getOrigin().getRow()-movement.getDestination().getRow()==0){
            for (int i=movement.getOrigin().getColumn()-1;i>=movement.getDestination().getColumn();i--) {
                if(i>movement.getDestination().getColumn()&&
                        chessBoard.getCell()[movement.getDestination().getRow()]
                        [i].getChessPiece()!=null)return false;
                else if(i==movement.getDestination().getColumn()&&
                        chessBoard.getCell()[movement.getDestination().getRow()]
                        [i].getChessPiece()!=null&&
                        chessBoard.getCell()[movement.getDestination().getRow()]
                        [i].getChessPiece().getColour()!=chessPiece.getColour())return true;
            }
            return false;
        }
        if(movement.getOrigin().getColumn()-movement.getDestination().getColumn()<0&&
                movement.getOrigin().getRow()-movement.getDestination().getRow()==0){
            for (int i=movement.getOrigin().getColumn()+1;i<=movement.getDestination().getColumn();i++) {
                if(i<movement.getDestination().getColumn()&&
                        chessBoard.getCell()[movement.getDestination().getRow()]
                        [i].getChessPiece()!=null)return false;
                else if(i==movement.getDestination().getColumn()&&
                        chessBoard.getCell()[movement.getDestination().getRow()]
                        [i].getChessPiece()!=null&&
                        chessBoard.getCell()[movement.getDestination().getRow()]
                        [i].getChessPiece().getColour()!=chessPiece.getColour())return true;
            }
        }
        return false;
    }

    private boolean moveVerticalAttack(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard) {
        if(movement.getOrigin().getRow()-movement.getDestination().getRow()>0&&
                movement.getOrigin().getColumn()-movement.getDestination().getColumn()==0){
            for (int i=movement.getOrigin().getRow()-1;i>=movement.getDestination().getRow();i--) {
                if(i>movement.getDestination().getRow()&&chessBoard.getCell()[i]
                        [movement.getDestination().getColumn()].getChessPiece()!=null)return false;
                else if(i==movement.getDestination().getRow()&&chessBoard.getCell()[i]
                        [movement.getDestination().getColumn()].getChessPiece()!=null&&
                     chessBoard.getCell()[i][movement.getDestination().getColumn()].getChessPiece().getColour()!=
                        chessPiece.getColour())return true;
            }
            return false;
        }
        if(movement.getOrigin().getRow()-movement.getDestination().getRow()<0&&
                movement.getOrigin().getColumn()-movement.getDestination().getColumn()==0){
            for (int i=movement.getOrigin().getRow()+1;i<=movement.getDestination().getRow();i++) {
                if(i<movement.getDestination().getRow()&&chessBoard.getCell()[i]
                        [movement.getDestination().getColumn()].getChessPiece()!=null)return false;
                else if(i==movement.getDestination().getRow()&&chessBoard.getCell()[i]
                        [movement.getDestination().getColumn()].getChessPiece()!=null&&chessBoard.getCell()[i]
                        [movement.getDestination().getColumn()].getChessPiece().getColour()!=
                        chessPiece.getColour())return true;
            }
        }
        return false;
    }

    private boolean moveDiagonalDownAttack(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard) {
        if(Math.abs(movement.getOrigin().getRow()-movement.getDestination().getRow())==
                Math.abs(movement.getOrigin().getColumn()-movement.getDestination().getColumn())&&
                movement.getOrigin().getRow()<movement.getDestination().getRow()&&
                movement.getOrigin().getColumn()<movement.getDestination().getColumn()){
            for (int i=1;i<movement.getDestination().getColumn()-movement.getOrigin().getColumn() + 1;i++) {
                if(i<movement.getDestination().getColumn()-movement.getOrigin().getColumn()&&
                        chessBoard.getCell()[movement.getOrigin().getRow()+i]
                        [movement.getOrigin().getColumn()+i].getChessPiece()!=null)return false;
                else if(i==movement.getDestination().getColumn()-movement.getOrigin().getColumn()&&
                        chessBoard.getCell()[movement.getOrigin().getRow()+i]
                        [movement.getOrigin().getColumn()+i].getChessPiece()!=null&&
                        chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece().getColour()!=
                        chessPiece.getColour())return true;
            }
            return false;
        }
        if(Math.abs(movement.getOrigin().getRow()-movement.getDestination().getRow())==
                Math.abs(movement.getOrigin().getColumn()-movement.getDestination().getColumn())&&
                movement.getOrigin().getRow()<movement.getDestination().getRow()&&
                movement.getOrigin().getColumn()>movement.getDestination().getColumn()){
            for (int i=1;i<movement.getOrigin().getColumn()-movement.getDestination().getColumn() + 1;i++) {
                if(i<movement.getOrigin().getColumn()-movement.getDestination().getColumn()&&
                        chessBoard.getCell()[movement.getOrigin().getRow()+i]
                        [movement.getOrigin().getColumn()-i].getChessPiece()!=null)return false;
                else if(i==movement.getOrigin().getColumn()-movement.getDestination().getColumn()&&
                        chessBoard.getCell()[movement.getOrigin().getRow()+i]
                        [movement.getOrigin().getColumn()-i].getChessPiece()!=null&&
                        chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece().getColour()!=
                        chessPiece.getColour())return true;
            }
        }
        return false;
    }

    private boolean moveDiagonalUpAttack(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard) {
        if(Math.abs(movement.getOrigin().getRow()-movement.getDestination().getRow())==
                Math.abs(movement.getOrigin().getColumn()-movement.getDestination().getColumn())&&
                movement.getOrigin().getRow()>movement.getDestination().getRow()&&
                movement.getOrigin().getColumn()<movement.getDestination().getColumn()){
            for (int i=1;i<movement.getDestination().getColumn()-movement.getOrigin().getColumn() + 1;i++) {
                if(i<movement.getDestination().getColumn()-movement.getOrigin().getColumn()&&
                        chessBoard.getCell()[movement.getOrigin().getRow()-i]
                        [movement.getOrigin().getColumn()+i].getChessPiece()!=null)return false;
                else if(i==movement.getDestination().getColumn()-movement.getOrigin().getColumn()&&
                        chessBoard.getCell()[movement.getOrigin().getRow()-i]
                        [movement.getOrigin().getColumn()+i].getChessPiece()!=null&&
                        chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece().getColour()!=
                        chessPiece.getColour())return true;
            }
            return false;
        }
        if(Math.abs(movement.getOrigin().getRow()-movement.getDestination().getRow())==
                Math.abs(movement.getOrigin().getColumn()-movement.getDestination().getColumn())&&
                movement.getOrigin().getRow()>movement.getDestination().getRow()&&
                movement.getOrigin().getColumn()>movement.getDestination().getColumn()){
            for (int i=1;i<movement.getOrigin().getColumn()-movement.getDestination().getColumn() + 1;i++) {
                if(i < movement.getDestination().getColumn() - movement.getOrigin().getColumn() &&
                        chessBoard.getCell()[movement.getOrigin().getRow()-i]
                        [movement.getOrigin().getColumn()-i].getChessPiece()!=null)return false;
                else if(i==movement.getOrigin().getColumn()-movement.getDestination().getColumn()&&
                        chessBoard.getCell()[movement.getOrigin().getRow()-i]
                        [movement.getOrigin().getColumn()-i].getChessPiece()!=null&&
                        chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece().getColour()!=
                        chessPiece.getColour())return true;
            }
        }
        return false;
    }
}