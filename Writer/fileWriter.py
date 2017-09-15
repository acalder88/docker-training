#!/usr/local/bin/python3
def main():
    print("I am running")
    with open("/tmp/test.txt", "a") as myfile:
        myfile.write("Test \n")
if __name__ == "__main__":
    main()
