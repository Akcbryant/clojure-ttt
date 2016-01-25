(ns clojure-ttt.core-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.core :as core]
            [clojure-ttt.board :as board]
            [clojure-ttt.ui :as ui]
            [clojure-ttt.human-player :as human]
            [clojure-ttt.hard-ai :as ai]))

(describe "Play game"
  (it "renders the board with given board when called at first"
    (let [render-board-called (atom 0)]
      (with-redefs [ui/render-board (fn [_] (reset! render-board-called 1))]
        (core/play-game ai/move ai/move {} board/X)
        ;compare boards using mock fn from ant-game
        (should= 1 @render-board-called)))))

(run-specs)
