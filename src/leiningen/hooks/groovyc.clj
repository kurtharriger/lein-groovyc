(ns leiningen.hooks.groovyc
  (:require [leiningen.groovyc :as groovyc])
  (:require [robert.hooke :as hooke])
  (:require [leiningen.compile :as compile]))

(defn activate  []
  (hooke/add-hook #'compile/compile
                  (fn [f & args]
                    (apply groovyc/groovyc args)
                    (apply f args))))