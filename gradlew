#!/bin/sh
APP_HOME="`pwd -P`"
CLASSPATH="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"
JAVA_HOME_CANDIDATES="/usr/lib/jvm/java-17-openjdk-amd64 /usr/lib/jvm/java-17-openjdk /usr/lib/jvm/java-11-openjdk-amd64 /usr/local/lib/jvm/openjdk17"
for dir in $JAVA_HOME_CANDIDATES; do
  if [ -d "$dir" ]; then JAVA_HOME="$dir"; break; fi
done
if [ -n "$JAVA_HOME" ]; then JAVACMD="$JAVA_HOME/bin/java"; else JAVACMD=java; fi
exec "$JAVACMD" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
