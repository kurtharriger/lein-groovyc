(ns leiningen.groovyc
  (:require [clojure.string :as string])
  (:require [leiningen.core.classpath :as classpath])
  (:require [clojure.java.io :as io])
  (:require [lancet.core :as lancet])
  (:import [org.codehaus.groovy.ant Groovyc]))

(.addTaskDefinition lancet/ant-project "groovyc" Groovyc)
(lancet/define-ant-task groovyc-task "groovyc")

(def ^{:doc "Default options for the groovyc compiler."
       :dynamic true}
  *default-groovyc-options*
  {:fork "true"
   :includeantruntime "no"
   :includejavaruntime "yes"
   :verbose "true"})

(defn get-classpath-string [project]
  (string/join java.io.File/pathSeparatorChar (classpath/get-classpath project)))

(defn- extract-groovyc-task
  "Extract a compile task from the given spec."
  [project path]
  (merge *default-groovyc-options*
         (:groovc-options project)
         {:destdir (:compile-path project)
          :srcdir path
          :classpath (get-classpath-string project)}))

(defn- extract-groovyc-tasks
  "Extract all compile tasks of the project."
  [project]
  (let [paths (:groovy-source-paths project [(:groovy-source-path project)])]
    (for [path paths]
      (extract-groovyc-task project path))))

(defn- run-groovyc-task
  "Compile the given task spec."
  [task-spec]
  (lancet/mkdir {:dir (:destdir task-spec)})
  (groovyc-task task-spec))

(defn groovyc
  "Compile Groovy source files.

Add a :groovy-source-path key to project.clj to specify where to find them."
  [project & [directory]]
  (doseq [task (extract-groovyc-tasks project)
          :when (or (nil? directory) (= directory (:srcdir task)))]
    (run-groovyc-task task)))
