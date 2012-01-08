(ns leiningen.hooks.groovyc
  (:use [leiningen.groovyc :only [groovyc]])
  (:use [robert.hooke :only [add-hook]])
  (:require [leiningen.javac]))

(add-hook #'leiningen.javac/javac
          (fn [f & args]
            (apply f args)
            (apply groovyc args)))
