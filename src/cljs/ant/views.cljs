(ns ant.views
  (:require
   [re-frame.core :as re-frame]
   [ant.subs :as subs]
   [ant.events :as ev]
   [cljs.pprint :refer [pprint]]
   ))

(defn main-panel []
  (let [grid (re-frame/subscribe [::subs/grid])]
    (into [:table {:style {:border "1px solid red"}}]
          (for [row @grid]
            (into [:tr]
                  (for [cell row]
                    [:td {:width "5px" :height "5px"
                          :style {:backgroundColor (if (= cell 1) "black" "white")}}]))))))

(defn dispatch-timer-event
  []
  (let [now (js/Date.)]
    (re-frame/dispatch [::ev/timer now])))  ;; <-- dispatch used

;; Call the dispatching function every second.
;; `defonce` is like `def` but it ensures only one instance is ever
;; created in the face of figwheel hot-reloading of this file.
(defonce do-timer (js/setInterval dispatch-timer-event 50))
