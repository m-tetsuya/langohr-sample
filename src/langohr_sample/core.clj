(ns langohr-sample.core
    (:gen-class)
    (:require 
       [langohr-sample.publisher :as publisher]
       [langohr-sample.consumer :as consumer]
       ))

(defn -main
  ([] (println "lein run [p/c]"))
  ([role]
   (case role
         "p" (publisher/publish)
         "c" (consumer/consume)
         (println "lein run [p/c]")))
  ([role & args] (println "lein run [p/c]")))
