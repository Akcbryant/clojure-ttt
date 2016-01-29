(ns clojure-ttt.hard-ai-spec
  (:require [speclj.core :refer :all]
        [clojure-ttt.hard-ai :refer :all]
        [clojure-ttt.board :refer :all]))

(describe "Minimax and AI Functions"

  (it "Return 10 - depth or -10 + depth depending on perspective"
    (should= 10 (score-board X {1 X 2 X 3 X} 0 true))
    (should= -10 (score-board O {1 X 2 X 3 X} 0 true))
    (should= 6 (score-board O {1 O 2 O 3 O} 4 true))
    (should= -5 (score-board X {1 O 2 O 3 O} 5 true))
    (should= -5 (score-board X {1 X 2 X 3 X} 5 false))
    (should= 1 (score-board O {1 X 2 X 3 X} 9 false))
    (should= -1 (score-board O {1 O 2 O 3 O} 9 false))
    (should= 9 (score-board X {1 O 2 O 3 O} 1 false))
    (should= 0 (score-board X {1 X 2 O 3 X
                               4 O 5 O 6 X 
                               7 X 8 X 9 O} 6 true))
    (should= 0 (score-board O {1 X 2 O 3 X
                               4 O 5 O 6 X 
                               7 X 8 X 9 O} 9 true)))

  (it "best-move should return the next optimal move."
    (should= 7 (best-move {1 O 2 X 3 X 4 X 5 X 6 O 8 O} O 2))
    (should= 7 (best-move {1 X 2 O 4 X 5 O 9 X} O 4))
    (should= 5 (best-move {1 O 2 X 3 O 4 X} X 5))
    (should= 9 (best-move {} X 9))))

(describe "When asked to make a move"
  (it "returns a board with the next move made"
    (should= {1 O 2 O 3 X 6 X 9 X} (move {1 O 2 O 6 X 9 X} X))))