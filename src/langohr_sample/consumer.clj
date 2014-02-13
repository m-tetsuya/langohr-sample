(ns langohr-sample.consumer
  (:gen-class)
  (:require
     [langohr.core      :as rmq]
     [langohr.channel   :as lch]
     [langohr.queue     :as lq]
     [langohr.consumers :as lcons]
     [langohr.basic     :as lb]
     )
  (:import [com.rabbitmq.client ShutdownSignalException]))

(defn consume []
  (let [conn     (rmq/connect {:host "192.168.34.10" })
        ch       (lch/open conn)
        qname "langohr.examples.hello-world"
        {:keys [queue]} (lq/declare ch qname :exclusive false )
        consumer (lcons/create-default ch 
                                       :handle-delivery-fn  
                                       (fn [ch metadata ^bytes payload]
                                         (println "Received a message: " (String. payload)))
                                       :handle-consume-ok-fn 
                                       (fn [consumer-tag]
                                         (println "Consumer registered"))
                                       :handle-cancel-fn (fn [consumer-tag]
                                                           (println "Consumer registered"))
                                       :handle-shutdown-signal-fn (fn  [^String consumer-tag ^ShutdownSignalException sig]
                                                           (println consumer-tag)))]
    (try
    (lb/consume ch queue consumer)
      (catch Exception e
        (println (.getMessage e))))
      
      
      ))
