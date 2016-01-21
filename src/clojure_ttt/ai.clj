(ns clojure-ttt.ai
  (:require [clojure-ttt.board :refer :all]))

(declare score-board)
(declare best-move)

(defn minimax [player board maximum]
  (let [moves (empty-spaces board)
    scores (map #(score-board player (make-move board % player) maximum) moves)
    move-scores (partition 2 (interleave moves scores))
    sorted-move-scores (sort-by second move-scores)]
    (if maximum 
      (last sorted-move-scores)
      (first sorted-move-scores))))

(defn score-board [player board maximum]
  (cond (= (winner board) player) (if maximum 10 -10)
        (= (winner board) (other-player player)) (if maximum -10 10)
        (= (full-board? board) true) 0
        :else (second (minimax (other-player player) board (not maximum)))))

(defn best-move [player board]
  (first (minimax player board true)))

(defn next-boards [player board]
  (map #(make-move board % player) (empty-spaces board)))