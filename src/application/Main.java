package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        while(!chessMatch.isCheckMate()){

            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);
                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                if (capturedPiece != null) {
                    captured.add(capturedPiece);
                }

                if (chessMatch.getPromoted() != null) {
                    System.out.println("Enter piece for promotion (B/N/Q/R): ");
                    String s = sc.nextLine().toUpperCase();
                    while (!s.equals("B") && !s.equals("N") && !s.equals("R") && !s.equals("Q")) {
                        System.out.println("Invalid choice. Enter piece for promotion (B/N/Q/R): ");
                        s = sc.nextLine().toUpperCase();
                    }
                    chessMatch.replacePromotedPiece(s);
                }

            } catch(ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.print("Press any key to continue...");
                sc.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
}
