(ns clojure-ttt.hard-ai
  (:require [clojure-ttt.board :as board]))

(declare score-board)
(declare best-move)

(defn minimax [player board depth maximum]
  (let [moves (board/empty-spaces board)
    scores (map #(score-board player (board/make-move board % player) depth maximum) moves)
    move-scores (partition 2 (interleave moves scores))
    sorted-move-scores (sort-by second move-scores)]
    (if maximum 
      (last sorted-move-scores)
      (first sorted-move-scores))))

(defn score-board [player board depth maximum]
  (cond (= (board/winner board) player) (if maximum (- 10 depth) (+ -10 depth))
        (= (board/winner board) (board/other-player player)) (if maximum (+ -10 depth) (- 10 depth))
        (= (board/full-board? board) true) 0
        :else (second (minimax (board/other-player player) board (dec depth) (not maximum)))))

(defn best-move [board player depth]
  (first (minimax player board depth true)))

(defn move [board player]
  (board/make-move board (best-move board player 9) player))