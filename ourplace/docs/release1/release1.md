# Progress for release 1
In release 1 we have a simple app that allows for loading text-posts from file and displaying them in a feed, and creating a new post to be written to the file. This corresponds to user story 1 and 2 in the [userStory.md](../../../userStory.md).

# Workflow
## Start phase
We started the project by creating issues for future features we knew we would have to implement, then we linked the relevant ones to Milestone 1: Gruppeinnlevering 1. We made issue and merge request templates to keep them somewhat consistent, and so people new to issues and merge requests have a guide to follow. As new required changes are discovered it is up to everyone to create new issues continuously.

## Development phase
After assigning issues to members, we made branches for each issue/feature. We would try to keep each branch up to date with the newest changes by either pulling from origin/master or by rebasing.

Programming was mostly done individually on separate branches, but on occasion we would utilize pair-programming. We have not added co-authors to our commits, but may look into doing so in the future. An example of an issue where pair-programming was done is "Make feed fetch posts from file", where Erlend would be programming, but work together with Nora or Silje.

## Merge phase
When the change is deemed to be finished, the assignee creates a merge request in gitlab, and waits for approval. Erlend had the main responsibility for code review, Nora was a secondary reviewer. After approval, the branch would be merged to master, the issue assignee would decide whether the merged branch would be deleted. Commits would not be squashed so we have more control over the changes. The issue assignee would then decide whether they want to close the issue, or keep it open if they deemed it to be not completely finished.

## Exceptions
On certain smaller changes, like adding the templates for issue/merge requests, expanding the folder structure for future files, and cleaning up comments, we found it was okay to make the changes directly in master, as long as the commits were kept concentrated on specific changes so they are easily revertible. 

On occasion the changes for a commit would be extremely minor (like removing a comment from a file), the commit message would just be "." indicating it as being more of a continuation of the previous commit.