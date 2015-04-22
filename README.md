# HUM
A Java Servlet Based Configurable Server

Write specifications not code.

The HUM server can be configured to perform any web server task because it gets its instructions dynamically. HUM can do this since the instructions are provided to it as an abstract syntax tree (AST)
.

##Whassa AST?##
Abstract syntax trees represent code constructs within in a program.  High level languages such as C++ and Java are compiled into ASTs during the compliation process. Thus any  program written in a high level language can be represented with an AST. HHUM is told what to do by reading a set of ASTs which are formatted in XML, (itself a tree structure). Don't get confused though. HUM does not use a compiler or an domain specific language, (not yet at least). The XML specifications are written by the user. The human readable format of XML makes this easy to do.


##TODO##
Add support for JSON formatted specification documents. (Currently only XML is supported)
Break out parser to use a schema descriptor. The schema descriptor and validator will most likely be imported as a jar from a separate project.
