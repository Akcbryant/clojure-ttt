(ns clojure-ttt.core
  (:require [clojure-ttt.ai :as ai]
            [clojure-ttt.ui :as ui]
            [clojure-ttt.board :as board]))

;; a move takes a board and a piece and returns the next move someone wants to make
; put in hard_ai
(defn ai-move [board piece]
  (ui/computer-processing)
  (board/make-move board (ai/best-move piece board) piece))

; put in human namespace
(defn human-move [board piece]
  (let [move (ui/get-move piece)]
    (if (board/valid-move? board move) (board/make-move board move piece)
      (human-move board piece))))

(defn play-game [player1-move player2-move board whose-turn]
  (ui/render-board board)
  (if-let [winner (board/winner board)]
    (ui/winner-message winner)
    (if (board/game-ended? board)
      (ui/tie-message)
      (if (= whose-turn board/X)
        (play-game player1-move player2-move (player1-move board whose-turn) (board/other-player whose-turn))
        (play-game player1-move player2-move (player2-move board whose-turn) (board/other-player whose-turn))))))

(defn choose-piece []
  (let [human-piece (ui/get-piece)]
    (if (= human-piece board/X)
      (play-game human-move ai-move {} board/X)
      (play-game ai-move human-move {} board/X))))

(defn setup-board-state []
  (let [humans (ui/how-many-humans)]
    (cond (= humans 1) (choose-piece)
          (= humans 2) (play-game human-move human-move {} board/X)
          (= humans 0) (play-game ai-move ai-move {} board/X))))

(defn -main []
  (ui/welcome-message)
  (setup-board-state))
