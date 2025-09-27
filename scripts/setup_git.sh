#!/usr/bin/env bash
set -euo pipefail

git init
git add .
git commit -m "init: maven project structure, junit5, cli, tests, metrics"
git branch -M main
git checkout -b feature/algorithm
git checkout -b feature/metrics
git checkout -b feature/testing
git checkout -b feature/cli
git checkout -b feature/optimization

echo "Branches created: main + feature/*"
