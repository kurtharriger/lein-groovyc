# lein-groovyc

Compile groovy source files

## Usage

  Now using Groovy v2.1.9

   Add the following to your project.clj

  Available options for use with :groovyc-options - http://groovy.codehaus.org/api/org/codehaus/groovy/ant/Groovyc.html

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
