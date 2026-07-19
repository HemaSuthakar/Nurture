#!/usr/bin/env bash
# Git-HOL Lab 2: Ignore unwanted files/folders with .gitignore.
# Prerequisite: Lab 1 has been run (uses the same GitDemo workspace).
source "$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)/scripts/common.sh"

if [ ! -d "$REPO_DIR/.git" ]; then
    echo "GitDemo repo not found — running Lab 1 first..."
    "$ROOT_DIR/01-git-setup-config/solution.sh"
fi

cd "$REPO_DIR"

step "Confirm we're starting from a clean working directory"
run "git status"

step "Create a .log file and a log/ folder (the things we want ignored)"
run "echo 'debug output' > app.log"
run "mkdir -p log"
run "echo 'nested log data' > log/nested.log"

step "Without .gitignore, Git sees all of these as untracked"
run "git status"

step "Create .gitignore to exclude *.log files and the log/ folder"
cat > .gitignore <<'EOF'
# Ignore any file with a .log extension
*.log

# Ignore the log folder entirely
log/
EOF
run "cat .gitignore"

step "Check status again - app.log and log/ no longer show as untracked"
run "git status"

step "Confirm Git is aware of them as ignored (not just silently absent)"
run "git status --ignored"

step "Stage and commit the .gitignore file itself"
run "git add .gitignore"
run "git commit -m 'Add .gitignore to exclude log files and folder'"

step "Final status - working directory, staging area, and repo all agree"
run "git status"

echo
echo "Lab 2 complete."
