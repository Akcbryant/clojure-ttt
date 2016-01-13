(ns clojure-ttt.core-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.core :refer :all]))

(describe "Board Logic"
  (it "player1 refers to 'X' and player2 refers to 'O'. Empty-board is empty."
    (should= {} empty-board)
    (should= {0 "X" 1 "O" 2 "X" 3 "O"} {0 player1, 1 player2, 2 player1, 3 player2}))

  (it "Makes a new board when given a board, move position, and player."
  	(should= {9 player1} (make-move empty-board 9 player1))
  	(should= {1 player1 2 player2 9 player2} (make-move {1 player1 2 player2} 9 player2)))

  (it "Check if there is a winner otherwise return nil."
  	(should= nil (winner empty-board))
  	(should= nil (winner {1 player1 2 player2}))
  	(should= nil (winner {1 player1 2 player2 3 player1 4 player2 5 player2 6 player1 7 player1 8 player1 9 player2}))
  	(should= player1 (winner {1 player1 2 player1 3 player1}))
  	(should= player1 (winner {1 player1 2 player2 3 player1 4 player2 5 player1 6 player2 7 player1 8 player2 9 player1}))
	(should= player2 (winner {1 player2 2 player2 3 player2}))
	(should= player2 (winner {1 player2 2 player2 3 player1 4 player1 5 player2 6 player2 7 player2 8 player2 9 player1}))
	;This test shows that if both players have a winning position then this returns the first one to be found in the winner list.
	(should= player1 (winner {1 player1 2 player1 3 player1 4 player2 5 player2 6 player2 7 player2 8 player2 9 player1})))

  (it "Get player who is making the move based on introspecting the board."
  	(should= player1 (whose-turn empty-board))
  	(should= player2 (whose-turn {1 player1}))
  	(should= player1 (whose-turn {1 player1 2 player2}))
  	(should= player2 (whose-turn {1 player1 2 player2 3 player1}))
  	(should= nil (whose-turn {1 player1 2 player2 3 player1 4 player2 5 player1 6 player2 7 player1 8 player2 9 player1}))))

(describe "Minimax and AI Functions"
	(it "Should return the next move for the computer using the Minimax algorithm below."
		)

	(it "The minimax function recursively calculates the values of a move and returns the highest scoring one."
		)

	(it "This should return the opposite player of the one passed to the function."
		(should= player2 (other-player player1))
		(should= player1 (other-player player2))
		(should= nil (other-player "G")))

	(it "Should return all of the empty spaces on the board."
		(should= '(1 2 3 4 5 6 7 8 9) (empty-spaces empty-board))
		(should= '(1 3 5 7 9) (empty-spaces {2 player1 4 player2 6 player1 8 player2}))
		(should= '() (empty-spaces {1 player1 2 player1 3 player1 4 player2 5 player2 6 player2 7 player2 8 player2 9 player1})))

	(it "Return 10 if the passed in player won, -10 if they lost, and 0 if it's a tie."
		(should= 10 (score-board player1 {1 player1 2 player1 3 player1}))
		(should= -10 (score-board player2 {1 player1 2 player1 3 player1}))
		(should= 10 (score-board player2 {1 player2 2 player2 3 player2}))
		(should= -10 (score-board player1 {1 player2 2 player2 3 player2}))
		(should= 0 (score-board player1 {1 player1 2 player2 3 player1 4 player2 5 player2 6 player1 7 player1 8 player1 9 player2}))
		(should= 0 (score-board player2 {1 player1 2 player2 3 player1 4 player2 5 player2 6 player1 7 player1 8 player1 9 player2}))
		(should= nil (score-board player1 {}))
		(should= nil (score-board player2 {}))
		(should= nil (score-board player1 {1 player1 2 player2}))
		(should= nil (score-board player2 {1 player2 2 player1})))

	(it "Should return true if the game is full and false if there are places yet to go"
		(should= false (full-board? {}))
		(should= false (full-board? {1 player1 2 player2 3 player1}))
		(should= true (full-board? {1 player1 2 player1 3 player1 4 player2 5 player2 6 player2 7 player2 8 player2 9 player1}))))
 

; Interactivity and UI tests probably to be moved put it down here.

 (describe "Rendering Board"
 	;___|___|___
 	;___|___|___
 	;   |   | 
 	(it "Empty board should look empty"
 		(should= "___|___|___\n___|___|___\n   |   |   \n" (with-out-str (render-board {}))))

 	(it "Should fill in the appropriate spaces automatically."
 		(should= "_X_|_O_|_X_\n_O_|_X_|_O_\n   |   |   \n" 
 			(with-out-str (render-board {1 player1 2 player2 3 player1 4 player2 5 player1 6 player2})))))


(run-specs)
