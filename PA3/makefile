## Flags
JC = javac
JA = java
JLIBS = "../algs4.jar;."
JTESTLIBS = ".;../algs4.jar"
JFLAGS = -g
CUSTOM_CLASSES = LineSegment.java Point.java

.SUFFIXES: .java .class

## Rules for building classes
FastCollinearPoints.class: FastCollinearPoints.java
	$(JC) $(JFLAGS) -cp $(JLIBS) $(CUSTOM_CLASSES) $*.java

BruteCollinearPoints.class: BruteCollinearPoints.java
	$(JC) $(JFLAGS) -cp $(JLIBS) $(CUSTOM_CLASSES) $*.java

TestClient.class: TestClient.java
	$(JC) -cp $(JLIBS) TestClient.java

## Execute test, example: make test input="collinear/input6.txt"
test: TestClient.class
	set -e; \
    $(JA) -cp $(JTESTLIBS) TestClient $(input)

fast: FastCollinearPoints.class
brute: BruteCollinearPoints.class

clean:
	$(RM) *.class
