package Model;

import Aima.RadikalChessState;
import Model.Pieces.Bishop;
import Model.Pieces.King;
import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import Model.Pieces.Rook;
import java.util.ArrayList;

public class PieceMoveRange {

    private static PieceMoveRange instance;

    private PieceMoveRange() {
    }

    public static PieceMoveRange getInstance() {
        if (instance == null) {
            instance = new PieceMoveRange();
        }
        return instance;
    }

    public ArrayList<Movement> selectMove(ChessPiece chessPiece, RadikalChessState state) {
        ArrayList<Movement> moveList = new ArrayList<>();
        if (chessPiece instanceof Pawn) {
            moveRangePawn(chessPiece, state, moveList);
        }
        if (chessPiece instanceof Rook) {
            moveRangeRook(chessPiece, state, moveList);
        }
        if (chessPiece instanceof Bishop) {
            moveRangeBishop(chessPiece, state, moveList);
        }
        if (chessPiece instanceof Queen) {
            moveRangeQueen(chessPiece, state, moveList);
        }
        if (chessPiece instanceof King) {
            moveRangeKing(chessPiece, state, moveList);
        }
        return moveList;
    }

    private void moveRangePawn(ChessPiece chessPiece, RadikalChessState state,
            ArrayList<Movement> moveList) {
        if (state.getPlayer().getPlayerName().equals("Black") && chessPiece.getColour().equals("Black")) {
            if (chessPiece.getPosition().getRow() + 1 < state.getChessBoard().getRow()) {
                if (ProposeMove.getInstance().selectMove(chessPiece, 
                        new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() + 1,
                        chessPiece.getPosition().getColumn())),
                        state.getChessBoard())) {
                    moveList.add(new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() + 1, 
                            chessPiece.getPosition().getColumn())));
                }
            }
            if (chessPiece.getPosition().getRow() + 1 < state.getChessBoard().getRow()
                    && chessPiece.getPosition().getColumn() + 1 < state.getChessBoard().getColumn()) {
                if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                        new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() + 1,
                        chessPiece.getPosition().getColumn() + 1)),
                        state.getChessBoard())) {
                    moveList.add(new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() + 1, 
                            chessPiece.getPosition().getColumn() + 1)));
                }
            }
            if (chessPiece.getPosition().getRow() + 1 < state.getChessBoard().getRow()
                    && chessPiece.getPosition().getColumn() - 1 >= 0) {
                if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                        new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() + 1,
                        chessPiece.getPosition().getColumn() - 1)),
                        state.getChessBoard())) {
                    moveList.add(new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() + 1, 
                            chessPiece.getPosition().getColumn() - 1)));
                }
            }
        }
        if (state.getPlayer().getPlayerName().equals("White") && chessPiece.getColour().equals("White")) {
            if (chessPiece.getPosition().getRow() - 1 >= 0) {
                if (ProposeMove.getInstance().selectMove(chessPiece, new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() - 1,
                        chessPiece.getPosition().getColumn())),
                        state.getChessBoard())) {
                    moveList.add(new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() - 1, 
                            chessPiece.getPosition().getColumn())));
                }
            }
            if (chessPiece.getPosition().getRow() - 1 >= 0
                    && chessPiece.getPosition().getColumn() + 1 < state.getChessBoard().getColumn()) {
                if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                        new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() - 1,
                        chessPiece.getPosition().getColumn() + 1)),
                        state.getChessBoard())) {
                    moveList.add(new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() - 1, 
                            chessPiece.getPosition().getColumn() + 1)));
                }
            }
            if (chessPiece.getPosition().getRow() - 1 >= 0
                    && chessPiece.getPosition().getColumn() - 1 >= 0) {
                if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                        new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() - 1,
                        chessPiece.getPosition().getColumn() - 1)),
                        state.getChessBoard())) {
                    moveList.add(new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow()
                            - 1, chessPiece.getPosition().getColumn() - 1)));
                }
            }
        }
    }

    private void moveRangeRook(ChessPiece chessPiece, RadikalChessState state,
            ArrayList<Movement> moveList) {
        if (chessPiece.getColour().equals(state.getPlayer().getPlayerName())) {

            // Vertical descendente

            for (int i = 1; i < state.getChessBoard().getRow(); i++) {
                if (chessPiece.getPosition().getRow() + i < state.getChessBoard().getRow()) {
                    if (ProposeMove.getInstance().selectMove(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() + i,
                            chessPiece.getPosition().getColumn())), state.getChessBoard())) {
                        if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() + i,
                                chessPiece.getPosition().getColumn()))) {
                            moveList.add(new Movement(chessPiece.getPosition(),
                                    new Position(chessPiece.getPosition().getRow() + i,
                                    chessPiece.getPosition().getColumn())));
                        }
                    } else if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() + i,
                            chessPiece.getPosition().getColumn())), state.getChessBoard())) {
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() + i,
                                chessPiece.getPosition().getColumn())));
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            // Vertical ascendente

            for (int i = 1; i < state.getChessBoard().getRow(); i++) {
                if (chessPiece.getPosition().getRow() - i >= 0) {
                    if (ProposeMove.getInstance().selectMove(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() - i,
                            chessPiece.getPosition().getColumn())), state.getChessBoard())) {
                        if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() - i,
                                chessPiece.getPosition().getColumn()))) {
                            moveList.add(new Movement(chessPiece.getPosition(),
                                    new Position(chessPiece.getPosition().getRow() - i,
                                    chessPiece.getPosition().getColumn())));
                        }
                    } else if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() - i,
                            chessPiece.getPosition().getColumn())), state.getChessBoard())) {
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() - i,
                                chessPiece.getPosition().getColumn())));
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            // Horizontal a la derecha

            for (int i = 1; i < state.getChessBoard().getColumn(); i++) {
                if (chessPiece.getPosition().getColumn() + i < state.getChessBoard().getColumn()) {
                    if (ProposeMove.getInstance().selectMove(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow(),
                            chessPiece.getPosition().getColumn() + i)), state.getChessBoard())) {
                        if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow(),
                                chessPiece.getPosition().getColumn() + i))) {
                            moveList.add(new Movement(chessPiece.getPosition(),
                                    new Position(chessPiece.getPosition().getRow(),
                                    chessPiece.getPosition().getColumn() + i)));
                        }
                    } else if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow(),
                            chessPiece.getPosition().getColumn() + i)), state.getChessBoard())) {
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow(),
                                chessPiece.getPosition().getColumn() + i)));
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }


            // Horizontal a la izquierda

            for (int i = 1; i < state.getChessBoard().getColumn(); i++) {
                if (chessPiece.getPosition().getColumn() - i >= 0) {
                    if (ProposeMove.getInstance().selectMove(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow(),
                            chessPiece.getPosition().getColumn() - i)), state.getChessBoard())) {
                        if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow(),
                                chessPiece.getPosition().getColumn() - i))) {
                            moveList.add(new Movement(chessPiece.getPosition(),
                                    new Position(chessPiece.getPosition().getRow(),
                                    chessPiece.getPosition().getColumn() - i)));
                        }
                    } else if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow(),
                            chessPiece.getPosition().getColumn() - i)), state.getChessBoard())) {
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow(),
                                chessPiece.getPosition().getColumn() - i)));
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }

    private void moveRangeBishop(ChessPiece chessPiece, RadikalChessState state,
            ArrayList<Movement> moveList) {
        if (chessPiece.getColour().equals(state.getPlayer().getPlayerName())) {
            for (int i = 1; i < state.getChessBoard().getRow(); i++) {
                if (chessPiece.getPosition().getRow() + i < state.getChessBoard().getRow()
                        && chessPiece.getPosition().getColumn() + i < state.getChessBoard().getColumn()) {
                    if (ProposeMove.getInstance().selectMove(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() + i,
                            chessPiece.getPosition().getColumn()
                            + i)), state.getChessBoard())) {
                        if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() + i,
                                chessPiece.getPosition().getColumn() + i))) {
                            moveList.add(new Movement(chessPiece.getPosition(),
                                    new Position(chessPiece.getPosition().getRow() + i,
                                    chessPiece.getPosition().getColumn() + i)));
                        }
                    } else if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() + i,
                            chessPiece.getPosition().getColumn()
                            + i)), state.getChessBoard())) {
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() + i,
                                chessPiece.getPosition().getColumn() + i)));
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            for (int i = 1; i < state.getChessBoard().getRow(); i++) {
                if (chessPiece.getPosition().getRow() + i < state.getChessBoard().getRow()
                        && chessPiece.getPosition().getColumn() - i >= 0) {
                    if (ProposeMove.getInstance().selectMove(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() + i,
                            chessPiece.getPosition().getColumn() - i)), state.getChessBoard())) {
                        if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() + i,
                                chessPiece.getPosition().getColumn() - i))) {
                            moveList.add(new Movement(chessPiece.getPosition(),
                                    new Position(chessPiece.getPosition().getRow() + i,
                                    chessPiece.getPosition().getColumn() - i)));
                        }

                    } else if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() + i,
                            chessPiece.getPosition().getColumn() - i)), state.getChessBoard())) {
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() + i,
                                chessPiece.getPosition().getColumn() - i)));
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            for (int i = 1; i < state.getChessBoard().getRow(); i++) {
                if (chessPiece.getPosition().getRow() - i >= 0
                        && chessPiece.getPosition().getColumn() + i < state.getChessBoard().getColumn()) {
                    if (ProposeMove.getInstance().selectMove(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() - i,
                            chessPiece.getPosition().getColumn()
                            + i)), state.getChessBoard())) {
                        if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() - i,
                                chessPiece.getPosition().getColumn() + i))) {
                            moveList.add(new Movement(chessPiece.getPosition(),
                                    new Position(chessPiece.getPosition().getRow() - i,
                                    chessPiece.getPosition().getColumn() + i)));
                        }
                    } else if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() - i,
                            chessPiece.getPosition().getColumn() + i)), state.getChessBoard())) {
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() - i,
                                chessPiece.getPosition().getColumn() + i)));
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            for (int i = 1; i < state.getChessBoard().getRow(); i++) {
                if (chessPiece.getPosition().getRow() - i >= 0
                        && chessPiece.getPosition().getColumn() - i >= 0) {
                    if (ProposeMove.getInstance().selectMove(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() - i,
                            chessPiece.getPosition().getColumn() - i)), state.getChessBoard())) {
                        if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() - i,
                                chessPiece.getPosition().getColumn() - i))) {
                            moveList.add(new Movement(chessPiece.getPosition(),
                                    new Position(chessPiece.getPosition().getRow() - i,
                                    chessPiece.getPosition().getColumn() - i)));
                        }
                    } else if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() - i,
                            chessPiece.getPosition().getColumn() - i)), state.getChessBoard())) {
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() - i,
                                chessPiece.getPosition().getColumn() - i)));
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }

    private void moveRangeQueen(ChessPiece chessPiece, RadikalChessState state,
            ArrayList<Movement> moveList) {
        moveRangeRook(chessPiece, state, moveList);
        moveRangeBishop(chessPiece, state, moveList);
    }

    private void moveRangeKing(ChessPiece chessPiece, RadikalChessState state,
            ArrayList<Movement> moveList) {
        if (chessPiece.getColour().equals(state.getPlayer().getPlayerName())) {
            if (chessPiece.getPosition().getRow() + 1 < state.getChessBoard().getRow()) {
                if (ProposeMove.getInstance().selectMove(chessPiece, new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() + 1,
                        chessPiece.getPosition().getColumn())), state.getChessBoard())) {
                    if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() + 1,
                            chessPiece.getPosition().getColumn()))) {
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() + 1,
                                chessPiece.getPosition().getColumn())));
                    }
                } else if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                        new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() + 1,
                        chessPiece.getPosition().getColumn())),
                        state.getChessBoard())) {
                    moveList.add(new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() + 1,
                            chessPiece.getPosition().getColumn())));
                }
            }
            if (chessPiece.getPosition().getRow() + 1 < state.getChessBoard().getRow()
                    && chessPiece.getPosition().getColumn() - 1 >= 0) {
                if (ProposeMove.getInstance().selectMove(chessPiece, new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() + 1,
                        chessPiece.getPosition().getColumn() - 1)),
                        state.getChessBoard())) {
                    if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() + 1,
                            chessPiece.getPosition().getColumn() - 1))) {
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow()
                                + 1, chessPiece.getPosition().getColumn() - 1)));
                    }
                } else if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                        new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() + 1,
                        chessPiece.getPosition().getColumn() - 1)),
                        state.getChessBoard())) {
                    moveList.add(new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() + 1,
                            chessPiece.getPosition().getColumn() - 1)));
                }
            }
            if (chessPiece.getPosition().getColumn() - 1 >= 0) {
                if (ProposeMove.getInstance().selectMove(chessPiece, new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow(),
                        chessPiece.getPosition().getColumn() - 1)), state.getChessBoard())) {
                    if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow(),
                            chessPiece.getPosition().getColumn() - 1))) {
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow(),
                                chessPiece.getPosition().getColumn() - 1)));
                    }
                } else if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                        new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow(),
                        chessPiece.getPosition().getColumn() - 1)),
                        state.getChessBoard())) {
                    moveList.add(new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow(),
                            chessPiece.getPosition().getColumn() - 1)));
                }
            }
            if (chessPiece.getPosition().getRow() - 1 >= 0 && chessPiece.getPosition().getColumn() - 1 >= 0) {
                if (ProposeMove.getInstance().selectMove(chessPiece, new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() - 1,
                        chessPiece.getPosition().getColumn() - 1)),
                        state.getChessBoard())) {
                    if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() - 1,
                            chessPiece.getPosition().getColumn() - 1))) {
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() - 1,
                                chessPiece.getPosition().getColumn() - 1)));
                    }
                } else if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                        new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() - 1,
                        chessPiece.getPosition().getColumn() - 1)),
                        state.getChessBoard())) {
                    moveList.add(new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() - 1,
                            chessPiece.getPosition().getColumn() - 1)));
                }
            }
            if (chessPiece.getPosition().getRow() - 1 >= 0) {
                if (ProposeMove.getInstance().selectMove(chessPiece, new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() - 1,
                        chessPiece.getPosition().getColumn())),
                        state.getChessBoard())) {
                    if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() - 1,
                            chessPiece.getPosition().getColumn()))) {
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() - 1,
                                chessPiece.getPosition().getColumn())));
                    }
                } else if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                        new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() - 1,
                        chessPiece.getPosition().getColumn())),
                        state.getChessBoard())) {
                    moveList.add(new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() - 1,
                            chessPiece.getPosition().getColumn())));
                }
            }
            if (chessPiece.getPosition().getRow() - 1 >= 0
                    && chessPiece.getPosition().getColumn() + 1 < state.getChessBoard().getColumn()) {
                if (ProposeMove.getInstance().selectMove(chessPiece, new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() - 1, 
                        chessPiece.getPosition().getColumn() + 1)),
                        state.getChessBoard())) {
                    if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() - 1, 
                            chessPiece.getPosition().getColumn() + 1))) {
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() - 1, 
                                chessPiece.getPosition().getColumn() + 1)));
                    }
                }else if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                        new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() - 1,
                        chessPiece.getPosition().getColumn() + 1)),
                        state.getChessBoard())) {
                    moveList.add(new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() - 1,
                            chessPiece.getPosition().getColumn() + 1)));
                }
            }
            if (chessPiece.getPosition().getColumn() + 1 < state.getChessBoard().getColumn()) {
                if (ProposeMove.getInstance().selectMove(chessPiece, new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow(), 
                        chessPiece.getPosition().getColumn() + 1)),
                        state.getChessBoard())) {
                    if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow(), 
                            chessPiece.getPosition().getColumn() + 1))) {
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow(),
                                chessPiece.getPosition().getColumn() + 1)));
                    }
                }
                else if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                        new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow(), 
                        chessPiece.getPosition().getColumn() + 1)),
                        state.getChessBoard())){
                    moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow(), 
                            chessPiece.getPosition().getColumn() + 1)));    
                }
            }
            if (chessPiece.getPosition().getRow() + 1 < state.getChessBoard().getRow()
                    && chessPiece.getPosition().getColumn() + 1 < state.getChessBoard().getColumn()) {
                if (ProposeMove.getInstance().selectMove(chessPiece, new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() + 1, 
                        chessPiece.getPosition().getColumn() + 1)),
                        state.getChessBoard())) {
                    if (state.isEuclideanDistanceReduced(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow() + 1, 
                            chessPiece.getPosition().getColumn() + 1))) {
                        moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() + 1, 
                                chessPiece.getPosition().getColumn() + 1)));
                    }
                }
                else if (ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                        new Movement(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow() + 1, 
                        chessPiece.getPosition().getColumn() + 1)),
                        state.getChessBoard())){
                    moveList.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow() + 1, 
                            chessPiece.getPosition().getColumn() + 1)));    
                }
            }
        }
    }
}
