(ns clojure-ttt.ui)

(defn prompt-for-input [string]
  (println string)
  (flush)
  (read-line))

(defn input-error []
  (println "Invalid input."))

(defn welcome-message []
  (println "Welcome to tic-tac-toe."))

(defn get-piece []
  (let [piece (prompt-for-input "What piece would you like to play X or O?")]
    (if (or (= piece "X")(= piece "x")(= piece "O")(= piece "o"))
      (clojure.string/upper-case piece)
      ((input-error)(get-piece)))))

(defn how-many-humans []
  (let [humans (try (Integer/parseInt (prompt-for-input "How many humans are playing today?"))
                    (catch java.lang.NumberFormatException e ((println "Try a number between 1-9!") (how-many-humans))))]
    (if (and (>= humans 0) (<= humans 2))
      humans
      ((input-error) (how-many-humans)))))

(defn computer-processing []
  (println "The computer is thinking..."))

(defn get-move [player]
  (let [move (Integer/parseInt (prompt-for-input (str "Where does " player " want to move? Choose 1-9")))]
    (if (and (>= move 1) (<= move 9))
      move
      ((input-error) (get-move player)))))

(defn winner-message [winner]
  (println (str "The winner is " winner "!!! Perhaps you'd like to play again.")))

(defn tie-message []
  (println "There seems to have been a tie, you should play again."))

(defn render-board [board]
  (println (str "_" (get board 1 "_") "_|_" (get board 2 "_") "_|_" (get board 3 "_") "_"))
  (println (str "_" (get board 4 "_") "_|_" (get board 5 "_") "_|_" (get board 6 "_") "_"))
  (println (str " " (get board 7 " ") " | " (get board 8 " ") " | " (get board 9 " ") " ")))
