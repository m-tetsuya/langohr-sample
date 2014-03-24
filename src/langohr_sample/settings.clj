; not used yet
(ns langohr-sample.settings
  (:gen-class)
  (:require 
     [langohr.core      :as rmq]
     [langohr.channel   :as lch]
     [langohr.queue     :as lq]
     [langohr.consumers :as lc]
     [langohr.basic     :as lb]))

(def config
  (let [env (or (System/getenv "ENVIRONMENT") "development")]
    ((keyword env)
      {:development
         {:server '("192.168.34.10" "192.168.34.11")}
       :test
         {:database-url "postgres://lborges:@localhost/clj-boilerplate-test"}
       :production
         {:database-url (System/getenv "DATABASE_URL")}})))
