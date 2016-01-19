(ns clojure-ttt.board)

(def X "X")
(def O "O")
(def empty-board {})
(def winning-vectors [[1,2,3] [4,5,6] [7,8,9] [1,5,9] [1,4,7] [2,5,8] [3,6,9] [3,5,7]])

(declare full-board?)

(defn make-move [board position player]
	(merge board {position player}))

(defn winner-check [board]
	(for [vector winning-vectors]
		(if (and (= (board (first vector)) (board (second vector)) (board (last vector))) (not= (board (first vector)) nil))
			(board (first vector))
			nil)))

(defn winner [board]
	(some #{X O} (winner-check board)))

(defn whose-turn [board]
	(if (and (>= (count board) 0) (< (count board) 9))
		(cond (even? (count board)) X
			  (odd? (count board)) O)
		nil))

(defn game-ended? [board]
	(if (or (full-board? board) (winner board)) 
		true
		false))

(defn other-player [player]
	(cond (= player X) O
		  (= player O) X
		  :else nil))

(defn empty-spaces [board]
	(remove (fn [k] (contains? board k)) (range 1 10)))

(defn full-board? [board]
	(if (empty? (empty-spaces board)) 
		true
		false))