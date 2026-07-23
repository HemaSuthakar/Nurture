#!/usr/bin/env bash
# Git-HOL Lab 5: Clean up and push back to the remote.
# Prerequisite ID from the original lab: "Git-T03-HOL_002" -> our Lab 4.
source "$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)/scripts/common.sh"

if [ ! -f "$REPO_DIR/hello.xml" ]; then
    echo "GitDemo repo (with Lab 4 state) not found — running Labs 1-4 first..."
    "$ROOT_DIR/01-git-setup-config/solution.sh"
    "$ROOT_DIR/02-gitignore/solution.sh"
    "$ROOT_DIR/03-branch-merge/solution.sh"
    "$ROOT_DIR/04-merge-conflict/solution.sh"
fi

cd "$REPO_DIR"

step "1. Verify master is in a clean state"
run "git checkout master"
run "git status"

step "2. List out all the available branches"
run "git branch -a"

step "3. Pull the remote repository into master (picks up anything new on origin)"
run "git pull origin master"

step "4. Push the pending changes from Lab 4 up to the remote"
run "git push origin master"

step "5. Verify the changes are reflected on the remote"
echo "Inspecting the bare 'remote' repo directly:"
run "git --git-dir='$REMOTE_DIR' log --oneline --graph --decorate --all"
run "git --git-dir='$REMOTE_DIR' branch -a"

echo
echo "Lab 5 complete. All local commits are now on the remote: $REMOTE_DIR"
