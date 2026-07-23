#!/usr/bin/env bash
# Git-HOL Lab 3: Branching and merging.
# Prerequisite: Labs 1 & 2 have been run.
source "$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)/scripts/common.sh"

if [ ! -f "$REPO_DIR/.gitignore" ]; then
    echo "GitDemo repo (with Lab 2 state) not found — running Labs 1-2 first..."
    "$ROOT_DIR/01-git-setup-config/solution.sh"
    "$ROOT_DIR/02-gitignore/solution.sh"
fi

cd "$REPO_DIR"

echo
echo "############ BRANCHING ############"

step "1. Create a new branch 'GitNewBranch'"
run "git branch GitNewBranch"

step "2. List all local and remote branches - '*' marks the current branch"
run "git branch -a"

step "3. Switch to the new branch and add a file with content"
run "git checkout GitNewBranch"
run "echo 'This file lives on GitNewBranch.' > branch-feature.txt"

step "4. Commit the change to the branch"
run "git add branch-feature.txt"
run "git commit -m 'Add branch-feature.txt on GitNewBranch'"

step "5. Check status"
run "git status"

echo
echo "############ MERGING ############"

step "1. Switch back to master"
run "git checkout master"

step "2. List the differences between master and the branch (CLI diff)"
run "git diff master GitNewBranch || true"

step "3. (Visual diff) In the original lab this used the P4Merge GUI tool:"
echo "     git difftool -d master GitNewBranch"
echo "   Any GUI diff tool configured via 'git config diff.tool' works here."

step "4. Merge the branch into master"
run "git merge GitNewBranch -m 'Merge GitNewBranch into master'"

step "5. Observe the log"
run "git log --oneline --graph --decorate --all"

step "6. Delete the branch now that it's merged, and check status"
run "git branch -d GitNewBranch"
run "git status"

echo
echo "Lab 3 complete."
