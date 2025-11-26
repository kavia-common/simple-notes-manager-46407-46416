#!/bin/bash
cd /home/kavia/workspace/code-generation/simple-notes-manager-46407-46416/notes_backend
./gradlew checkstyleMain
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

