#!/usr/bin/env bash
# Git-HOL Lab 4: Resolve a merge conflict.
# Prerequisite ID from the original lab: "Git-T03-HOL_001" -> our Lab 3.
source "$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)/scripts/common.sh"

if [ ! -f "$REPO_DIR/branch-feature.txt" ]; then
    echo "GitDemo repo (with Lab 3 state) not found — running Labs 1-3 first..."
    "$ROOT_DIR/01-git-setup-config/solution.sh"
    "$ROOT_DIR/02-gitignore/solution.sh"
    "$ROOT_DIR/03-branch-merge/solution.sh"
fi

cd "$REPO_DIR"

step "1. Verify master is in a clean state"
run "git checkout master"
run "git status"

step "2. Create branch 'GitWork' and add hello.xml"
run "git checkout -b GitWork"
cat > hello.xml <<'EOF'
<hello>
  <message>Hello from GitWork - version 1</message>
</hello>
EOF
run "cat hello.xml"

step "3. Update the content of hello.xml and observe the status"
cat > hello.xml <<'EOF'
<hello>
  <message>Hello from GitWork - version 1 (updated)</message>
</hello>
EOF
run "git status"

step "4. Commit the changes to the branch"
run "git add hello.xml"
run "git commit -m 'Add hello.xml on GitWork'"

step "5. Switch to master"
run "git checkout master"

step "6. Add hello.xml to master with different content"
cat > hello.xml <<'EOF'
<hello>
  <message>Hello from master - a completely different greeting</message>
</hello>
EOF
run "git add hello.xml"
run "git commit -m 'Add hello.xml on master'"

step "8. Observe the log across both branches"
run "git log --oneline --graph --decorate --all"

step "9. Check the differences with git diff"
run "git diff master GitWork -- hello.xml || true"

step "10. Attempt the merge - this triggers a conflict on hello.xml"
set +e
git merge GitWork -m "Merge GitWork into master"
MERGE_STATUS=$?
set -e
echo "(git merge exit status: $MERGE_STATUS - non-zero is expected, that's the conflict)"
run "git status"

step "11. Resolve the conflict by hand-editing hello.xml"
cat > hello.xml <<'EOF'
<hello>
  <message>Hello - merged content from master and GitWork</message>
</hello>
EOF
run "cat hello.xml"

step "12. Mark it resolved, then commit the merge"
run "git add hello.xml"
run "git commit -m 'Resolve merge conflict in hello.xml'"

step "13. Confirm the merge is complete"
run "git status"
run "git log --oneline --graph --decorate --all"

echo
echo "Lab 4 complete."
