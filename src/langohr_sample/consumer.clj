(ns langohr-sample.core
  (:gen-class)
  (:require
     [langohr.core      :as rmq]
     [langohr.channel   :as lch]
     [langohr.queue     :as lq]
     [langohr.consumers :as lc]
     [langohr.basic     :as lb]
     ))

(let [conn     (rmq/connect)
      ch       (lch/open conn)
      consumer (lcons/create-default ch 
                                     :handle-delivery-fn  
                                     (fn [ch metadata ^bytes payload]
                                       (println "Received a message: " (String. payload)))
                                     :handle-consume-ok-fn 
                                     (fn [consumer-tag]
                                       (println "Consumer registered")))]
  (lb/consume ch queue consumer))
