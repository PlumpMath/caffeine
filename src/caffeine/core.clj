
(ns caffeine.core)

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
        output
        (combine
         (pour-water
          (grind-coffee beans-input)
          (heat-water water-input))
         (froth-milk milk-input))]
    (println output)))
