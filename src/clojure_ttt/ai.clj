(ns clojure-ttt.ai
	(:require [clojure-ttt.board :refer :all]))

(declare score-board)
(declare best-move)

(defn minimax [player cells maximum]
	(let [moves (empty-spaces cells)
		scores (map #(score-board player (make-move cells % player) maximum) moves)
		move-scores (partition 2 (interleave moves scores))
		sorted-move-scores (sort-by second move-scores)]
		(if maximum 
			(last sorted-move-scores)
			(first sorted-move-scores))))

(defn score-board [player cells maximum]
	(cond (= (winner cells) player) (if maximum 10 -10)
		  (= (winner cells) (other-player player)) (if maximum -10 10)
		  (= (full-board? cells) true) 0
		  :else (second (minimax (other-player player) cells (not maximum)))))

(defn best-move [player cells]
	(first (minimax player cells true)))

(defn next-boards [player cells]
	(map #(make-move cells % player) (empty-spaces cells)))