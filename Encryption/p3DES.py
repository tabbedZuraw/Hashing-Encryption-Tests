from pyDes import *
import datetime as dt
from twofish import Twofish
import base64
import bcrypt
import time
import chardet
from itertools import repeat

print('_________________')
print()
print('Encryption Benchmark')
print('3DES')
print('_________________')
print()

hashed = ""

file = open('string16.txt', 'rb')
for line in file:
    hashed = line


file.close()
x = 0
lowest = 0
highest = 0
average = 0
lowestD = 0
highestD = 0
averageD = 0
for i in repeat(None,150):

	timeSE = time.time()
	k = triple_des("ESCRYPT111111111", CBC)
	d = k.encrypt(hashed)
	timeTE = (time.time()-timeSE) * 1000
	if(lowest == 0):
		lowest = timeTE
	if(highest == 0):
		highest = timeTE
	if(average == 0):
		average = timeTE
	if(timeTE < lowest):
		lowest = timeTE
	if(timeTE > highest):
		highest = timeTE
	if(average != 0):
		average = (average + timeTE) / 2
	

	timeSD = time.time()
	res = k.decrypt(d, padmode=PAD_PKCS5)
	timeTD = (time.time()-timeSD) * 1000
	if(lowestD == 0):
		lowestD = timeTD
	if(highestD == 0):
		highestD = timeTD
	if(averageD == 0):
		averageD = timeTD
	if(timeTD < lowestD):
		lowestD = timeTD
	if(timeTD > highestD):
		highestD = timeTD
	if(averageD != 0):
		averageD = (averageD + timeTD) / 2



print('Encrypt')
print('Lowest : ' + str(lowest))
print('Average : ' + str(average))
print('Highest : ' + str(highest))
print('Decrypt')
print('Lowest : ' + str(lowestD))
print('Average : ' + str(averageD))
print('Highest : ' + str(highestD))







