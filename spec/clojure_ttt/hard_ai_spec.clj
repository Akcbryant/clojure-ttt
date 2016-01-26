(ns clojure-ttt.hard-ai-spec
  (:require [speclj.core :refer :all]
        [clojure-ttt.hard-ai :refer :all]
        [clojure-ttt.board :refer :all]))

(describe "Minimax and AI Functions"

  (it "Return 10 if the passed in player won, -10 if they lost, and 0 if it's a tie."
    (should= 10 (score-board X {1 X 2 X 3 X} true))
    (should= -10 (score-board O {1 X 2 X 3 X} true))
    (should= 10 (score-board O {1 O 2 O 3 O} true))
    (should= -10 (score-board X {1 O 2 O 3 O} true))
    (should= -10 (score-board X {1 X 2 X 3 X} false))
    (should= 10 (score-board O {1 X 2 X 3 X} false))
    (should= -10 (score-board O {1 O 2 O 3 O} false))
    (should= 10 (score-board X {1 O 2 O 3 O} false))
    (should= 0 (score-board X {1 X 2 O 3 X 4 O 5 O 6 X 7 X 8 X 9 O} true))
    (should= 0 (score-board O {1 X 2 O 3 X 4 O 5 O 6 X 7 X 8 X 9 O} true)))

  (it "best-move should return the next optimal move."
    (should= 7 (best-move O {1 O 2 X 3 X 4 X 5 X 6 O 8 O}))
    (should= 7 (best-move O {1 X 2 O 4 X 5 O 9 X}))
    (should= 5 (best-move X {1 O 2 X 3 O 4 X}))
    (should= 9 (best-move X {}))))

(describe "When asked to make a move"
  (it "returns a board with the next move made"
    (should= {1 O 2 O 3 X 6 X 9 X} (move {1 O 2 O 6 X 9 X} X))))