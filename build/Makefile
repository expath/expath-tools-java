ver  := 0.1.0dev
mod  := expath-model-java
root := $(mod)-$(ver)
prj  := ../model-java
jar  := $(prj)/dist/model-java.jar

prop=$(prj)/src/org/expath/model/version.properties
rev=$(shell git describe --always)

all: clean dist

dist:
	echo "org.expath.model.version=$(ver)"  >  $(prop)
	echo "org.expath.model.revision=$(rev)" >> $(prop)
	( cd "$(prj)" && ant )
	mkdir "$(root)"
	echo "Version: $(ver)" > "$(root)/VERSION"
	echo "Git revision:" `git describe --always` >> "$(root)/VERSION"
	cp "$(jar)" "$(mod).jar"
	cp "$(jar)" "$(root)/$(mod).jar"
	cp README "$(root)/README"
	cp -pPR "$(prj)/src" "$(root)"
	find "$(root)/src" -type d -name .~ \
	    -exec rm -rf {} \; 2>/dev/null || true
	zip -r "$(root).zip" "$(root)"
	rm -rf "$(root)"

clean:
	rm -f  "$(mod).jar"
	rm -fr "$(root)"
	rm -f  "$(root).zip"

check-version:
	java -cp "$(mod).jar" org.expath.model.Version

.PHONY: dist clean
