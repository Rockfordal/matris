(ns app.core
  (:require [app.ardu :as ardu :refer [board steng]]
            [app.skiss :as skiss]
            [app.joy :as joy]
            [app.extra :as extra]
            [app.matris :as matris :refer [maxSingle]])
  (:use :reload-all clodiuno.core)
  (:use :reload-all clodiuno.firmata))

(def minn (atom [ #{} #{} #{} #{} #{} #{} #{} #{} ]))

(defn minimappa [i]
  [i] (cond (= i 3) 4
            (= i 4) 8
            (= i 5) 16
            (= i 6) 32
            (= i 7) 64
            (= i 8) 128
            (< i 1) 1
            :else i))

(defn mappa [xx yy]
  (let [x (int (minimappa (int (Math/ceil (/ yy 128)))))
        y (int (Math/ceil (/ xx 128)))]
  [x (if (= y 0) 1 y)]))

(defn rensamem []
  (reset! minn [#{} #{} #{} #{} #{} #{} #{} #{}]))

(defn addtomem [y x]
  (swap! minn update-in [(dec y)] merge (minimappa x) ))

(defn delfrommem [y x]
  (swap! minn update-in [(dec y)] disj (minimappa x) ))

(defn suprendrera []
  (loop [y 8]
    (when (>= y 1)
     (->> (dec y)
          (@minn)         ; (@minn 7)
          (reduce +)      ; (reduce + @minn)
          (maxSingle y))
      (recur (- y 1)))))

(defn rita [v]
  (let [x (first v)
        y (second v)]
    (do
      (addtomem y x)
      (suprendrera))))


(defn ritprogram []
   (let [x      (analog-read @board joy/joyx)
         y      (analog-read @board joy/joyy)
         jknapp (digital-read @board joy/joyknapp)
         rknapp (digital-read @board extra/rodknapp)]
       (do
         (if (= rknapp 1) (rensamem))
         (prn rknapp jknapp)
         (rita (mappa x y))))
  (Thread/sleep 20))

(defn ritt []
  (let [x (analog-read @board joy/joyx)
        y (analog-read @board joy/joyy)]
    (do
      (rita (mappa x y)))))

(defn ritprog []
   (let [jknapp (digital-read @board joy/joyknapp)
         rknapp (digital-read @board extra/rodknapp)]
       (do
         (if (= rknapp 1) (rensamem)))))

(defn testaknapp []
   (let [jknapp (digital-read @board joy/joyknapp)
         rknapp (digital-read @board extra/rodknapp)]
         (prn rknapp jknapp)
  (Thread/sleep 250)))

(comment
  (rensamem)
  (reset! minn [#{1 128 64} #{2} #{4} #{8} #{16} #{32} #{64} #{128 1 2}])
  @minn
  (ritprogram)
  (matris/init 0)
  (matris/clear)
  (joy/pinmodes)
  (extra/pinmodes)
  (while true (testaknapp))
  (while true (ritprogram))
  (skiss/gladgubbe)
  (skiss/vampyr)
  (skiss/minivampyr)
  (ardu/steng)
  (extra/lys 500)
  (extra/blinker)
  (ardu/steng))

(defn -main []
  (Thread/sleep 3000)
  (ardu/init)
  (matris/init 0)
  (matris/clear)
  (joy/pinmodes)
  (extra/pinmodes)
  (while true (ritprogram))
  ;(ardu/steng)
  (println "Hej då!"))

(-main)
