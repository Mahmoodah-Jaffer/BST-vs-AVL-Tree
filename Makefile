JAVAC = /usr/bin/javac
.SUFFIXES: .java .class
SRCDIR = src
BINDIR = bin
DOCDIR = doc

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES= PowerStation.class AVLTree.class PowerAVLApp.class PowerAVLAppTest.class BinarySearchTree.class PowerBSTApp.class PowerBSTAppTest.class
CLASS_FILES= $(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class

doc:
	javadoc -d $(DOCDIR) $(SRCDIR)/*.java

doc_clean:
	rm -rf $(DOCDIR)

