# v1.1.0: The Grand Refactoring
Refactored most of the code to conform with the modern standards. There are a multitude of changes, 
which will be briefly documented in a list below.
## Changes
- Refactored File Tree according to the Maven Architecture
- Introduced Maven into the Project
  - Flexible Dependency Management
  - Unit Testing
  - JAR Packaging
- Changed JSON library: `org.json/json/20201115` to `com.google.code.gson/gson/2.8.8`
- Fixed Bugs
  - String *reference comparison* (`==`) instead of *value comparison* (`.equals()`)
  - Invalid size accessor for ArrayList: `.length()` instead `.size()`
  - Redundant Casts
- Fixed Style
  - Replaced `if` with modern `switch`'es, where possible