(ns clojure-ttt.core
  (:require [clojure-ttt.ai :refer :all]
            [clojure-ttt.ui :refer :all]
            [clojure-ttt.board :refer :all]))

(declare choose-piece)
(declare play-game)
(declare human-move)
(declare ai-move)
        
(defn setup-board-state []
  (let [humans (how-many-humans)]
    (cond (= humans 1) (choose-piece)
          (= humans 2) (play-game human-move human-move {} X)
          (= humans 0) (play-game ai-move ai-move {} X)))) 

;; a move takes a board and a piece and returns the next move someone wants to make
(defn ai-move [board piece]
  (computer-processing)
  (make-move board (best-move piece board) piece))

(defn human-move [board piece]
  (let [move (get-move piece)]
    (if (valid-move? board move) (make-move board move piece)
      (human-move board piece))))

(defn play-game [player1-move player2-move board whose-turn]
  (render-board board)
  (if-let [winner (winner board)]
    (winner-message winner)
    (if (game-ended? board) 
      (tie-message)
      (if (= whose-turn X)
        (play-game player1-move player2-move (player1-move board whose-turn) (other-player whose-turn))
        (play-game player1-move player2-move (player2-move board whose-turn) (other-player whose-turn))))))

(defn choose-piece []
  (let [human-piece (get-piece)]
    (if (= human-piece X)
      (play-game human-move ai-move {} X)
      (play-game ai-move human-move {} X))))

(defn -main []
  (welcome-message)
  (setup-board-state))
