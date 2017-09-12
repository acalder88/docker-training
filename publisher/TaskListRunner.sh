#!/bin/sh
MAIN_WORKDIR="/opt/jars"

# This is the main function
main()
{
	echo "Test message"
	wjava=$(which java)
	$wjava -jar $MAIN_WORKDIR/publisher.jar
}
#
# Execute all
main

