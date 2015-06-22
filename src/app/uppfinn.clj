(ns app.uppfinn)

(def punkt (atom {:x 1 :y 1}))
(def minne (atom #{}))

(defn update-state [key val]
  (swap! state assoc key val))

(defn get-state [key]
  (@state key))

(defn map-int [x in-min in-max out-min out-max]
  (+ (/ (* (- x in-min) (- out-max out-min)) (- in-max in-min)) out-min))

(defn getx [x]
  [x] (cond (< x 508) "vänster"
            (> x 509) "höger"
            :else "mitt"))

(defn gety [y]
  [y] (cond (< y 516) "upp"
            (> y 518) "ner"
            :else "mitt"))

(swap! minne conj @punkt)
(reset! punkt {:x 4 :y 6})

;@minne
;@punkt
