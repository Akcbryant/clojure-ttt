(ns clojure-ttt.hard-ai-spec
  (:require [speclj.core :refer :all]
        [clojure-ttt.hard-ai :refer :all]
        [clojure-ttt.board :refer :all]))

(describe "score-board"
  (it "return 10 when it is X's turn and X has won with 0 depth"
    (should= 10 (score-board X [X X X] 0 true)))
  (it "return -10 when it is O's turn and X wins (maximizing for O)"
    (should= -10 (score-board O [X X X] 0 true)))
  (it "return 6 when there is a depth of 6 for a maximizing player"
    (should= 6 (score-board O [O O O] 4 true)))
  (it "return -5 when the maximizing person loses at depth of 5"
    (should= -5 (score-board X [O O O] 5 true)))
  (it "return 1 when the minimizing person loses at depth of 9"
    (should= 1 (score-board O [X X X] 9 false)))
  (it "return 0 when there is a tie for the maximizing player"
    (should= 0 (score-board X [X O X O O X X X O] 6 true)))
  (it "return 0 when there is a tie for the minimizing player"
    (should= 0 (score-board O [X O X O O X X X O] 6 true))))

(describe "best-move"
  (it "should win in space 6"
    (should= 6 (best-move [O X X
                           X X O
                           0 O 0] O 7)))
  (it "should win in space 6"
    (should= 6 (best-move [X O 0
                           X O 0
                           0 0 X] X 4)))
  (it "should pick the optimal spot 4"
    (should= 4 (best-move [O X O
                           X 0 0
                           0 0 0] X 3))))

(describe "move"
  (it "returns a board with the X moving to  move made"
    (should= [O O X 0 0 X 0 0 X] (move [O O 0 0 0 X 0 0 X] X))))
