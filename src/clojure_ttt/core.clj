(ns clojure-ttt.core)

(def player1 "X")
(def player2 "O")
(def empty-board {})
(def winning-vectors [[1,2,3] [4,5,6] [7,8,9] [1,5,9] [1,4,7] [2,5,8] [3,6,9] [3,5,7]])

(defn make-move [board position player]
	(merge board {position player}))

(defn winner-check [board]
	(for [vector winning-vectors]
		(if (and (= (board (first vector)) (board (second vector)) (board (last vector))) 
			(not= (board (first vector)) nil))
			(board (first vector))
			nil)))

(defn winner [board]
	(some #{player1 player2} (winner-check board)))

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
		(println "please get better")
		))

(defn prompt-for-move [board]
	(render-board board)
	(println "Where would you like to move: Pick 1-9?")
	(flush)
	(process-move ((read-line) board)))
	
(defn get-computer-move [board]
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
