(ns clojure-ttt.core-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.core :refer :all]))

(describe "Board Logic"
  (it "X refers to 'X' and O refers to 'O'. Empty-board is empty."
    (should= {} empty-board)
    (should= {0 "X" 1 "O" 2 "X" 3 "O"} {0 X, 1 O, 2 X, 3 O}))

  (it "Makes a new board when given a board, move position, and player."
  	(should= {9 X} (make-move empty-board 9 X))
  	(should= {1 X 2 O 9 O} (make-move {1 X 2 O} 9 O)))

  (it "Check if there is a winner otherwise return nil."
  	(should= nil (winner empty-board))
  	(should= nil (winner {1 X 2 O}))
  	(should= nil (winner {1 X 2 O 3 X 4 O 5 O 6 X 7 X 8 X 9 O}))
  	(should= X (winner {1 X 2 X 3 X}))
  	(should= X (winner {1 X 2 O 3 X 4 O 5 X 6 O 7 X 8 O 9 X}))
	(should= O (winner {1 O 2 O 3 O}))
	(should= O (winner {1 O 2 O 3 X 4 X 5 O 6 O 7 O 8 O 9 X}))
	;This test shows that if both players have a winning position then this returns the first one to be found in the winner list.
	(should= X (winner {1 X 2 X 3 X 4 O 5 O 6 O 7 O 8 O 9 X})))

  (it "Get player who is making the move based on introspecting the board."
  	(should= X (whose-turn empty-board))
  	(should= O (whose-turn {1 X}))
  	(should= X (whose-turn {1 X 2 O}))
  	(should= O (whose-turn {1 X 2 O 3 X}))
  	(should= nil (whose-turn {1 X 2 O 3 X 4 O 5 X 6 O 7 X 8 O 9 X})))

  (it "Returns true or false depending on whether the game has reached a possible end state."
  	(should= false (game-ended? {}))
  	(should= false (game-ended? {1 X 2 O 3 X}))
  	(should= true  (game-ended? {1 X 2 X 3 X}))
  	(should= true  (game-ended? {1 X 2 X 3 X 4 O 5 O 6 O 7 O 8 O 9 X}))
  	(should= true  (game-ended? {1 O 2 O 3 O}))))

(describe "Minimax and AI Functions"
	(it "This should return the opposite player of the one passed to the function."
		(should= O (other-player X))
		(should= X (other-player O))
		(should= nil (other-player "G")))

	(it "Should return all of the empty spaces on the board."
		(should= '(1 2 3 4 5 6 7 8 9) (empty-spaces empty-board))
		(should= '(1 3 5 7 9) (empty-spaces {2 X 4 O 6 X 8 O}))
		(should= '() (empty-spaces {1 X 2 X 3 X 4 O 5 O 6 O 7 O 8 O 9 X})))

	(it "Return 10 if the passed in player won, -10 if they lost, and 0 if it's a tie."
		(should= 10 (score-board X {1 X 2 X 3 X} true))
		(should= -10 (score-board O {1 X 2 X 3 X} true))
		(should= 10 (score-board O {1 O 2 O 3 O} true))
		(should= -10 (score-board X {1 O 2 O 3 O} true))
		(should= 0 (score-board X {1 X 2 O 3 X 4 O 5 O 6 X 7 X 8 X 9 O} true))
		(should= 0 (score-board O {1 X 2 O 3 X 4 O 5 O 6 X 7 X 8 X 9 O} true)))

	(it "Should return true if the game is full and false if there are places yet to go"
		(should= false (full-board? {}))
		(should= false (full-board? {1 X 2 O 3 X}))
		(should= true (full-board? {1 X 2 X 3 X 4 O 5 O 6 O 7 O 8 O 9 X})))


	(it "Should return a list of the possible next boards given the player that is moving next."
		(should= '({1 "X"} {2 "X"} {3 "X"} {4 "X"} {5 "X"} {6 "X"} {7 "X"} {8 "X"} {9 "X"})
				  (next-boards X {}))
		(should= '({1 "O", 2 "X"} {1 "O", 3 "X"} {1 "O", 4 "X"} {1 "O", 5 "X"} {1 "O", 6 "X"} {1 "O", 7 "X"} {1 "O", 8 "X"} {1 "O", 9 "X"})
				  (next-boards X {1 O}))
		(should= '({1 "O", 2 "O", 3 "O", 4 "O"} {1 "O", 2 "O", 3 "O", 5 "O"} {1 "O", 2 "O", 3 "O", 6 "O"} {1 "O", 2 "O", 3 "O", 7 "O"} {1 "O", 2 "O", 3 "O", 8 "O"} {1 "O", 2 "O", 3 "O", 9 "O"})
				  (next-boards O {1 O 2 O 3 O})))

	(it "best-move should return the next optimal move."
		(should= 7 (best-move O {1 O 2 X 3 X 4 X 5 X 6 O 8 O}))
		(should= 5 (best-move X {1 O 2 X 3 O 4 X}))
		(should= 9 (best-move X {}))))
 

; Interactivity and UI tests probably to be moved put it down here.

 (describe "Rendering Board"
 	;___|___|___
 	;___|___|___
 	;   |   | 
 	(it "Empty board should look empty"
 		(should= "___|___|___\n___|___|___\n   |   |   \n" (with-out-str (render-board {}))))

 	(it "Should fill in the appropriate spaces automatically."
 		(should= "_X_|_O_|_X_\n_O_|_X_|_O_\n   |   |   \n" 
 			(with-out-str (render-board {1 X 2 O 3 X 4 O 5 X 6 O})))))


(run-specs)
