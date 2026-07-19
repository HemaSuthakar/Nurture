#!/usr/bin/env bash
# Runs all 5 Git-HOL labs in order, from a clean workspace.
set -euo pipefail
ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

rm -rf "$ROOT_DIR/workspace"

for lab in 01-git-setup-config 02-gitignore 03-branch-merge 04-merge-conflict 05-cleanup-push; do
    echo
    echo "=========================================================="
    echo " Running: $lab"
    echo "=========================================================="
    bash "$ROOT_DIR/$lab/solution.sh"
done

echo
echo "All 5 labs completed successfully."
echo "Inspect the resulting repo at: $ROOT_DIR/workspace/GitDemo"
