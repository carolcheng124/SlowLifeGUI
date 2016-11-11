# SlowLifeGUI
Conway's Game of Life, in GUI Form.  Deliberately non-performant.

###PROFILE AND TARGET
After starting running the application, the CPU consuming status can be watched by profiler -> CPU in VisualVM. After random clicking on some buttom in the panel to make cells alive, then click "runContinous" to keep it reproductive. Most hotspot methods and their consuming time will be showed up. 


After making analysis the screenshot, I decide to find target methods required improvements based on their total time and self time consumption. I figure out there are several methods consuming plenty of CPU. First, in the mainly called function runContinous(), I figure out runContinous() method's selftime is huge (20.7%). It is suspicious that runContinous() need improvement.


Second, there are two methods were called in runContinous(), calculateNextIteration（）and backup(). I analyzed each from top to the bottom.


in calculateNextIteration（), two methods, IterateCell() and DisplayIteration() are called in calculateNextIteration（). For IterateCell(), it is quite obvious  that getNumNeighbors() engaged almost 64% of the total time. ConverIntInt() method, called in getNumNeighbors() even consume almost 64% of the total time and 51.5% self time. That's why I target on getNumNeighbors() and ConverIntInt().


Even though backup() consumed much less than CalculateNextIteration(), but cell<init> called in backup() still consume almost 6% self time, that's why I'd like to make improvement on backup(), especially to avoid construction of cell instances.


Below the link to the screenshot of the CPU status when running the original application:
https://github.com/carolcheng124/SlowLifeGUI/blob/master/screenshots/snapshot1_initial.png
https://github.com/carolcheng124/SlowLifeGUI/blob/master/screenshots/snapshot11.1_initial.png


Below the link to the screenshot of the CPU status when running the application after modification:
https://github.com/carolcheng124/SlowLifeGUI/blob/master/screenshots/snapshot2_final.png


###MANUAL TEST ON runContinous()

PRECONDITION:

run this application, set panel size as 15, all cells shows grey in panel.
EXECUTION STEPS:

1. click on all cells in row 7 to trigger them alive, showing "X" with red backgroundcolor

2. click on all cells in colum 7 to trigger them alive, showing "X" with red backgroundcolor

3. click on "runContinous" button

POSTCONDITIONS: 

All cells start keeping changing color, as part of the cells alive and show red and part of the cells dies and show green, but the status keep changing, until at some point, their colors keep stable there forever.
