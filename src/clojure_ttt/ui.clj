(ns clojure-ttt.ui
  (:require [clojure-ttt.messages :refer :all]))

(def intro-message "Welcome to tic tac toe.\nWhich piece do you want?")
(def input-message "What piece would you like to use?")

(defn output-string [string]
  (println string))

(defn get-input []
  (flush)
  (read-line))

(defn render-board [board player1 player2]
  (println (str "_" (get board 1 "_") "_|_" (get board 2 "_") "_|_" (get board 3 "_") "_"))
  (println (str "_" (get board 4 "_") "_|_" (get board 5 "_") "_|_" (get board 6 "_") "_"))
  (println (str " " (get board 7 " ") " | " (get board 8 " ") " | " (get board 9 " ") " ")))