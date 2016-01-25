(ns clojure-ttt.core
  (:require [clojure-ttt.hard-ai :as ai]
            [clojure-ttt.ui :as ui]
            [clojure-ttt.board :as board]
            [clojure-ttt.human-player :as human]))

(defn play-game [player1 player2 board whose-turn]
  (ui/render-board board)
  (if-let [winner (board/winner board)]
    (ui/winner-message winner)
    (if (board/game-ended? board)
      (ui/tie-message)
      (if (= whose-turn board/X)
        (play-game player1 player2 (player1 board whose-turn) (board/other-player whose-turn))
        (play-game player1 player2 (player2 board whose-turn) (board/other-player whose-turn))))))

(defn choose-piece []
  (let [human-piece (ui/get-piece)]
    (if (= human-piece board/X)
      (play-game human/move ai/move {} board/X)
      (play-game ai/move human/move {} board/X))))

(defn setup-board-state []
  (let [humans (ui/how-many-humans)]
    (cond (= humans 1) (choose-piece)
          (= humans 2) (play-game human/move human/move {} board/X)
          (= humans 0) (play-game ai/move ai/move {} board/X))))

(defn -main []
  (ui/welcome-message)
  (setup-board-state))
