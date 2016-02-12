(ns clojure-ttt.board
  (:require [clojure.core.matrix :as matrix]))

(def X 1)
(def O -1)

(declare full-board?)

(defn make-move [board position player]
  (assoc board position player))

(defn valid-move? [board position]
  (zero? (get board position)))

(defn board-width [board]
  (int (Math/sqrt (count board))))

(defn board-matrix [board]
  (partition (board-width board) board))

(defn rows-columns [board-matrix]
  (concat board-matrix (matrix/columns board-matrix)))

(defn diagonals [board-matrix]
  (vector (matrix/diagonal board-matrix) (matrix/diagonal (matrix/transpose board-matrix))))

(defn winner [board]
  (let [board-matrix (board-matrix board)
        board-width (board-width board)
        all-vectors (concat (rows-columns board-matrix) (diagonals board-matrix))
        all-values (map #(reduce + %) all-vectors)]
    (cond (some #{board-width} all-values) X
          (some #{(- board-width)} all-values) O
          :else nil)))

(defn game-ended? [board]
  (if (or (full-board? board) (winner board))
    true
    false))

(defn other-player [player]
  (cond (= player X) O
    (= player O) X
    :else nil))

(defn empty-spaces [board]
  (map first (filter #(= (second %) 0) (map-indexed vector board))))

(defn full-board? [board]
  (if (empty? (empty-spaces board)) 
    true
    false))

(defn next-boards [player board]
  (map #(make-move board % player) (empty-spaces board)))
