(ns clojure-ttt.hard-ai
  (:require [clojure-ttt.board :as board]))

(declare score-board)
(declare best-move)

(defn minimax [player board maximum]
  (let [moves (board/empty-spaces board)
    scores (map #(score-board player (board/make-move board % player) maximum) moves)
    move-scores (partition 2 (interleave moves scores))
    sorted-move-scores (sort-by second move-scores)]
    (if maximum 
      (last sorted-move-scores)
      (first sorted-move-scores))))

(defn score-board [player board maximum]
  (cond (= (board/winner board) player) (if maximum 10 -10)
        (= (board/winner board) (board/other-player player)) (if maximum -10 10)
        (= (board/full-board? board) true) 0
        :else (second (minimax (board/other-player player) board (not maximum)))))

(defn best-move [player board]
  (first (minimax player board true)))

(defn move [board piece]
  (board/make-move board (best-move piece board) piece))