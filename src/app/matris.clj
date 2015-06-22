(ns app.matris
   (:require [app.ardu :as ardu :refer [board]])
   (:use :reload-all clodiuno.core)
   (:use :reload-all clodiuno.firmata))

(def state (atom {:bugs ()
                  :items #{4 5 6}}))

(defn digibug [bug]
  (swap! state update-in [:bugs] conj bug))

(def datain 12)
(def lada 10)
(def clock 11)

(maxSingle (columns :col-1) (int 2r01010101))

(:bugs @state)

(def reg {:no-op (byte 0x0)
          :decode-mode (byte 0x9)    ;; 9
          :intensity (byte 0xa)      ;; 10
          :scan-limit (byte 0xb)     ;; 11
          :shutdown (byte 0xc)       ;; 12
          :display-test (byte 0xf)}) ;; 15

(def columns {:col-1 (byte 0x1)
              :col-2 (byte 0x2)
              :col-3 (byte 0x3)
              :col-4 (byte 0x4)
              :col-5 (byte 0x5)
              :col-6 (byte 0x6)
              :col-7 (byte 0x7)
              :col-8 (byte 0x8)})

(defn pinmodes []
  ; Aktivera portarna som digitala utgångar
  (pin-mode @board datain OUTPUT)
  (pin-mode @board clock OUTPUT)
  (pin-mode @board lada OUTPUT)
  (enable-pin @board :digital datain)
  (enable-pin @board :digital clock)
  (enable-pin @board :digital lada))

(defn putBajt [data]
  (loop [i 7]
    (when (>= i 0)
      (let [mask (bit-shift-left 0x1 i)]
      (digital-write @board clock LOW)
      (digibug "c")
      ;(println "mask:" mask "data:" data "i:" i)

      (if (not (= 0 (bit-and data mask)))
       (do
         (digital-write @board datain HIGH)
         (digibug "1")
         )
       (do
         (digital-write @board datain LOW)
         (digibug "0")
         ))
      (digital-write @board clock HIGH)
      (digibug "C")
    )
    (recur (- i 1)))))

(defn maxSingle [reg col]
    (digital-write @board lada LOW)
    ;(digibug "l") ;; CS
    (putBajt reg)
    (putBajt col)
    (digital-write @board lada HIGH)
    ;(digibug "L")
)

(defn maxxa [a b c d e f g h]
  (maxSingle (columns :col-1) a)
  (maxSingle (columns :col-2) b)
  (maxSingle (columns :col-3) c)
  (maxSingle (columns :col-4) d)
  (maxSingle (columns :col-5) e)
  (maxSingle (columns :col-6) f)
  (maxSingle (columns :col-7) g)
  (maxSingle (columns :col-8) h))

(defn clear []
  (maxxa 0 0 0 0 0 0 0 0))

(defn init [intensity]
  (pinmodes)
  (maxSingle (reg :decode-mode) 0)
  (maxSingle (reg :display-test) 0)
  (maxSingle (reg :scan-limit) 7)
  (maxSingle (reg :intensity) intensity)
  (maxSingle (reg :shutdown) 1))

(defn test []
  (maxSingle (reg :display-test) (byte 0x1)))

;(:bugs @state)           ;; titta i loggen
