import json
import re
#from pandas import DataFrame

data = []
id=1
with open("training.json") as f:
    for line in f:
        #line = re.sub("[^a-zA-Z]"," ",line)
        #print line
        #print type(line)
        obj=json.loads(line.lower())
        data.append(obj)
        

  