# lein-groovyc

Compile groovy source files

## Usage

   Add the following to your project.clj
   :dev-dependencies [[lein-groovyc "0.1.0"]]
   :groovyc-source-path "src/groovy"

   ;;Multiple paths can be specified using paths instead
   :groovyc-source-paths ["src/groovy/main"]

   ;; or more generally in groovyc-options
   :groovyc-options {:includeJavaRuntime true}


   Run groovyc
   lein clean, groovyc

## License

Copyright (C) 2012 Kurt Harriger

Distributed under the Eclipse Public License, the same as Clojure.
