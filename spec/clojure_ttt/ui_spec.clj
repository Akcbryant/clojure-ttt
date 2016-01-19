(ns clojure-ttt.ui-spec
	(:require [speclj.core :refer :all]
			  [clojure-ttt.ui :refer :all]))

(describe "UI"
	(it "Prints the intro message."
		(should= (str intro-message "\n") (with-out-str (prompt intro-message))))

	(it "Reads input from the user."
		(should= input-message (with-in-str input-message (get-input)))))

(describe "Rendering Board"

 	(it "Empty board should look empty"
 		(should= "___|___|___\n
 				  ___|___|___\n
 				     |   |   \n" (with-out-str (render-board {}))))

 	(it "Should fill in the appropriate spaces automatically."
 		(should= "_"X"_|_"O"_|_"X"_\n
 				  _"O"_|_"X"_|_"O"_\n
 				       |     |     \n" 
 			(with-out-str (render-board {1 X 2 O 3 X 4 O 5 X 6 O})))))