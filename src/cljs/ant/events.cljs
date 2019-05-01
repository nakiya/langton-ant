(ns ant.events
  (:require
   [re-frame.core :as re-frame]
   [ant.db :as db]
   [ant.world :as aw]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::timer
 (fn [db _]
   (aw/iter-world db)))
