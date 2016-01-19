(ns clojure-ttt.core
	(:require [clojure-ttt.ai :refer :all]))

(def X "X")
(def O "O")
;Eliminate this state by passing player1 player2 in where X and O are used.


;TODO Terminal stuff - move to other file.

;Forward Declarations

(defn prompt-for-initial-input []
	(println "Welcome to Tic-Tac-Toe!")
	(println "What piece do you choose?")
	(flush)
	(read-line))

(defn -main []
	(prompt-for-initial-input))
