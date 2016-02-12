(defproject clojure-ttt "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0-RC2"]
                 [net.mikera/core.matrix "0.49.0"]]
  :profiles {:dev {:dependencies [[speclj "3.3.1"]]}}
  :plugins [[speclj "3.3.1"]]
  :test-paths ["spec"]
  :main clojure-ttt.core)
