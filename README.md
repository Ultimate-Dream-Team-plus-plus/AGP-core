# AGP-core

The one week long project for the AGP course. [WIP until we have the subject]

## Requirements

- At least Java version 11
- Maven (the last version is good)
- Tomcat server version 9 at least (better to have version 9 though)

Eclipse EE or equivalent is required to develop (or be good installing plugins)
> Other IDEs can be used, don't worry

## How to install

1. Clone the project at the right location (it will not move after integrating into Eclipse)
2. Import (described for Eclipse only)
    1. "File" > "Import" > "Existing Maven projects"
    2. Select the wanted folder
    3. At this moment, a ".pom" file should be already selected, you can finish the operation
3. Configuration
    - After importing project, the src/main/java is filled with files ending with ".template" : DO NOT MODIFY THESE !
    - Copy each file and remove the ".template" from the new files's names in order to add personal configurations (like DB creadentials or Lucene index path for example)
    > If need to update an important constant, or add a new one, the template can be altered, but others must be warned afterwards
4. Run the server and see the magnificient result !

5. voilà !