(ns app.joy
   (:require [app.ardu :as ardu :refer [board]])
   (:use :reload-all clodiuno.core)
   (:use :reload-all clodiuno.firmata))

;; Joystick
(def joyx 0)
(def joyy 1)
(def joyknapp 2)

(defn pinmodes []
  (println "Startar..")
  (pin-mode @board joyknapp INPUT)
  (pin-mode @board joyx INPUT)
  (pin-mode @board joyy INPUT)
  (enable-pin @board :analog joyx)
  (enable-pin @board :analog joyy)
  (enable-pin @board :digital joyknapp))
