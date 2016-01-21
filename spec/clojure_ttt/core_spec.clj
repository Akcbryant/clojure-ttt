(ns clojure-ttt.core-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.core :refer :all]
            [clojure-ttt.board :refer :all]))

(describe "Should return a board with the next move in it."
  (it "If a human chooses an open space 5 it will return a board with that space filled."
    (should= {5 X} (with-in-str "5" (human-move {} X))))
  
  (it "Uses the minimax function to grab a board with the next AI move made."
    (should= {9 O} (ai-move {} O))))

(run-specs)
