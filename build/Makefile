ver  := 0.2.0
mod  := expath-tools-java
root := $(mod)-$(ver)
prj  := ../tools-java
jar  := $(prj)/dist/tools-java.jar

prop=$(prj)/src/org/expath/tools/version.properties
rev=$(shell git describe --always)

all: clean dist

dist:
	echo "org.expath.tools.version=$(ver)"  >  $(prop)
	echo "org.expath.tools.revision=$(rev)" >> $(prop)
	( cd "$(prj)" && ant )
	mkdir "$(root)"
	echo "Version: $(ver)" > "$(root)/VERSION"
	echo "Git revision: $(rev)" >> "$(root)/VERSION"
	cp "$(jar)" "$(root).jar"
	cp "$(jar)" "$(root)/$(root).jar"
	cp README "$(root)/README"
	cp -pPR "$(prj)/src" "$(root)"
	find "$(root)/src" -type d -name .~ \
	    -exec rm -rf {} \; 2>/dev/null || true
	zip -r "$(root).zip" "$(root)"
	rm -rf "$(root)"

clean:
	rm -f  "$(root).jar"
	rm -fr "$(root)"
	rm -f  "$(root).zip"

check-version:
	java -cp "$(root).jar" org.expath.tools.Version

.PHONY: dist clean
