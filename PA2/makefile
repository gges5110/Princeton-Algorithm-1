## Flags
JC = javac
JA = java
JLIBS = "../algs4.jar;../junit-4.11.jar;."
JTESTLIBS = ".;../algs4.jar;../junit-4.11.jar;../hamcrest-core-1.3.jar"
JUNITCORE = org.junit.runner.JUnitCore
JFLAGS = -g
XLINTFLAGS = -Xlint:unchecked

.SUFFIXES: .java .class

## Rules for building classes
Deque.class: Deque.java
	$(JC) $(JFLAGS) -cp $(JLIBS) $(XLINTFLAGS) $*.java

RandomizedQueue.class: RandomizedQueue.java
	$(JC) $(JFLAGS) -cp $(JLIBS) $(XLINTFLAGS) $*.java

DequeTest.class: DequeTest.java
	$(JC) $(JFLAGS) -cp $(JLIBS) $*.java

RandomizedQueueTest.class: RandomizedQueueTest.java
	$(JC) $(JFLAGS) -cp $(JLIBS) $*.java

deque: Deque.class
random-queue: RandomizedQueue.class

## Execute tests
deque-test: DequeTest.class Deque.class
	set -e; \
    $(JA) -cp $(JTESTLIBS) $(JUNITCORE) DequeTest

random-test: RandomizedQueueTest.class RandomizedQueue.class
	set -e; \
    $(JA) -cp $(JTESTLIBS) $(JUNITCORE) RandomizedQueueTest

clean:
	$(RM) *.class
