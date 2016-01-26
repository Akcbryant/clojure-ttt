(ns clojure-ttt.core-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.core :as core]
            [clojure-ttt.board :as board]
            [clojure-ttt.ui :as ui]
            [clojure-ttt.human-player :as human]
            [clojure-ttt.hard-ai :as ai]))

(defn mock-fn
  ([parameters-atom return-value]
    (fn [& parameters]
      (reset! parameters-atom (conj @parameters-atom {:parameters parameters}))
      return-value))
  ([parameters-atom] (mock-fn parameters-atom nil)))

(describe "Play game"
  (it "ai vs ai should follow a predictable path"
    (let [get-invocations (atom [])]
      (with-redefs [ui/render-board (mock-fn get-invocations {})]
        (core/play-game ai/move ai/move {} board/X)
        (should= {} (first (:parameters (get @get-invocations 0))))
        (should= {9 "X"} (first (:parameters (get @get-invocations 1))))
        (should= {9 "X" 5 "O"} (first (:parameters (get @get-invocations 2))))
        (should= {9 "X" 5 "O" 8 "X"} (first (:parameters (get @get-invocations 3))))
        (should= {9 "X" 5 "O" 8 "X" 7 "O"} (first (:parameters (get @get-invocations 4))))
        (should= {9 "X" 5 "O" 8 "X" 7 "O" 3 "X"} (first (:parameters (get @get-invocations 5))))
        (should= {9 "X" 5 "O" 8 "X" 7 "O" 3 "X" 6 "O"} (first (:parameters (get @get-invocations 6))))
        (should= {9 "X" 5 "O" 8 "X" 7 "O" 3 "X" 6 "O" 4 "X"} (first (:parameters (get @get-invocations 7))))
        (should= {9 "X" 5 "O" 8 "X" 7 "O" 3 "X" 6 "O" 4 "X" 2 "O"} (first (:parameters (get @get-invocations 8))))
        (should= {9 "X" 5 "O" 8 "X" 7 "O" 3 "X" 6 "O" 4 "X" 2 "O" 1 "X"}(first (:parameters (get @get-invocations 9))))))))

(run-specs)
