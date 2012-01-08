(ns leiningen.groovyc
  (:use [leiningen.classpath :only [get-classpath-string]]
        [leiningen.util.paths :only [normalize-path]])
  (:require [lancet.core :as lancet])
  (:import [java.io File]
           [org.codehaus.groovy.ant Groovyc]))

(.addTaskDefinition lancet/ant-project "groovyc" Groovyc)
(lancet/define-ant-task groovyc-task "groovyc")

(def ^{:doc "Default options for the groovyc compiler."
       :dynamic true}
  *default-groovyc-options*
  {:fork "true"
   :includeantruntime "no"
   :includejavaruntime "yes"
   :verbose "true"})

(defn- extract-groovyc-task
  "Extract a compile task from the given spec."
  [project [path & options]]
  (merge *default-groovyc-options*
         (:groovc-options project)
         {:destdir (:compile-path project)
          :srcdir (normalize-path (:root project) path)
          :classpath (get-classpath-string project)}
         (apply hash-map options)))

(defn- extract-groovyc-tasks
  "Extract all compile tasks of the project."
  [project]
  (let [specs (:groovy-source-path project)]
    (for [spec (if (string? specs) [[specs]] specs)]
      (extract-groovyc-task project spec))))

(defn- run-groovyc-task
  "Compile the given task spec."
  [task-spec]
  (lancet/mkdir {:dir (:destdir task-spec)})
  (groovyc-task task-spec))

(defn groovyc
  "Compile Java source files.

Add a :groovy-source-path key to project.clj to specify where to find them."
  [project & [directory]]
  (doseq [task (extract-groovyc-tasks project)
          :when (or (nil? directory) (= directory (:srcdir task)))]
    (run-groovyc-task task)))
