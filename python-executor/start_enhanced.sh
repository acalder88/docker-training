#!/bin/bash
#
# To execute a local file inside a running container:
# NOTE: python version is not mandatory, if not specified it runs using
#       the python intepreter found in path.
# docker run -ti --rm <image> <-2|3 (python version)> -c <local_file_path>
# To execute python code without identation:
# docker run -ti --rm <image> <-2|3 (python version)> -c "<python_code>"
#   Example for this case:
#   docker run -ti --rm <image> -c "import os; print(os.environ);"
#   will execute the python code using the interpreter found in path.
# To execute a file inside a running container:
# docker run -ti --rm <image> <-2|3 (python version)> <container_file_path>
#
PYTHON2="$(type -P python2)"
PYTHON3="$(type -P python3)"
PYTHON="$(type -P python)"

LOCAL_FILE=false

while [[ $# -gt 0 ]]
do
  arg=$1
  case $arg in
    "-2")
      PYTHON="$PYTHON2"
    ;;
    "-3")
      PYTHON="$PYTHON3"
    ;;
    "-c")
      PYTHON="$PYTHON $arg"
      LOCAL_FILE=true
    ;;
    *)
      F_ARGS=( "$@" )
    ;;
  esac
  shift
done

[[ $LOCAL_FILE == true ]] && {
  $PYTHON "$(printf "%s\n" "${F_ARGS[@]}")"
} || {
  $PYTHON "${F_ARGS[@]}"
}

