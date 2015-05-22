(ns delete-nth.core)

(defn add-element-until [n]
  (fn [[elements counts] elem]
    (let [elem-counts (get counts elem)]
      (if (< elem-counts n)
        [(conj elements elem)
         (assoc
           counts
           elem
           (inc elem-counts))]
        [elements counts]))))

(defn initial-counts-by-element [coll]
  (zipmap coll (repeat (count coll) 0)))

(defn delete-nth [n coll]
  (first
    (reduce
      (add-element-until n)
      [[] (initial-counts-by-element coll)]
      coll)))

(defn delete-nth-rec
  ([n coll]
   (delete-nth-rec
     n
     coll
     (initial-counts-by-element coll)
     []))
  ([n [elem & next-elems] counts acc]
   (if (nil? elem)
     acc
     (let [elem-counts (get counts elem)]
       (if (< elem-counts n)
         (recur
           n
           next-elems
           (assoc counts elem (inc elem-counts))
           (conj acc elem))
         (recur
           n
           next-elems
           (assoc counts elem (inc elem-counts))
           acc))))))