#!/bin/bash
PYTHON="$(which python)"
ARGUMENTS="$@"
echo "${ARGUMENTS[@]}"
echo "${ARGUMENTS[0]}"
echo "${ARGUMENTS[1]}"

set -- "$@"
while read argument
do
  case $argument in
    "-c")
      COMMAND="$PYTHON -c "
    ;;
    *)
      COMMAND="$PYTHON "
    ;;
  esac
  shift
done <<<"${ARGUMENTS[@]}"

echo $COMMAND
#EXEC_TYPE="$1"
#EXEC_PGM="$2"
#[[ $# == 1 ]] && {
#  EXEC_TYPE=""
#  EXEC_PGM="$1"
#}
#$PYTHON "${EXEC_TYPE}" "${EXEC_PGM}"
#$PYTHON "$@"
#$PYTHON -c "$@"
$COMMAND "$@"

