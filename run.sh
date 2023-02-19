#!/bin/bash
mvn clean -q
mvn compile -q

mvn exec:java -q -Dexec.mainClass=org.example.App -Dexec.args="add -c 'This is a new task'"
mvn exec:java -q -Dexec.mainClass=org.example.App -Dexec.args="add -d 2022-03-01 -c 'finalize the agenda exercise'"
mvn exec:java -q -Dexec.mainClass=org.example.App -Dexec.args="list"
mvn exec:java -q -Dexec.mainClass=org.example.App -Dexec.args="update d2d67f1c-9c7f-4f39-aec7-ae88d8c1eb3f -d 2022-04-01"
mvn exec:java -q -Dexec.mainClass=org.example.App -Dexec.args="addSubtask d2d67f1c-9c7f-4f39-aec7-ae88d8c1eb3f -c 'This is a new subtask'"
mvn exec:java -q -Dexec.mainClass=org.example.App -Dexec.args="remove d2d67f1c-9c7f-4f39-aec7-ae88d8c1eb3f"
mvn exec:java -q -Dexec.mainClass=org.example.App -Dexec.args="update d2d67f1c-9c7f-4f39-aec7-ae88d8c1eb3f -s done"

printf "\n"
read -r -p "Press enter to continue"