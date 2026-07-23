#!/usr/bin/env bash
# Git-HOL Lab 1: Setup Git configuration + add a file to a repository.
source "$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)/scripts/common.sh"

step "Check Git is installed"
run "git --version"

ensure_remote

step "Create and enter the GitDemo project"
rm -rf "$REPO_DIR"
mkdir -p "$REPO_DIR"
cd "$REPO_DIR"
run "git init -b master ."

step "Configure user name and email (repo-local, so it won't touch your global config)"
run "git config user.name  'GitDemo User'"
run "git config user.email 'gitdemo.user@example.com'"

step "Verify the configuration"
run "git config --list --local | grep user"

step "Confirm the .git folder exists (this is the local repository)"
run "ls -la"

step "Create welcome.txt and add content"
run "echo 'Welcome to the Git Hands-On Lab!' > welcome.txt"

step "Verify the file exists and check its content"
run "ls -la welcome.txt"
run "cat welcome.txt"

step "Check status - welcome.txt is untracked (working directory only)"
run "git status"

step "Stage the file so Git tracks it"
run "git add welcome.txt"

step "Commit the staged file"
run "git commit -m 'Add welcome.txt' -m 'First commit for the Git-HOL lab.'"

step "Check status again - working directory and local repo now match"
run "git status"

step "Point the local repo at the remote (our local bare repo stands in for GitLab)"
run "git remote add origin '$REMOTE_DIR'"

step "Pull from the remote (empty the first time, that's expected)"
run "git pull origin master --allow-unrelated-histories || true"

step "Push the local repo to the remote"
run "git push -u origin master"

echo
echo "Lab 1 complete. Repo is at: $REPO_DIR"
