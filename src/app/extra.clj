(ns app.extra
   (:require [app.ardu :as ardu :refer [board]])
   (:use :reload-all clodiuno.core)
   (:use :reload-all clodiuno.firmata))

(def rodknapp 7)
(def lampa 8)

(defn pinmodes []
  (pin-mode @board rodknapp INPUT)
  (pin-mode @board lampa OUTPUT)
  (enable-pin @board :digital rodknapp)
  (enable-pin @board :digital lampa))

(defn blinka [board tid]
  (digital-write @board lampa HIGH)
  (Thread/sleep tid)
  (digital-write @board lampa LOW)
  (Thread/sleep tid))

(defn lys [tid]
  (dosync
  (digital-write @board lampa HIGH)
  (Thread/sleep tid)
  (digital-write @board lampa LOW)
  (Thread/sleep tid)))

(defn blinker []
  (dotimes [n 12] (lys (* 50 n))))

;(defn ritadec [punkt]
;  (let [{:keys [x y]} punkt]
;  y))

