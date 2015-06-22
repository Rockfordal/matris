(ns app.skiss
  (:require [app.matris :as matris :refer [maxSingle columns]]))

(defn minivampyr []
  (matris/maxxa 255 189 219 126 165 195 102 60))

(defn gladgubbe[]
  (maxSingle (columns :col-1) (int 2r11111111))  ;; 255
  (maxSingle (columns :col-2) (int 2r11011011))  ;; 189
  (maxSingle (columns :col-3) (int 2r11011011))  ;; 219
  (maxSingle (columns :col-4) (int 2r11111111))  ;; 254
  (maxSingle (columns :col-5) (int 2r10111101))  ;; 165
  (maxSingle (columns :col-6) (int 2r11000011))  ;; 195
  (maxSingle (columns :col-7) (int 2r01100110))  ;; 102
  (maxSingle (columns :col-8) (int 2r00111100))) ;; 60

(defn vampyr []
  (maxSingle (columns :col-1) (int 2r11111111)) ;; 255
  (maxSingle (columns :col-2) (int 2r10111101))
  (maxSingle (columns :col-3) (int 2r11011011))
  (maxSingle (columns :col-4) (int 2r01111110)) ;; 126
  (maxSingle (columns :col-5) (int 2r10100101))
  (maxSingle (columns :col-6) (int 2r11000011))
  (maxSingle (columns :col-7) (int 2r01100110))
  (maxSingle (columns :col-8) (int 2r00111100)))

(defn h []
  (maxSingle (columns :col-1) (int 2r11000011)) ;; 255
  (maxSingle (columns :col-2) (int 2r11000011))
  (maxSingle (columns :col-3) (int 2r11000011))
  (maxSingle (columns :col-4) (int 2r11111111)) ;; 126
  (maxSingle (columns :col-5) (int 2r11111111))
  (maxSingle (columns :col-6) (int 2r11000011))
  (maxSingle (columns :col-7) (int 2r11000011))
  (maxSingle (columns :col-8) (int 2r11000011)))

(defn e []
  (maxSingle (columns :col-1) (int 2r11111111)) ;; 255
  (maxSingle (columns :col-2) (int 2r11111111))
  (maxSingle (columns :col-3) (int 2r11000000))
  (maxSingle (columns :col-4) (int 2r11111111)) ;; 126
  (maxSingle (columns :col-5) (int 2r11111111))
  (maxSingle (columns :col-6) (int 2r11000000))
  (maxSingle (columns :col-7) (int 2r11111111))
  (maxSingle (columns :col-8) (int 2r11111111)))

;(defn j []
;  (maxSingle (columns :col-1) (int 2r11111111)) ;; 255
;  (maxSingle (columns :col-2) (int 2r11111111))
;  (maxSingle (columns :col-3) (int 2r11000000))
;  (maxSingle (columns :col-4) (int 2r11111111)) ;; 126
;  (maxSingle (columns :col-5) (int 2r11111111))
;  (maxSingle (columns :col-6) (int 2r11000000))
;  (maxSingle (columns :col-7) (int 2r11111111))
;  (maxSingle (columns :col-8) (int 2r11111111)))
