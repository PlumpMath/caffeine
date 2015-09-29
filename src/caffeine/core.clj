(ns caffeine.core
  (:require [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! timeout]]))

(defn froth-milk
  [milk]
  (println "Frothing milk...")
  (Thread/sleep (rand 3000))
  "frothed milk")

(defn heat-water
  [water]
  (println "Heating water...")
  (Thread/sleep (rand 3000))
  "boiled water")

(defn grind-coffee
  [beans]
  (println "Grinding beans manually...")
  (Thread/sleep (rand 3000))
  "coffee grounds")

(defn pour-water
  [ground-coffee hot-water]
  (println "Pouring water...")
  (Thread/sleep (rand 3000))
  "hot coffee")

(defn combine
  [foamed-milk brewed-coffee]
  (println "Combining...")
  (Thread/sleep (rand 3000))
  "a delicious latte")

(defn main []
  (let [milk-input "Cold Milk"
        beans-input "Coffee Beans"
        water-input "Cold Water"
        ch1 (chan)
        ch2 (chan)
        ch3 (chan)
        ch4 (chan)
        ch5 (chan)]
    (go (>! ch1 (froth-milk milk-input)))
    (go (>! ch2 (heat-water water-input)))
    (go (>! ch3 (grind-coffee beans-input)))
    (go (>! ch4 (pour-water (<! ch3) (<! ch2))))
    (go (>! ch5 (combine (<! ch1) (<! ch4))))
    (println (<!! ch5))))
