(ns clojure-ttt.human-player-spec
    (:require [speclj.core :refer :all]
              [clojure-ttt.human-player :refer :all]))

(describe "Human player"
  (it "return a board for the move input"
    (should= [1 0 0 0 0 0 0 0 0] (with-in-str "0" (move [0 0 0 0 0 0 0 0 0] 1)))))
