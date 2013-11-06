# lein-groovyc

Compile groovy source files

## Usage

   Add the following to your project.clj

   ```clojure
   :plugins [[lein-groovyc "0.3.0"]]

   :groovy-source-paths ["src/groovy"]

   ;; or more generally in groovyc-options
   :groovyc-options {:includeJavaRuntime true}

   ;; automatically run groovyc task after javac
   :hooks [leiningen.hooks.groovyc]
   ```

## License

Copyright (C) 2012 Kurt Harriger

Distributed under the Eclipse Public License, the same as Clojure.
