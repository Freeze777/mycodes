# Enter your code here. Read input from STDIN. Print output to STDOUT
n = int(raw_input())
L = []
while n > 0:
    inp = raw_input().split()
    if inp[0] == "insert":
        L.insert(int(inp[1]),int(inp[2]))
    elif inp[0] == "delete":
        L.delete(int(inp[1]))
    elif inp[0] == "print":
        print(L)
    elif inp[0] == "append":
        L.append(int(inp[1]))
    elif inp[0] == "remove":
        L.remove(int(inp[1]))
    elif inp[0] == "pop":
        L.pop()
    elif inp[0] == "index":
        L.index(int(inp[1]))
    elif inp[0] == "count":
        L.count(int(inp[1]))
    elif inp[0] == "sort":
        L.sort()
    else:
        L.reverse()
    n -= 1
