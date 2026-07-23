#!/usr/bin/env bash
# Shared settings for all Git-HOL lab scripts.
# All labs work inside ./workspace so nothing outside the project folder is touched,
# and a local bare repo stands in for the "remote GitLab/GitHub" repo used in the
# original lab sheets (so the whole thing runs offline, with no account needed).

set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
WORKSPACE="$ROOT_DIR/workspace"
REPO_DIR="$WORKSPACE/GitDemo"
REMOTE_DIR="$WORKSPACE/GitDemo-remote.git"

step() { echo; echo "==> $*"; }
run()  { echo "\$ $*"; eval "$@"; echo; }

ensure_remote() {
    if [ ! -d "$REMOTE_DIR" ]; then
        mkdir -p "$WORKSPACE"
        git init --bare "$REMOTE_DIR" -b master >/dev/null
        echo "Created local bare repo to act as the remote: $REMOTE_DIR"
    fi
}
