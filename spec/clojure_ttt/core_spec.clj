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
  	(should= nil (winner {}))
  	(should= nil (winner {1 player1 2 player2}))
  	(should= nil (winner {1 player1 2 player2 3 player1 4 player2 5 player2 6 player1 7 player1 8 player1 9 player2}))
  	(should= player1 (winner {1 player1 2 player2 3 player1 4 player2 5 player1 6 player2 7 player1 8 player2 9 player1}))
	(should= player2 (winner {1 player2 2 player2 3 player2}))
	(should= player2 (winner {1 player2 2 player2 3 player1 4 player1 5 player2 6 player2 7 player2 8 player2 9 player1}))
	;This test shows that if both players have a winning position then this returns the first one to be found in the winner list.
	(should= player1 (winner {1 player1 2 player1 3 player1 4 player2 5 player2 6 player2 7 player2 8 player2 9 player1}))))

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
