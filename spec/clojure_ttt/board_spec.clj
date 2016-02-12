(ns clojure-ttt.board-spec
	(:require [speclj.core :refer :all]
			  [clojure-ttt.board :refer :all]))

(tags :board)

(def empty-board [0 0 0 0 0 0 0 0 0])

(describe "Board data structures"
  (it "1 refers to 'X' and -1 refers to 'O' and 0 is empty"
    (should= [1 -1 1 -1 0 0 0 0 0 ] [X O X O 0 0 0 0 0])))
  
(describe "make-move"
  (it "returns a board with the specified move made on the board provided"
    (should= [0 0 0 0 0 0 0 0 X] (make-move empty-board 8 X))))

(describe "valid-move?"
  (it "Should return true if a player moves into the open spot."
    (should= true (valid-move? empty-board 1)))
  (it "Should return false if someone moves into a taken 5th spot."
    (should= false (valid-move? [X O X X O X 0 0 0] 5))))

(describe "board-width"
  (it "convenience method to get the square root of a board to give the width"
    (should= 3 (board-width (repeat 9 0)))
    (should= 4 (board-width (repeat 16 0)))))

(describe "board-matrix"
  (it "make a matrix from a 3 X 3 board"
    (should= [[X 0 X] [0 0 0] [0 0 0]] (board-matrix [X 0 X 0 0 0 0 0 0])))
  (it "make a matrix from a 4 X 4 board"
    (should= [[X 0 X 0] [X 0 0 0] [0 X X 0] [X 0 X 0]] (board-matrix [X 0 X 0 X 0 0 0 0 X X 0 X 0 X 0]))))

(describe "rows-columns"
  (it "returns a vector of the rows and columns for a 3 X 3 board"
    (should= [[1 0 0][0 1 0][0 0 1][1 0 0][0 1 0][0 0 1]] (rows-columns [[1 0 0][0 1 0][0 0 1]])))
  (it "returns a vector of the rows and columns for a 4 X 4 board"
    (should= [[1 0 0 0] [-1 0 0 0] [0 1 1 1] [0 1 1 0] [1 -1 0 0] [0 0 1 1][ 0 0 1 1][0 0 1 0]]
             (rows-columns [[1 0 0 0] [-1 0 0 0] [0 1 1 1] [0 1 1 0]]))))

(describe "diagonals"
  (it "returns the diagonals of a 3 X 3 board"
    (should= [[1 0 1] [1 0 1]] (diagonals [[1 0 1] [0 0 0] [1 0 1]])))
  (it "returns the diagonals of a 4 X 4 board"
    (should= [[1 0 0 1][1 0 0 1]](diagonals [[1 0 0 1][0 0 0 0][0 0 0 0][1 0 0 1]]))))

(describe "winner"
  (it "returns X if X has won on a 3 X 3 board"
    (should= X (winner [X X X 0 0 0 0 0 0])))
  (it "returns O if O has won on a 3 X 3 board"
    (should= O (winner [O X O O 0 0 O X 0])))
  (it "returns 0 if there is no winner or a tie"
    (should= nil (winner [0 0 0 0 0 0 0 0 0]))
    (should= nil (winner [X O X O X O O X O]))))

(describe "game-ended?"
  (it "returns false if the board is empty"
    (should= false (game-ended? [0 0 0 0 0 0 0 0 0])))
  (it "returns false if the game still has possible moves"
    (should= false (game-ended? [X O X 0 0 0 0 0 0])))
  (it "returns true if X has won"
    (should= true (game-ended? [X X X 0 0 0 0 0 0])))
  (it "returns true if O has won"
    (should= true (game-ended? [O O O 0 0 0 0 0 0])))
  (it "returns true if the board is full"
    (should= true (game-ended? [O X O X O X X O X]))))

(describe "full-board?"
  (it "returns false if the board is empty"
    (should= false (full-board? [0 0 0 0 0 0 0 0 0])))
  (it "returns false if the board has 4 moves in it"
    (should= false (full-board? [0 0 0 X 0 0 0 O 0 0 0 X 0 0 0 O])))
  (it "returns true if the board is full"
    (should= true (full-board? [X O X X O X O X O X])))
  (it "extra test case"
    (should= false (full-board? [O X X X X X O 0 O 0]))))

(describe "empty-spaces"
  (it "returns an empty seq on a full board"
    (should= [] (empty-spaces [X O X O X O O X O])))
  (it "returns a vector of the indexes of the empty spaces in a board"
    (should= [1 2 3] (empty-spaces [X 0 0 0 X O X O X])))
  (it "when given an empty board it returns all empty spaces"
    (should= [0 1 2 3 4 5 6 7 8] (empty-spaces [0 0 0 0 0 0 0 0 0]))))

(describe "other-player"
  (it "given X the other player is O"
    (should= O (other-player X)))
  (it "given O the other player is X"
    (should= X (other-player O)))
  (it "given a non X or O character return nil"
    (should= nil (other-player "G"))))

(describe "next-boards"
  (it "returns no boards when the board is full"
    (should= [] (next-boards X [X O X X O X O X O])))
  (it "returns 3 boards with the player having moved into each empty spot"
    (should= [[X O X O X O X 0 0] [X O X O X O 0 X 0] [X O X O X O 0 0 X]]
              (next-boards X [X O X O X O 0 0 0]))))

(run-specs)

