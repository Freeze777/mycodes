import json

data = []
with open("training.json") as f:
    for line in f:
	data.append(json.loads(line))
print