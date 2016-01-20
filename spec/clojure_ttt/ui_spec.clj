(ns clojure-ttt.ui-spec
  (:require [speclj.core :refer :all]
        [clojure-ttt.ui :refer :all]
        [clojure-ttt.messages :refer :all]))

(describe "Output states"
  (it "Displays the intro message.")

  (it "Displays the prompt for Player 1's move.")

  (it "Displays the prompt for Player 2's move.")

  (it "Displays the winner is Player 1.")

  (it "Displays the winner is Player 2.")

  (it "Displays there has been a tie.")

  (it "Displays the option for a new game."))

(describe "Input states."
  (it "Is waiting for Player 1's move.")

  (it "Asks for the number of players."))

(describe "Rendering Board"

  (let [X "X"
        O "O"]
  (it "Empty board should look empty"
    (should= (str "___|___|___\n"
                  "___|___|___\n"
                  "   |   |   \n") (with-out-str (render-board {} X O))))

  (it "Should fill in the appropriate spaces automatically."
    (should= (str "_"X"_|_"O"_|_"X"_\n"
                  "_"O"_|_"X"_|_"O"_\n"
                  "   |   |   \n")
      (with-out-str (render-board {1 X 2 O 3 X 4 O 5 X 6 O} X O))))))