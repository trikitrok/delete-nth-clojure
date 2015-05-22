(ns delete-nth.core-test
  (:use midje.sweet)
  (:require [delete-nth.core :refer [delete-nth delete-nth-rec]]))

(facts
  "about deleting after copies after the nth one"

  (fact
    "an empty list is ignored"
    (delete-nth 1 []) => []
    (delete-nth-rec 1 []) => [])

  (fact
    "it removes all copies of one element after the nth one"
    (delete-nth 1 [1 2 1]) => [1 2]
    (delete-nth-rec 1 [1 2 1]) => [1 2]
    (delete-nth 2 [1 2 1 2 5 2]) => [1 2 1 2 5]
    (delete-nth-rec 2 [1 2 1 2 5 2]) => [1 2 1 2 5]
    (delete-nth 3 [2 1 2 2 1 2 5 2 1]) => [2 1 2 2 1 5 1]
    (delete-nth-rec 3 [2 1 2 2 1 2 5 2 1]) => [2 1 2 2 1 5 1]))
