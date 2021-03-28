# Conway
## Hayden McAlister

A simple implementation of Conways game of life in Java (java.swing). Allows
for running a simulation of the game of life, ticking forward one step,
randomising the world. Also allows for saving the state and restoring it later
(Assuming the world is the same size), and for changing the colour of the
cells.

All the constants for the project can be found in Constant.java, from which you
can alter the panel dimensions, cell dimensions, the directory to save into,
and more.

## Compile and Run
* Run `javac *.java -d .` from the directory you clone into
* Run `java Conway.ConwayApp` and let the window open up
* Use your mouse to change the state of cells, and click run/tick to advance
  time
