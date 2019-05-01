(ns ant.world)

(defn init-world [width height]
  {:width width
   :height height
   :grid (into [] (repeat height (into [] (repeat width 0))))
   :ant-pos [(quot width 2) (quot height 2)] ;pos is [x, y]
   :ant-dir :up
   :stopped? false}) ;dir is [dx, dy] for moving

(defn within-bounds? [[row col] width height]
  (and (>= row 0) (>= col 0)
       (< row width) (< col height)))

(defn turn-right [dir]
  (case dir
    :up :right
    :right :down
    :down :left
    :left :up))

(defn turn-left [dir]
  (case dir
    :up :left
    :left :down
    :down :right
    :right :up))

(defn move [[x y] dir]
  (case dir
    :up    [x (dec y)]
    :right [(inc x) y]
    :down  [x (inc y)]
    :left  [(dec x) y]))

(defn iter-world [{:keys [width height grid
                          ant-pos
                          ant-dir
                          stopped?]
                   :as world}]
  (if (and (not stopped?) (within-bounds? ant-pos width height))
    (let [cell-color (get-in grid ant-pos)
          is-white? (= cell-color 0)
          next-dir (if is-white? (turn-right ant-dir) (turn-left ant-dir))
          next-pos (move ant-pos next-dir)
          next-grid (assoc-in grid ant-pos (if is-white? 1 0))]
      {:width width
       :height height
       :grid next-grid
       :ant-pos next-pos
       :ant-dir next-dir
       :stopped? false})
    (assoc world :stopped? true)))


