# Progress for release 2
In release 2 we have a simple app that allows for loading text-posts from file and displaying them in a feed, and creating a new post to be written to the file. This corresponds to user story 1 and 2 in the [project readme](../../README.md). The focus for this release was modularization, code quality and coverage like JaCoCo, Spotbugs and Checkstyle as well as hosting the project eclipse che.   

# Workflow
## Start phase
Release 2 was started with the establishment of milestones aligned with the provided assignment, with Milestone 2 designated as 'Gruppeinnlevering 2'. We then broke down the tasks into more manageable issues, assigned them to team members, and created individual branches for each issue. As the project progressed, we remained adaptable, incorporating new issues as necessary.

## Development phase
We would try to keep each branch up to date with the newest changes by rebasing from an up to date master.

Programming was mostly done individually on separate branches as it was difficult to meet up as a group, but on occasion we did utilize pair-programming. We forgot at times to add co-authors, but this is something we will remember to do more in the future. For this assignment Silje and Isabelle used pair-programming to try to implement JaCoCo. And Nora and Erlend used pair programming to resolve Jackson errors.

During the development process; `mvn spotbugs:check`, `mvn jacoco:report` and `checkstyle:check `were utilized to evaluate the code quality. As we experienced troubles with the plugins, it took some time for them to be implemented in the workflow, but they will be heavily employed in later releases to guarantee code quality.

## Merge phase
When the change is deemed to be finished, the assignee creates a merge request in gitlab, and waits for approval. We made a common rule that the reviewer has a responsibility to check that the app is functional before approval. After approval, the branch would be merged to master by the person who created the merge request, the issue assignee would decide whether the merged branch would be deleted. Commits would not be squashed so we have more control over the changes. The issue assignee would then decide whether they want to close the issue, or keep it open if they deemed it to be not completely finished.

## Exceptions
In this assignment we have gotten better at writing commit messages.  

# Choices

## Test coverage
Like JaCoCo shows, we have made different tests for functionality in our code. For release 2 we focused on making tests for what we considered our projects main functionalities to be (because of time constraints). Which is a functional user interface and the ability to make persistent posts. We will in the future make tests that cover our code more thoroughly.   

## Checkstyle and Spotbugs
After implementing Checkstyle we have encountered several Checkstyle errors during our development process. We do believe that it is important to follow these coding conventions, but that for this release it is more important that our app is functional. Due to time constraints (but not for lack of trying) we couldn't find a good way to modify the configuration of Checkstyle to exclude certain rules, and therefore we decided that Checkstyle violations shouldn't break the build, and instead we implement checking and resolving Checkstyle violations as an important part of our workflow.

Some of these Checkstyle violations are package naming conventions, and missing package-info files. As for the first one, we believe we are working with the package names we were dealt. The modules were created using the mvn archetype:generate maven command, and we attempted to follow the to-do list example when creating the folder structure inside those modules. This resulted in our package names being gr2310.ourplace.modulename, which has an extra "." than the Sun code conventions liked. We figured as long as the modules and packages were functional, there was no real harm in an unconventional package name, and therefore shouldn't break the build either. As for package-info files. We do not have any relevant examples of these, and notice that they are not present in the to-do list example either. Thus we believe this is also not necessary. We also found that other Checkstyle conventions wanted to, for example, hide the constructor for the spring boot app class, which made the class not functional. In summary we opted not to resolve some violations because of lack of education, the sense that some violations were unresolvable, and time constraints.

The same goes for certain Spotbugs bugs. The (possibly odd designed) App class, and the controller classes may expose internal representation, but for now we also feel that it's unlikely for the internal representation to be modified in any significant way, and therefore chose to exclude these bugs from the check. The exclusions can be viewed [here](../../../config/spotbugs/exclude.xml). We are looking into better ways to create our classes, in the future, but this is not a problem we can solve in our current release.

## Document metaphor or implicit saving (?)
Our project is an app. It has one main purpose, which is to save and publish post to a single chronological feed. The graphical user interface is supposed to be a single "main stage" where users are meant to interact together. Therefore we choose *implicit saving*, the app takes care of saving and publishing the posts, and retrieving them from the file (which is not meant to be accessible by the user when we start moving things to a proper server).

## Future improvements
Here is a list of things we are considering changing for future releases:

- Change folder structure so packages can match naming conventions
- Find a good way to exclude certain Checkstyle violations
- Let JaCoCo create an aggregate report so that we don't have to check every separate reports.
- Improving the design of the app class so that internal representation is not exposed, but also allows controller classes to change the scene easily.
- Resolve whatever problem that is not making the Eclipse Che link functional.