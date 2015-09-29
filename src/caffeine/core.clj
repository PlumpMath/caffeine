(ns caffeine.core
  (:require [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! timeout]]))

(defn froth-milk
  [milk]
  (let [out (chan)]
    (go
      (when (<! milk)
        (println "Frothing milk...")
        (Thread/sleep (rand 3000))
        (>! out "frothed milk")))
    out))

(defn heat-water
  [water]
  (let [out (chan)]
    (go
      (when (<! water)
        (println "Heating water...")
        (Thread/sleep (rand 3000))
        (>! out "boiled water")))
    out))

(defn grind-coffee
  [beans]
  (let [out (chan)]
    (go
      (when (<! beans)
        (println "Grinding beans manually...")
        (Thread/sleep (rand 3000))
        (>! out "ground coffee")))
    out))

(defn pour-water
  [ground-coffee hot-water]
  (let [out (chan)]
    (go
      (when (and (<! ground-coffee) (<! hot-water))
        (println "Pouring water...")
        (Thread/sleep (rand 3000))
        (>! out "hot coffee")))
    out))

(defn combine
  [foamed-milk brewed-coffee]
  (let [out (chan)]
    (go
      (when (and (<! foamed-milk) (<! brewed-coffee))
        (println "Combining...")
        (Thread/sleep (rand 3000))
        (>! out "a delicious latte")))
    out))

(defn main []
  (let [milk-input (chan)
        beans-input (chan)
        water-input (chan)
        output
        (combine
         (pour-water
          (grind-coffee beans-input)
          (heat-water water-input))
         (froth-milk milk-input))]
    (go
      (>! milk-input "Milk")
      (>! beans-input "Coffee Beans")
      (>! water-input "Cold Water"))
    (println (<!! output))))
