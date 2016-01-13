(ns clojure-ttt.core)

(def player1 "X")
(def player2 "O")
(def empty-board {})
(def winning-vectors [[1,2,3] [4,5,6] [7,8,9] [1,5,9] [1,4,7] [2,5,8] [3,6,9] [3,5,7]])

(defn make-move [board position player]
	(merge board {position player}))

(defn winner-check [board]
	(for [vector winning-vectors]
		(if (and (= (board (first vector)) (board (second vector)) (board (last vector))) (not= (board (first vector)) nil))
			(board (first vector))
			nil)))

(defn winner [board]
	(some #{player1 player2} (winner-check board)))

(defn whose-turn [board]
	(if (and (>= (count board) 0) (< (count board) 9))
		(cond (even? (count board)) player1
			  (odd? (count board)) player2)
		nil))

;AI and Minimax
(defn other-player [player]
	(cond (= player player1) player2
		  (= player player2) player1
		  :else nil))

(defn empty-spaces [board]
	(remove (fn [k] (contains? board k)) (range 1 10)))

(defn full-board? [board]
	(if (empty? (empty-spaces board)) 
		true
		false))

(defn score-board [player board]
	(cond (= (winner board) player) 10
		  (= (winner board) (other-player player)) -10
		  (= (full-board? board) true) 0
		  :else nil))

(defn minimax [player board]
	;should return an int that is the move to be made
	;makes a move and scores it accordingly, if not finished minimax that board as the opposite player .
	;Enumerate through the empty-spaces with make-move.
	;For each make-move board score the board with either 10 -10 or 0 based on if player wins

	; (for [move (empty-spaces board)]
	; 	(cond (= score-board (make-move board move player) 10) move
	; 		  (= score-board (make-move board move)))))
)

(defn get-computer-move [board]
	;If it's the end of the game whose-turn will return nil check for this to end game?
	(minimax (whose-turn board) board))

;Terminal stuff - move to other file later.

;Forward Declarations
(declare process-initial-input)
(declare prompt-for-move)

(defn render-board [board]
	(println (str "_" (get board 1 "_") "_|_" (get board 2 "_") "_|_" (get board 3 "_") "_"))
	(println (str "_" (get board 4 "_") "_|_" (get board 5 "_") "_|_" (get board 6 "_") "_"))
	(println (str " " (get board 7 " ") " | " (get board 8 " ") " | " (get board 9 " ") " ")))

(defn process-move [input board]
	(cond (contains? board input)
		((println "That space is taken!!!")
			(prompt-for-move board))
		(or (> input 9) (< input 1)) 
		(prompt-for-move board)
		:else 
		(println "please get better")))

(defn prompt-for-move [board]
	(render-board board)
	(println "Where would you like to move: Pick 1-9?")
	(flush)
	(process-move ((read-line) board)))
	
(defn get-computer-move [player board]
	(println "get-computer-move"))

(defn prompt-for-initial-input []
	(println "Welcome to Jurassic Par... Tic Tac Toe!!!")
	(println "Would you like to be X's or O's???")
	(flush)
	(process-initial-input (read-line)))

(defn process-initial-input [input]
	(cond (or (= input "X") (= input "x"))
		(prompt-for-move empty-board)
		  (or (= input "O") (= input "o"))
		(get-computer-move empty-board)
		:else
		((println "This is not the time for GAMES!!!")
		(prompt-for-initial-input))))

(defn -main []
	(prompt-for-initial-input))
