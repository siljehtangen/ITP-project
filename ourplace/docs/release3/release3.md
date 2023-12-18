# Progress in release 3
In release 3 we have extended the application with a RESTful web server using Spring Boot.  
We have also created new functionality for our app, corresponding to [user story 4](../../../userStory.md). In total, our app now fulfills the user stories 1, 2 and 4. If we were given more time, we would have expanded the app to include user story 3. We have also written more tests for new and existing features, and the coverage rate is typically around 80~90% for each module.

## REST
The REST server, powered by Spring Boot, serves as the intermediary between clients and the server using HTTP, following RESTful principles. It defines endpoints that clients interact with, each mapped to specific URIs and HTTP methods.  
The full documentation of the supported requests is in [apidoc.md](apidoc.md).

## Shippable product
We have also ensured our project is configured for shippable product. We are using the Maven Assembly plugin to package the fxui app into an executable jar, that can be moved an run from anywhere on the computer, as long as the server is running.  
The jar can be found in the fxui's target folder after the package phase. The name of the executable jar is [fxui-1.0-SNAPSHOT-jar-with-dependencies.jar](../../fxui/target/fxui-1.0-SNAPSHOT-jar-with-dependencies.jar).

# Workflow
To avoid repeating what was said in the previous release, this document will only mention the changes to our workflow since release 2. If something is not mentioned here, but was in release 2, you can assume that it has stayed the same.

## Commit messages
Some of the members of our team, had misunderstood some of the recommended commit message practices, and written their messages like "Fixed ..." instead of "Fix ...", as an example.  
In this release this confusion was cleared up, and the commit message quality has improved.  
We also have used the Co-authored-by footer more extensively, especially since pair programming is now happening mostly in person. For example, the "sort by likes" feature was made by Erlend and Isabelle using pair-programming. It was also used by Nora and Erlend when sorting out merge conflicts. Other than this, programming was mostly done individually on separate branches. 

## Code review practices
I previous releases, as was mentioned in their respective documentations, we have implemented code review as a part of our workflow since the very beginning. However, these reviews were communicated internally, and were not visible on GitLab aside from the eventual approval.  
In this release, we migrated this internal communication over to GitLab, using their code review/comment feature. Aside from that, we have still kept the requirements that the reviewer must check for Spotbugs and Checkstyle violations, as well that the application runs seamlessly on their computer, in addition to peer reviewing each other's code. If the reviewer finds no flaws in the code, they must also comment this in GitLab, in addition to the approval button.

# Challenges
In the [challenges file](challenges.md) we have listed some of the challenges we encountered during production, that we think could prossibly have been resolved in a better way, if we were given more time.

# Known problems
During testing some of our group members have experienced problems, when others don't. Here is a brief overview of problems we know to have occurred at certain times, but have found to be mostly working.  
These were not added to the challenges.md file as the faults are sporadic, but mostly resolved. It is only here to help in case other people encounter the same problems.

The tests in RestClientTest would sometimes not run, and we would get a `Failed to bind ... :8080` error. This can be resolved by typing this in the terminal: `lsof -i :8080`. Then, take the PID-number: `kill PID-number` and again check with `lsof -i :8080`. After this, the tests in RestClientTest should run.

## Eclipse Che
We have continuously experienced some troubles with Eclipse Che, and it has been difficult to understand whether the fault was on our side or theirs.  
For example, one member experienced that the "Waiting for workspace deployment" step resulted in a timeout because it took too long. Still, we have confirmed that a member managed to run the app and the tests on Eclipse Che, so it should still be functional.  
According to our project supervisor (teaching aid), Eclipse is known to cause problems when a user repeatedly tries to open the workspace, and it may help to open Eclipse Che in an incognito browser.

## Problems with fxui tests
In a late stage of development, we experienced that the TestFX robot tests, specifically the testUI test in AppTest, would sometimes fail on macOS, but would always succeed on Windows. We suspect that macOS may be blocking some of the functionality of the Robot tool, as it could be used in a damaging manner, considering it has access to the user's cursor. We have added this note to the root readme, and fxui tests are skipped by default anyway, so it shouldn't be too big of a problem, but it is something to be aware of. 

We also added some more tests, to balance out the issue, but we found that some of them, especially FeedControllerTest, would not be reflected in the JaCoCo report. Again this is not necessarily a big problem in our eyes, but is something to take into account.

# Improvements
Here are the improvements that were mentioned in release 2, and their status after release 3:

- **Change folder structure so packages can match naming conventions:** (SCRAPPED) We decided to ignore the Checkstyle naming conventions regarding the folder structure since the app is working, and we would rather consider changing the rule set configurations.

- **Find a good way to exclude certain Checkstyle violations:** (ON HOLD) This could not be achieved during this release, as other requirements had priority.

- **Let JaCoCo create an aggregate report so that we don't have to check every separate reports:** (SCRAPPED) After adding the client module and tests, it would be impossible to create an aggregate report, as fxui requires the server to be running, and client requires the server to not be running. At least one module would be excluded from the report, which just seems like a waste.

- **Improving the design of the app class so that internal representation is not exposed, but also allows controller classes to change the scene easily:** (ON HOLD) We still haven't figured out a proper solution, and other requirements took priority over figuring out if it is even possible to accomplish.

- **Resolve whatever problem that is not making the Eclipse Che link functional:** (RESOLVED) We discovered that the problem with Eclipse Che was only on our end, as some did not have the personal access tokens, and in the end the link was indeed functional.