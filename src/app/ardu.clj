(ns app.ardu
  (:use :reload-all clodiuno.core)
  (:use :reload-all clodiuno.firmata))

(def portdev "/dev/ttyACM0")
(def board (atom nil))

(defn steng []
  (close @board))

(defn init []
  (System/setProperty "gnu.io.rxtx.SerialPorts" portdev)
  (reset! board (arduino :firmata portdev)))
