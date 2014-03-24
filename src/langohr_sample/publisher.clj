(ns langohr-sample.publisher
  (:gen-class)
  (:require 
     [langohr.core      :as rmq]
     [langohr.channel   :as lch]
     [langohr.queue     :as lq]
     [langohr.consumers :as lc]
     [langohr.basic     :as lb]))

(def ^{:const true}
  default-exchange-name "")

(defn publish []
  (let [conn  (rmq/connect {:host "192.168.34.10"})
        ch    (lch/open conn)
        qname "langohr.examples.hello-world"]
    (println (format "[main] Connected. Channel id: %d" (.getChannelNumber ch)))
    (lq/declare ch qname :exclusive false :durable true)
    (doseq [i (range 10)]
      (lb/publish ch default-exchange-name qname (str i ) :content-type "text/plain" :type "greetings.hi"))
    (println "[main] Disconnecting...")
    (rmq/close ch)
    (rmq/close conn)))

