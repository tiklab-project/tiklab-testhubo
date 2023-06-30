#!/bin/sh

DIR=$(dirname "$PWD")

JAR_DIR=`find ${DIR}/lib -name "tiklab-upgrade-starter*.jar" -print`

JAVA_HOME=${DIR}/embbed/jdk-16.0.2

if [ -d ${JAR_DIR} ] ; then
  echo "The upgrade program does not exist。"
  exit 1
fi

cd ${JAVA_HOME}/bin && ./java -jar -Dfiledir="${DIR}" "${JAR_DIR}"

echo "System update completed."