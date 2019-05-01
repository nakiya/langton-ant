(ns ant.db
  (:require [ant.world :as aw]))

(def default-db
  (aw/init-world 100 100))
