#!/bin/sh
MAIN_WORKDIR="/opt/jars"

# This is the main function
main()
{
	echo "Test message"
	wjava=$(which java)
	$wjava -jar $MAIN_WORKDIR/talker.jar
}
#
# Execute all
main

