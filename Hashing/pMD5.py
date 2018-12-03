from hashlib import md5
import time
from itertools import repeat

print('_________________')
print()
print('Hashing Benchmark')
print('MD5')
print('_________________')
print()

hashed = ""

file = open('string1024.txt', 'r')
for line in file:
    hashed += line


file.close()
x = 0
lowest = 0
highest = 0
average = 0

for i in repeat(None,150):

	timeS = time.time()
	result = md5(hashed.encode('utf-8')).hexdigest()
	timeT = (time.time()-timeS) * 1000
	if(lowest == 0):
		lowest = timeT
	if(highest == 0):
		highest = timeT
	if(average == 0):
		average = timeT
	if(timeT < lowest):
		lowest = timeT
	if(timeT > highest):
		highest = timeT
	if(average != 0):
		average = (average + timeT) / 2
	
print('Input : ' + hashed)
print('Output : ' + result)
print()
print('Lowest : ' + str(lowest))
print('Average : ' + str(average))
print('Highest : ' + str(highest))
