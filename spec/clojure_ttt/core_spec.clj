(ns clojure-ttt.core-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.core :as core]
            [clojure-ttt.board :as board]
            [clojure-ttt.ui :as ui]))

; (describe "Should return a board with the next move in it"
;   (it "If a human chooses an open space 5 it will return a board with that space filled."
;     (should= {5 board/X} (with-in-str "5" (core/human-move {} board/X))))

;   (it "Uses the minimax function to grab a board with the next AI move made."
;     (should= {9 board/O} (core/ai-move {} board/O))))

(describe "Play game"
  (it "renders the board with given board when called at first"
    (let [render-board-called (atom 0)]
      (with-redefs [ui/render-board (fn [_] (reset! render-board-called 1))]
        (core/play-game core/ai-move core/ai-move {} board/X)
        (should= 1 @render-board-called)))))

(run-specs)
