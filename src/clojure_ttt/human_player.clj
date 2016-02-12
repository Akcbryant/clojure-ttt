(ns clojure-ttt.human-player
  (:require [clojure-ttt.ui :as ui]
            [clojure-ttt.board :as board]))

(defn move [board piece]
  (let [move (ui/get-move piece)]
    (if (board/valid-move? board move) (board/make-move board move piece)
      (move board piece))))
